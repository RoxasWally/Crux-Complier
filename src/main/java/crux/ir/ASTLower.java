package crux.ir;

import crux.ast.SymbolTable.Symbol;
import crux.ast.*;
import crux.ast.OpExpr.Operation;
import crux.ast.traversal.NodeVisitor;
import crux.ast.types.*;
import crux.ir.insts.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class InstPair {
  // should have a start and end instruction
  //need getters and setters
  //get the value
  Instruction start, end;
  Variable value;

  public InstPair(Instruction start, Instruction end, Variable value){
    this.start = start;
    this.end = end;
    this.value = value;
  }
  public InstPair(Instruction Start, Variable Value){
    start = Start;
    end = Start;
    value = Value;
  }
  public InstPair(Instruction Start, Instruction End){
    start = Start;
    end = End;
    value = null;
  }
  public InstPair(Instruction Start){
    start = Start;
    end = Start;
    value = null;
  }

  public Instruction getStart(){
    return start;
  }
  public Instruction getEnd(){
    return end;
  }
  public Variable getValue(){
    return value;
  }

}


/**
 * Convert AST to IR and build the CFG
 */
public final class ASTLower implements NodeVisitor<InstPair> {
  //stores a list of global declecaration and functions
  private Program mCurrentProgram = null;
  private TypeChecker checker;
  private Function mCurrentFunction = null;

  //helper function to check if source is null
  private void addEdge(Instruction source, Instruction destination){
    if (source == null ){
      mCurrentFunction.setStart(destination);
    }else{
      source.setNext(0, destination);
    }
  }



  private Map<Symbol, LocalVar> mCurrentLocalVarMap = null;

  /**
   * A constructor to initialize member variables
   */
  public ASTLower() {}

  public Program lower(DeclarationList ast) {
    visit(ast);
    return mCurrentProgram;
  }

  @Override
  public InstPair visit(DeclarationList declarationList) {
    mCurrentProgram = new Program();
    //similar to stg2
    for(Node dec : declarationList.getChildren())
      dec.accept(this);
    return null;
  }

  /**
   * This visitor should create a Function instance for the functionDefinition node, add parameters
   * to the localVarMap, add the function to the program, and init the function start Instruction.
   */
  @Override
  public InstPair visit(FunctionDefinition functionDefinition) {
    TypeList functionType = new TypeList();
    for (Symbol sym : functionDefinition.getParameters())
      functionType.append(sym.getType());
    var myFunction = functionDefinition.getSymbol();
    var fucType = (FuncType) myFunction.getType();
    mCurrentFunction = new Function(myFunction.getName(), fucType);
    mCurrentLocalVarMap = new HashMap<>();
    List<LocalVar> myList = new ArrayList<>();
    for(Symbol symParam : functionDefinition.getParameters()){
      LocalVar tempVariable = mCurrentFunction.getTempVar(symParam.getType());
      myList.add(tempVariable);
      mCurrentLocalVarMap.put(symParam, tempVariable);
    }
    mCurrentFunction.setArguments(myList);
    mCurrentProgram.addFunction(mCurrentFunction);
    InstPair result = functionDefinition.getStatements().accept(this);
    mCurrentFunction.setStart(result.getStart());
    mCurrentLocalVarMap = null;
    mCurrentFunction = null;
    return null;
  }

  @Override
  public InstPair visit(StatementList statementList) {
    InstPair sym = null;
    Instruction firstIns = null;
    Instruction lastIns = null;
    for (Node statement : statementList.getChildren()) {
      sym = statement.accept(this);
      if (firstIns == null) {
        firstIns = sym.getStart();
      } else {
        addEdge(lastIns, sym.getStart());
      }
      lastIns = sym.getEnd();
    }
      if (sym != null) {
        return new InstPair(firstIns, lastIns, sym.getValue());
      } else {
        NopInst lastNop = new NopInst();
        return new InstPair(lastNop, lastNop, null);
      }
    }

  /**
   * Declarations, could be either local or Global
   */
  @Override
  public InstPair visit(VariableDeclaration variableDeclaration) {
    Symbol mySym = variableDeclaration.getSymbol();
    if (mCurrentFunction == null){
      var global = new GlobalDecl(mySym, IntegerConstant.get(mCurrentProgram, 1));
      mCurrentProgram.addGlobalVar(global);
    }else{
      LocalVar local = mCurrentFunction.getTempVar(variableDeclaration.getType());
      mCurrentLocalVarMap.put(mySym, local);
    }
    return new InstPair(new NopInst());
  }

  /**
   * Create a declaration for array and connected it to the CFG
   */
  @Override
  public InstPair visit(ArrayDeclaration arrayDeclaration) {
    long val = ((ArrayType) arrayDeclaration.getSymbol().getType()).getExtent();
    GlobalDecl global = new GlobalDecl(arrayDeclaration.getSymbol(),IntegerConstant.get(mCurrentProgram,val));
    mCurrentProgram.addGlobalVar(global);
    return new InstPair(new NopInst());

  }

  /**
   * LookUp the name in the map(s). For globals, we should do a load to get the value to load into a
   * LocalVar.
   */
  @Override
  public InstPair visit(VarAccess name) {
    /*Check if it is local or global by checking mCurrentLocalVarMap
    If local: return empty InstPair with LocalVar as value
    If global:  use AddressAt and LoadInst
    For all cases, InstPair val is LocalVar
    */
    LocalVar temp;

    if (mCurrentLocalVarMap.containsKey(name.getSymbol())) {
      temp = mCurrentLocalVarMap.get(name.getSymbol());
      return new InstPair(new NopInst(), temp);
    } else {
      AddressVar addressVar = mCurrentFunction.getTempAddressVar(name.getType());
      AddressAt accessAddress = new AddressAt(addressVar, name.getSymbol());
      temp = mCurrentFunction.getTempVar(name.getType());
      LoadInst load = new LoadInst(temp, accessAddress.getDst());
      accessAddress.setNext(0, load);
      return new InstPair(accessAddress, load, temp);
    }
  }

  /**
   * If the location is a VarAccess to a LocalVar, copy the value to it. If the location is a
   * VarAccess to a global, store the value. If the location is ArrayAccess, store the value.
   */
  @Override
  public InstPair visit(Assignment assignment) {
    /*Do not visit the location.
     Instead, check if it is an ArrayAccess or global VarAccess.
     If yes, use AddressAt and StoreInst (similar to visiting them but using Store instead of Load).
     Otherwise, use CopyInst (into LocalVar).
     finish array access first
    */

    Expression lhs = assignment.getLocation();
    Expression rhs = assignment.getValue();
    var rhsRes = rhs.accept(this);

    if(lhs.getClass().equals(VarAccess.class)) {
      if (mCurrentLocalVarMap.containsKey(((VarAccess) lhs).getSymbol())) {
        LocalVar var = mCurrentLocalVarMap.get(((VarAccess) lhs).getSymbol());
        CopyInst copy = new CopyInst(var, rhsRes.getValue());
        rhsRes.getEnd().setNext(0, copy);
        return new InstPair(rhsRes.getStart(), copy);
      } else {
        AddressVar destAddress = mCurrentFunction.getTempAddressVar(assignment.getType());
        AddressAt at = new AddressAt(destAddress, ((VarAccess) lhs).getSymbol());
        at.setNext(0,rhsRes.start);
        StoreInst store = new StoreInst((LocalVar) rhsRes.value, destAddress);
        rhsRes.getEnd().setNext(0, store);
        return new InstPair(at,store);
      }
    }else {
      AddressVar destAddress = mCurrentFunction.getTempAddressVar(assignment.getType());
      InstPair index = ((ArrayAccess) lhs).getIndex().accept(this);
      AddressAt at = new AddressAt(destAddress, ((ArrayAccess) lhs).getBase(), (LocalVar) index.value);
      index.end.setNext(0,at);
      at.setNext(0, rhsRes.start);
      StoreInst store = new StoreInst((LocalVar) rhsRes.value, destAddress);
      rhsRes.getEnd().setNext(0, store);

      return new InstPair(index.start, store);

    }
    //still needs to return null if those conditions aren't met but lhs and rhs will still be accepted
  }


  /**
   * Lower a Call.
   */
  @Override
  public InstPair visit(Call call) {
    /*
    Similar to StatementList, visit each argument and connect them
    Depending on the return value, use different CallInst constructor
    If return value exists, returning InstPair’s val is same as destVar in constructor
     */
    Instruction beg =  null;
    Instruction current = null;
    List<LocalVar> myList = new ArrayList<>();
    Variable value = null;
    for(Expression n: call.getArguments())
    {
      InstPair result = n.accept(this);
      if (beg == null)
      {
        beg = result.getStart();
      }
      else
      {
        current.setNext(0, result.getStart());
      }
      current = result.getEnd();

      var tmp = mCurrentFunction.getTempVar(result.getValue().getType());
      if (result.getValue().getClass().equals(AddressVar.class))
      {
        LoadInst myLoad = new LoadInst(tmp, (AddressVar) result.getValue());
        current.setNext(0, myLoad);
        current = myLoad;
      }
      else
      {
        tmp = (LocalVar) result.getValue();
      }
      myList.add(tmp);
    }


    FuncType myFuncType = (FuncType) call.getCallee().getType();
    if (!myFuncType.getRet().getClass().equals(VoidType.class))
    {
      LocalVar myTmp = mCurrentFunction.getTempVar(myFuncType.getRet());
      var myCalls = new CallInst(myTmp, call.getCallee(), myList);
      if (beg == null)
      {
        return new InstPair(myCalls, myCalls, myTmp);
      }
      current.setNext(0, myCalls);
      return new InstPair(beg, myCalls, myTmp);
    }
    else
    {
      var myCalls = new CallInst(call.getCallee(), myList);
      if (beg == null)
      {
        return new InstPair(myCalls, myCalls, value) ;
      }
      current.setNext(0, myCalls);
      return new InstPair(beg, myCalls, value);
    }
  }



  /**
   * Handle operations like arithmetics and comparisons. Also handle logical operations (and,
   * or, not).
   */
  @Override
  public InstPair visit(OpExpr operation) {

    String op = operation.getOp().toString();



      LocalVar dst = mCurrentFunction.getTempVar(operation.getType());
      switch (op) {
        case "+": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          var res = new BinaryOperator(BinaryOperator.Op.Add, dst, leftVal, rightVal);
          rhsRes.getEnd().setNext(0,res);
          return new InstPair(lhsRes.getStart(), res, dst);
        }
        case "-": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          var res = new BinaryOperator(BinaryOperator.Op.Sub, dst, leftVal, rightVal);
          rhsRes.getEnd().setNext(0,res);
          return new InstPair(lhsRes.getStart(), res, dst);
        }
        case "*": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          var res = new BinaryOperator(BinaryOperator.Op.Mul, dst, leftVal, rightVal);
          rhsRes.getEnd().setNext(0,res);
          return new InstPair(lhsRes.getStart(), res, dst);
        }
        case "/": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          var res = new BinaryOperator(BinaryOperator.Op.Div, dst, leftVal, rightVal);
          rhsRes.getEnd().setNext(0,res);
          return new InstPair(lhsRes.getStart(), res, dst);

        }
        case ">=": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          var cmpRes = new CompareInst(dst, CompareInst.Predicate.GE, leftVal, rightVal);
          rhsRes.getEnd().setNext(0,cmpRes);
          return new InstPair(lhsRes.getStart(), cmpRes, dst);
        }
        case ">": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          var cmpRes = new CompareInst(dst, CompareInst.Predicate.GT, leftVal, rightVal);
          rhsRes.getEnd().setNext(0,cmpRes);
          return new InstPair(lhsRes.getStart(), cmpRes, dst);
        }
        case "<=": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          lhsRes.getEnd().setNext(0, rhsRes.getStart());

          var cmpRes = new CompareInst(dst, CompareInst.Predicate.LE, leftVal, rightVal);
          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          rhsRes.getEnd().setNext(0,cmpRes);
          return new InstPair(lhsRes.getStart(), cmpRes, dst);

        }
        case "<": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          lhsRes.getEnd().setNext(0, rhsRes.getStart());

          var cmpRes = new CompareInst(dst, CompareInst.Predicate.LT, leftVal, rightVal);
          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          rhsRes.getEnd().setNext(0,cmpRes);
          return new InstPair(lhsRes.getStart(), cmpRes, dst);

        }
        case "==": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          var cmpRes = new CompareInst(dst, CompareInst.Predicate.EQ, leftVal, rightVal);
          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          rhsRes.getEnd().setNext(0,cmpRes);
          return new InstPair(lhsRes.getStart(), cmpRes, dst);

        }
        case "!=": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);


          LocalVar leftVal = (LocalVar) lhsRes.getValue();
          LocalVar rightVal = (LocalVar) rhsRes.getValue();

          var cmpRes = new CompareInst(dst, CompareInst.Predicate.NE, leftVal, rightVal);
          lhsRes.getEnd().setNext(0, rhsRes.getStart());
          rhsRes.getEnd().setNext(0,cmpRes);
          return new InstPair(lhsRes.getStart(), cmpRes, dst);
        }
        case "&&": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);



          NopInst nop = new NopInst();
          LocalVar predicate1 = mCurrentFunction.getTempVar(new BoolType());
          CopyInst copy1 = new CopyInst(predicate1, BooleanConstant.get(mCurrentProgram, false));
          CopyInst copy2 = new CopyInst(predicate1, rhsRes.getValue());

          JumpInst jum = new JumpInst((LocalVar) lhsRes.getValue());

          lhsRes.getEnd().setNext(0,jum);
          jum.setNext(0,copy1);
          jum.setNext(1,rhsRes.getStart());

          rhsRes.getEnd().setNext(0, copy2);
          copy1.setNext(0,nop);
          copy2.setNext(0,nop);
          return new InstPair(lhsRes.getStart(), nop, predicate1);
        }
        case "||": {
          Expression lhs = operation.getLeft();
          Expression rhs = operation.getRight();
          InstPair lhsRes = lhs.accept(this);
          InstPair rhsRes = rhs.accept(this);



          NopInst nop = new NopInst();
          LocalVar predicate1 = mCurrentFunction.getTempVar(new BoolType());
          CopyInst copy1 = new CopyInst(predicate1, BooleanConstant.get(mCurrentProgram, true));
          CopyInst copy2 = new CopyInst(predicate1, rhsRes.getValue());

          JumpInst jum = new JumpInst((LocalVar) lhsRes.getValue());
          lhsRes.getEnd().setNext(0,jum);
          jum.setNext(1,copy1);
          jum.setNext(0,rhsRes.getStart());

          rhsRes.getEnd().setNext(0, copy2);
          copy1.setNext(0,nop);
          copy2.setNext(0,nop);
          return new InstPair(lhsRes.getStart(), nop, predicate1);
        }
        case "!":{
          Expression lhs = operation.getLeft();
          InstPair lhsRes = lhs.accept(this);

          LocalVar destVar = mCurrentFunction.getTempVar(new BoolType());
          UnaryNotInst nop = new UnaryNotInst(destVar, (LocalVar) lhsRes.getValue());
          lhsRes.getEnd().setNext(0,nop);
          return new InstPair(lhsRes.getStart(), nop, destVar);
        }
      }
    return null;
  }


  private InstPair visit(Expression expression) {
    expression.accept(this);
    return null;
  }

  /**
   * It should compute the address into the array, do the load, and return the value in a LocalVar.
   */
  @Override
  public InstPair visit(ArrayAccess access) {
    /*Check if it is local or global by checking mCurrentLocalVarMap
    If local: return empty InstPair with LocalVar as value
    If global:  use AddressAt and LoadInst
    For all cases, InstPair val is LocalVar
    */

    InstPair offsetPair = access.getIndex().accept(this);
    AddressVar arrayVar = mCurrentFunction.getTempAddressVar(access.getType());
    AddressAt accessAddress = new AddressAt(arrayVar, access.getBase(), (LocalVar) offsetPair.getValue());
    LocalVar value = new LocalVar(access.getType());

    LoadInst load = new LoadInst(value, accessAddress.getDst());

    offsetPair.getEnd().setNext(0, accessAddress);
    accessAddress.setNext(0, load);

    return new InstPair(offsetPair.getStart(), load, value);

    }


  /**
   * Copy the literal into a tempVar
   */
  @Override
  public InstPair visit(LiteralBool literalBool) {
    var locationVar = mCurrentFunction.getTempVar(new BoolType());
    var temp = new CopyInst(locationVar, BooleanConstant.get(mCurrentProgram, literalBool.getValue()));
    return new InstPair(temp, locationVar);
  }

  /**
   * Copy the literal into a tempVar
   */
  @Override
  public InstPair visit(LiteralInt literalInt) {
    // wrong
    var intVal = IntegerConstant.get(mCurrentProgram,literalInt.getValue());
    var locationVar = mCurrentFunction.getTempVar(new IntType());
    var temp = new CopyInst(locationVar, IntegerConstant.get(mCurrentProgram, literalInt.getValue()));

    return new InstPair(temp, locationVar);
  }

  /**
   * Lower a Return.
   */
  @Override
  public InstPair visit(Return ret) {
    InstPair pairRet = ret.getValue().accept(this);
    var returned = new ReturnInst((LocalVar) pairRet.getValue());
    pairRet.getEnd().setNext(0, returned);
    var start = pairRet.getStart();
    return new InstPair(start, returned, null);
  }

  /**
   * Break Node
   */
  @Override
  public InstPair visit(Break brk) {
    Instruction head = new NopInst();
    Instruction finish = new NopInst();
    return new InstPair(head,finish,null);
  }

  /**
   * Implement If Then Else statements.
   */
  @Override
  public InstPair visit(IfElseBranch ifElseBranch) {
    InstPair then = ifElseBranch.getThenBlock().accept(this);
    InstPair condition = ifElseBranch.getCondition().accept(this);
    JumpInst jum = new JumpInst((LocalVar) condition.getValue());
    NopInst tog = new NopInst();
    condition.getEnd().setNext(0, jum);
    jum.setNext(1, then.getStart());
    then.getEnd().setNext(0,tog);
    if(ifElseBranch.getElseBlock().getChildren().size() == 0)
      jum.setNext(0,tog);
    else{
      InstPair elseBr = ifElseBranch.getElseBlock().accept(this);
      jum.setNext(0,elseBr.getStart());
      elseBr.getEnd().setNext(0,tog);
    }
    Instruction beg = condition.getStart();
    return new InstPair(beg,tog,null);

  }

  /**
   * Implement for loops.
   */
  @Override
  public InstPair visit(For loop) {
    NopInst exit = new NopInst();

    InstPair bod = loop.getBody().accept(this);
    InstPair condition = loop.getCond().accept(this);
    InstPair init = loop.getInit().accept(this);
    InstPair increment = loop.getIncrement().accept(this);
    init.getEnd().setNext(0,condition.getStart());
    JumpInst jum = new JumpInst((LocalVar) condition.getValue());
    condition.getEnd().setNext(0,jum);
    jum.setNext(0,exit);
    jum.setNext(1, bod.getStart());
    bod.getEnd().setNext(0,increment.getStart());
    increment.getEnd().setNext(0,condition.getStart());



    return new InstPair(init.getStart(),exit,null);
  }
}
