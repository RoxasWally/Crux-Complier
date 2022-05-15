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
  private Instruction head = new NopInst();
  private Instruction tail = new NopInst();
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
    return null;
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
    Symbol mySym = name.getSymbol();
    if(mCurrentLocalVarMap.containsKey(mySym)){
      Variable temp = mCurrentLocalVarMap.get(mySym);
      return new InstPair(new NopInst(), temp);
    }else{
      AddressVar address = mCurrentFunction.getTempAddressVar(mySym.getType());
      AddressAt addressLocation = new AddressAt(address, mySym);
      return new InstPair(addressLocation,address);
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
    Expression lhs = assignment.getValue();
    Expression rhs = assignment.getLocation();
    InstPair lhsResult = lhs.accept(this);
    InstPair rhsResult = rhs.accept(this);
    lhsResult.getEnd().setNext(0,rhsResult.getStart());
    if(rhsResult.getValue().getClass().equals(LocalVar.class) && lhsResult.getValue().getClass().equals(LocalVar.class)){
      CopyInst copy = new CopyInst((LocalVar) lhsResult.getValue(),rhsResult.getValue());
      rhsResult.getEnd().setNext(0, copy);
      return new InstPair(lhsResult.getStart(), copy);
    }
    if(rhsResult.getValue().getClass().equals(LocalVar.class) && lhsResult.getValue().getClass().equals(AddressVar.class)){
      StoreInst store = new StoreInst((LocalVar) rhsResult.getValue(),(AddressVar) lhsResult.getValue());
      rhsResult.getEnd().setNext(0, store);
      return new InstPair(lhsResult.getStart(), store);
    }
    //still needs to return null if those conditions aren't met but lhs and rhs will still be accepted
    return null;
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
    Instruction first = null;
    Instruction last = null;
    List<LocalVar> argVal = new ArrayList<LocalVar>();
    for (Expression arg : call.getArguments()){
      InstPair result = arg.accept(this);
      if(first == null){
        first = result.getStart();
        last = result.getEnd();
      }else{
        last.setNext(0, result.getStart());
        last = result.getEnd();
      }
      LocalVar temp = mCurrentFunction.getTempVar(result.getValue().getType());
      if(result.getValue().getClass().equals(AddressVar.class)){
        var loadInst = new LoadInst(temp,(AddressVar)result.getValue());
        last.setNext(0,loadInst);
        last = loadInst;
      } else {
        temp = (LocalVar) result.getValue();
      }
      argVal.add(temp);
    }
    FuncType funcType = (FuncType) call.getCallee().getType();
    if(funcType.getRet().getClass().equals(VoidType.class)){
      var callinst = new CallInst(call.getCallee(), argVal);
      if(first == null){
        return new InstPair(callinst, callinst, null);
      }
      last.setNext(0, callinst);
      return new InstPair(first,callinst,null);
    }else {
      LocalVar tempVar = mCurrentFunction.getTempVar(funcType.getRet());
      var callinst = new CallInst(tempVar, call.getCallee(), argVal);
      if(first == null){
        return new InstPair(callinst,callinst,null);
      }
      last.setNext(0, callinst);
      return new InstPair(first,callinst,tempVar);
    }
  }

  /**
   * Handle operations like arithmetics and comparisons. Also handle logical operations (and,
   * or, not).
   */
  @Override
  public InstPair visit(OpExpr operation) {
    return null;
  }

  private InstPair visit(Expression expression) {
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
    InstPair inde = access.getIndex().accept(this);
    Symbol mySym = access.getBase();
    AddressVar address = mCurrentFunction.getTempAddressVar(((ArrayType)access.getBase().getType()).getBase());
    AddressAt addressLocation = new AddressAt(address,mySym);

    return new InstPair(addressLocation, address);
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

    return new InstPair(head, new NopInst(),null);
  }

  /**
   * Implement If Then Else statements.
   */
  @Override
  public InstPair visit(IfElseBranch ifElseBranch) {
    return null;
  }

  /**
   * Implement for loops.
   */
  @Override
  public InstPair visit(For loop) {
    return null;
  }
}
