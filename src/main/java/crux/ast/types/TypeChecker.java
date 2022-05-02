package crux.ast.types;

import crux.ast.SymbolTable.Symbol;
import crux.ast.*;
import crux.ast.traversal.NullNodeVisitor;
import crux.ir.insts.BinaryOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class will associate types with the AST nodes from Stage 2
 */
public final class TypeChecker {
  private final ArrayList<String> errors = new ArrayList<>();

  public ArrayList<String> getErrors() {
    return errors;
  }

  public void check(DeclarationList ast) {
    var inferenceVisitor = new TypeInferenceVisitor();
    inferenceVisitor.visit(ast);
  }

  /**
   * Helper function, should be used to add error into the errors array
   */
  private void addTypeError(Node n, String message) {
    errors.add(String.format("TypeError%s[%s]", n.getPosition(), message));
  }

  /**
   * Helper function, should be used to record Types if the Type is an ErrorType then it will call
   * addTypeError
   */
  private void setNodeType(Node n, Type ty) {
    ((BaseNode) n).setType(ty);
    if (ty.getClass() == ErrorType.class) {
      var error = (ErrorType) ty;
      addTypeError(n, error.getMessage());
    }
  }

  /**
   * Helper to retrieve Type from the map
   */
  public Type getType(Node n) {
    return ((BaseNode) n).getType();
  }


  /**
   * This calls will visit each AST node and try to resolve it's type with the help of the
   * symbolTable.
   */
  private final class TypeInferenceVisitor extends NullNodeVisitor<Void> {
    private boolean lastStatementReturns ;
    private boolean hasBreak;
    private Type currentFunctionReturnType;
    private Symbol currentFunctionSymbol;

    @Override
    public Void visit(VarAccess vaccess) {
      //set the node type, should still return null
      setNodeType(vaccess,vaccess.getSymbol().getType());
      return null;
    }

    @Override
    public Void visit(ArrayDeclaration arrayDeclaration) {
      ArrayType array = (ArrayType) arrayDeclaration.getSymbol().getType();
      if(array.getBase().getClass() == IntType.class || array.getBase().getClass() == BoolType.class) {
        setNodeType(arrayDeclaration, array);
      }else{
        setNodeType(arrayDeclaration, new ErrorType(String.format("Array %s has invalid base type %s", arrayDeclaration.getSymbol().getName(),array.getBase())));
      }
      return null;
    }

    @Override
    public Void visit(Assignment assignment) {
      //get value and location, get the type then set the node
      Expression rha = assignment.getValue();
      Expression lha = assignment.getLocation();
      rha.accept(this);
      lha.accept(this);
      Type lh = getType(lha);
      Type rh = getType(rha);
      setNodeType(assignment,lh.assign(rh));
      lastStatementReturns = false;
      return null;
    }

    @Override
    public Void visit(Break brk) {
      hasBreak = true;
      lastStatementReturns = false;
      return null;
    }

    @Override
    public Void visit(Call call) {
      TypeList list = new TypeList();
      for(Expression exp : call.getArguments()){
        exp.accept(this);
        list.append(getType(exp));
      }
      Type callee = call.getCallee().getType();
      setNodeType(call,callee.call(list));
      return null;
    }

    @Override
    public Void visit(DeclarationList declarationList) {
      for (var declaration : declarationList.getChildren()){
        declaration.accept(this);
    }
      return null;
    }

    @Override
    public Void visit(FunctionDefinition functionDefinition) {
      currentFunctionSymbol = functionDefinition.getSymbol();
      currentFunctionReturnType = ((FuncType) functionDefinition.getSymbol().getType()).getRet();
      for (Symbol paramSym : functionDefinition.getParameters()) {
        if ( !(paramSym.getType().getClass() == IntType.class ||paramSym.getType().getClass() == BoolType.class )) {
          addTypeError(functionDefinition, String.format("Function not supported" , currentFunctionSymbol.getName(), paramSym.getName(), paramSym.getType()));
        }
      }

      visit(functionDefinition.getStatements());

      if (currentFunctionSymbol.getName().equals("main")) {
        if (currentFunctionReturnType.getClass() != VoidType.class) {
          addTypeError(functionDefinition, String.format("main should not return void",
                  currentFunctionReturnType));
        }
        if ( !(functionDefinition.getParameters().isEmpty())) {
          addTypeError(functionDefinition, String.format("main must not have parameters "));
        }
      }
      return null;

    }

    @Override
    public Void visit(IfElseBranch ifElseBranch) {
      ifElseBranch.getCondition().accept(this);
      Type conditionType = getType(ifElseBranch.getCondition());
      if (conditionType.getClass() != BoolType.class) {
        addTypeError(ifElseBranch, String.format(" if branch should have bool type",
                conditionType));
      }
      visit(ifElseBranch.getThenBlock());
      boolean thenBreak = hasBreak;
      boolean thenReturn = lastStatementReturns;
      hasBreak = false;
      lastStatementReturns = false;
      visit(ifElseBranch.getElseBlock());
      boolean elseBreak = hasBreak;
      boolean elseReturn = lastStatementReturns;

      hasBreak = thenBreak && elseBreak;

      lastStatementReturns = thenReturn && elseReturn;

      return null;
    }

    //unsure
    @Override
    public Void visit(ArrayAccess access) {
      Expression ind= access.getIndex();
      ind.accept(this);
      Type indType = getType(ind);
      setNodeType(access, indType);
      return null;
    }

    @Override
    public Void visit(LiteralBool literalBool) {
      setNodeType(literalBool, new BoolType());
      return null;
    }

    @Override
    public Void visit(LiteralInt literalInt) {
      setNodeType(literalInt, new IntType());
      return null;
    }

    @Override
    public Void visit(For forloop) {
      forloop.getCond().accept(this);
      if (getType(forloop.getCond()).getClass() == BoolType.class) {
        forloop.getInit().accept(this);
        forloop.getBody().accept(this);
        forloop.getIncrement().accept(this);
      }else {
        setNodeType(forloop, new ErrorType(String.format("For loop requires bool condition", getType(forloop.getCond()).toString())));
      }
      return null;

    }

    @Override
    public Void visit(OpExpr op) {
      Type result;
      if(op.getRight() != null){
        Expression rhs = op.getRight();
        Expression lhs = op.getLeft();
        lhs.accept(this);
        rhs.accept(this);
        Type rightType = getType(rhs);
        Type leftType = getType(lhs);
        if(op.getOp() == OpExpr.Operation.ADD){
          result = leftType.add(rightType);
        }else if (op.getOp() == OpExpr.Operation.SUB){
          result = leftType.sub(rightType);
        }else if (op.getOp() == OpExpr.Operation.MULT){
          result = leftType.mul(rightType);
        }else if (op.getOp() == OpExpr.Operation.DIV){
          result = leftType.div(rightType);
        }else if(op.getOp() == OpExpr.Operation.GT){
          result = leftType.compare(rightType);
        }else if(op.getOp() == OpExpr.Operation.LT){
          result = leftType.compare(rightType);
        }else if(op.getOp() == OpExpr.Operation.GE){
          result = leftType.compare(rightType);
        }else if(op.getOp() == OpExpr.Operation.LE){
          result = leftType.compare(rightType);
        }else if(op.getOp() == OpExpr.Operation.NE){
          result = leftType.compare(rightType);
        }else if(op.getOp() == OpExpr.Operation.EQ){
          result = leftType.compare(rightType);
        }else if(op.getOp() == OpExpr.Operation.LOGIC_OR){
          result = leftType.or(rightType);
        }else if(op.getOp() == OpExpr.Operation.LOGIC_AND){
          result = leftType.and(rightType);
        } else {
          result =leftType.assign(rightType);
        }
      }else{
        Expression lhs = op.getLeft();
        lhs.accept(this);
        Type leftType = getType(lhs);
        result = leftType.not();
      }
      setNodeType(op, result);
      return null;
    }

    @Override
    public Void visit(Return ret) {
      ret.getValue().accept(this);
      currentFunctionReturnType = getType(ret.getValue());
      lastStatementReturns = true;
      return null;
    }

    @Override
    public Void visit(StatementList statementList) {
      for(Node statement: statementList.getChildren())
        statement.accept(this);
      return null;
    }

    @Override
    public Void visit(VariableDeclaration variableDeclaration) {
      Type vartype = variableDeclaration.getSymbol().getType();
      if(vartype.equivalent(new VoidType())){
        setNodeType(variableDeclaration, new ErrorType("variable" + variableDeclaration.getSymbol().getName() + " has an invalid type"));
      }else{
        setNodeType(variableDeclaration, vartype);
      }
      return null;
    }
  }
}
