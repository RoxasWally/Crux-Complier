package crux.ast.types;

import crux.ast.SymbolTable.Symbol;
import crux.ast.*;
import crux.ast.traversal.NullNodeVisitor;

import java.util.ArrayList;
import java.util.HashMap;
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
    private boolean lastStatementReturns;
    private boolean hasBreak;
    private Type currentFunctionReturnType;

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
        setNodeType(arrayDeclaration, new ErrorType(String.format("Array %s has invalid base type %s", arrayDeclaration.getSymbol().getName(),array.getBase().toString())));
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
      return null;
    }

    @Override
    public Void visit(IfElseBranch ifElseBranch) {
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
      return null;
    }

    @Override
    public Void visit(OpExpr op) {
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
