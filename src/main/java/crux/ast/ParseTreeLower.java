package crux.ast;

import crux.ast.OpExpr.Operation;
import crux.pt.CruxBaseVisitor;
import crux.pt.CruxParser;
import crux.ast.types.*;
import crux.ast.SymbolTable.Symbol;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will convert the parse tree generated by ANTLR to AST It follows the visitor pattern
 * where declarations will be by DeclarationVisitor Class Statements will be resolved by
 * StatementVisitor Class Expressions will be resolved by ExpressionVisitor Class
 */

public final class ParseTreeLower {
  private final DeclarationVisitor declarationVisitor = new DeclarationVisitor();
  private final StatementVisitor statementVisitor = new StatementVisitor();
  private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();

  private final SymbolTable symTab;

  public ParseTreeLower(PrintStream err) {
    symTab = new SymbolTable(err);
  }

  private static Position makePosition(ParserRuleContext ctx) {
    var start = ctx.start;
    return new Position(start.getLine());
  }
  /**
   *
   * @return True if any errors
   */
  public boolean hasEncounteredError() {
    return symTab.hasEncounteredError();
  }


  /**
   * Lower top-level parse tree to AST
   * 
   * @return a {@link DeclarationList} object representing the top-level AST.
   */

  public DeclarationList lower(CruxParser.ProgramContext program) {
  Position position = makePosition(program);
  List<Declaration> tempList = new ArrayList<>();

  for (CruxParser.DeclarationContext ctx:
          program.declarationList().declaration()) tempList.add(ctx.accept(declarationVisitor));

  return new DeclarationList(position, tempList);
  }

  /**
   * Lower statement list by lower individual statement into AST.
   * 
   * @return a {@link StatementList} AST object.
   */

   private StatementList lower(CruxParser.StatementListContext statementList) {
     Position position = makePosition(statementList);
     List<Statement> s = new ArrayList<>();
     for(CruxParser.StatementContext content: statementList.statement()) s.add(content.accept(statementVisitor));
     return new StatementList(position,s);
   }


  /**
   * Similar to {@link #lower(CruxParser.StatementListContext)}, but handles symbol table as well.
   * 
   * @return a {@link StatementList} AST object.
   */

   private StatementList lower(CruxParser.StatementBlockContext statementBlock) {
     Position position = makePosition(statementBlock.statementList());
     List<Statement> statements = new ArrayList<>();
     symTab.enter();
     for(CruxParser.StatementContext sta : statementBlock.statementList().statement()) statements.add(sta.accept(statementVisitor));
     symTab.exit();
     return new StatementList(position,statements);
   }


  /**
   * A parse tree visitor to create AST nodes derived from {@link Declaration}
   */
  private final class DeclarationVisitor extends CruxBaseVisitor<Declaration> {
    /**
     * Visit a parse tree variable declaration and create an AST {@link VariableDeclaration}
     *
     * @return an AST {@link VariableDeclaration}
     */


    @Override
    public VariableDeclaration visitVariableDeclaration(CruxParser.VariableDeclarationContext ctx) {
      Position position = makePosition(ctx);
      String text = ctx.type().getText();
      //can't be null, they either return bool or an int
      Type type = new BoolType();
      if (text.equals("int")) {
        type = new IntType();
      }
      Symbol sym = symTab.add(position, ctx.Identifier().getText(), type);
      return new VariableDeclaration(position, sym);
    }


    /**
     * Visit a parse tree array declaration and creates an AST {@link ArrayDeclaration}
     *
     * @return an AST {@link ArrayDeclaration}
     */

    @Override
    public Declaration visitArrayDeclaration(CruxParser.ArrayDeclarationContext ctx) {

      Position position = makePosition(ctx);
      String text = ctx.type().getText();
      //can't be null, they either return bool or an int
      Type type = new BoolType();
      long elements = Long.parseLong(ctx.Integer().getText());
      if (text.equals("int")) {
        type = new IntType();
      }
      Symbol sym = symTab.add(position, ctx.Identifier().getText(), new ArrayType(elements, type));
      return new ArrayDeclaration(position, sym);

    }

    /**
     * Visit a parse tree function definition and create an AST {@link FunctionDefinition}
     *
     * @return an AST {@link FunctionDefinition}
     */


    @Override
    public Declaration visitFunctionDefinition(CruxParser.FunctionDefinitionContext ctx) {
      Position position = makePosition(ctx);
      String text = ctx.type().getText();
      //can't be null, functions can be void, int or bool  (returning wise)
      Type type = new IntType();
      if (text.equals("void")) {
        type = new VoidType();
      }
      if (text.equals("bool")) {
        type = new BoolType();
      }
      //Outer scope then inner scope
      TypeList listType = new TypeList();
      for (CruxParser.ParameterContext content : ctx.parameterList().parameter()) {
        String funcText = content.type().getText();
        Type inner = new IntType();
        if (funcText.equals("bool")) {
          type = new BoolType();
        }
        listType.append(inner);
      }
      Symbol funcSym = symTab.add(position, ctx.Identifier().getText(), new FuncType(listType, type));
      List<Symbol> listSym = new ArrayList<>();
      symTab.enter();

      for (CruxParser.ParameterContext contentInner : ctx.parameterList().parameter()) {
        String innerContent = contentInner.type().getText();
        Type innerType = new IntType();
        if (innerContent.equals("int")) {
          innerType = new IntType();
        }
        Symbol innerSym = symTab.add(position, contentInner.Identifier().getText(), innerType);
        listSym.add(innerSym);
      }
      StatementList func = lower(ctx.statementBlock());

      return new FunctionDefinition(position, funcSym, listSym, func);

    }
  }


  /**
   * A parse tree visitor to create AST nodes derived from {@link Statement}
   */

  private final class StatementVisitor extends CruxBaseVisitor<Statement> {
    /**
     * Visit a parse tree variable declaration and create an AST {@link VariableDeclaration}. Since
     * {@link VariableDeclaration} is both {@link Declaration} and {@link Statement}, we simply
     * delegate this to
     * {@link DeclarationVisitor#visitArrayDeclaration(CruxParser.ArrayDeclarationContext)} which we
     * implement earlier.
     * 
     * @return an AST {@link VariableDeclaration}
     */

      @Override
     public Statement visitVariableDeclaration(CruxParser.VariableDeclarationContext ctx) {
//        Position position = makePosition(ctx);
//        Symbol sym = new Symbol(ctx.Identifier())
        //instead of doing all of this we could return dec visitor
        return declarationVisitor.visitVariableDeclaration(ctx);
      }

    
    /**
     * Visit a parse tree assignment statement and create an AST {@link Assignment}
     * 
     * @return an AST {@link Assignment}
     */

      @Override
     public Statement visitAssignmentStatement(CruxParser.AssignmentStatementContext ctx) {
       Position position = makePosition(ctx);
       return new Assignment(position,ctx.designator().accept(expressionVisitor), ctx.expression0().accept(expressionVisitor));

    }

    /**
     * Visit a parse tree assignment nosemi statement and create an AST {@link Assignment}
     * 
     * @return an AST {@link Assignment}
     */


     @Override
     public Statement visitAssignmentStatementNoSemi(CruxParser.AssignmentStatementNoSemiContext ctx) {
       Position position = makePosition(ctx);
       return new Assignment(position, ctx.designator().accept(expressionVisitor),ctx.expression0().accept(expressionVisitor));
     }


    /**
     * Visit a parse tree call statement and create an AST {@link Call}. Since {@link Call} is both
     * {@link Expression} and {@link Statement}, we simply delegate this to
     * {@link ExpressionVisitor#visitCallExpression(CruxParser.CallExpressionContext)} that we will
     * implement later.
     * 
     * @return an AST {@link Call}
     */


      @Override
     public Statement visitCallStatement(CruxParser.CallStatementContext ctx) {
        return expressionVisitor.visitCallExpression(ctx.callExpression());
      }


    /**
     * Visit a parse tree if-else branch and create an AST {@link IfElseBranch}. The template code
     * shows partial implementations that visit the then block and else block recursively before
     * using those returned AST nodes to construct {@link IfElseBranch} object.
     * 
     * @return an AST {@link IfElseBranch}
     */

      @Override
      public Statement visitIfStatement(CruxParser.IfStatementContext ctx) {
        Position position = makePosition(ctx);
        if(ctx.statementBlock(1) == null) {
          return new IfElseBranch(position, ctx.expression0().accept(expressionVisitor), lower(ctx.statementBlock(0)), new StatementList(position, new ArrayList<>()));
        }
        return new IfElseBranch(position, ctx.expression0().accept(expressionVisitor), lower(ctx.statementBlock(0)), lower(ctx.statementBlock(1)));
      }


    /**
     * Visit a parse tree for loop and create an AST {@link For}. You'll going to use a similar
     * techniques as {@link #visitIfStatement(CruxParser.IfStatementContext)} to decompose this
     * construction.
     * 
     * @return an AST {@link For}
     */


      @Override
     public Statement visitForStatement(CruxParser.ForStatementContext ctx) {
        Position position = makePosition(ctx);
        Expression condition = ctx.expression0().accept(expressionVisitor);
        StatementList body = lower(ctx.statementBlock());
        Assignment init = new Assignment(position, ctx.assignmentStatement().designator().accept(expressionVisitor),ctx.assignmentStatement().expression0().accept(expressionVisitor));
        Assignment incr = new Assignment(position, ctx.assignmentStatementNoSemi().designator().accept(expressionVisitor),ctx.assignmentStatementNoSemi().expression0().accept(expressionVisitor));
        return new For(position,init,condition,incr,body);
      }


    /**
     * Visit a parse tree return statement and create an AST {@link Return}. Here we show a simple
     * example of how to lower a simple parse tree construction.
     * 
     * @return an AST {@link Return}
     */


     @Override
     public Statement visitReturnStatement(CruxParser.ReturnStatementContext ctx) {
       Position position = makePosition(ctx);
       return new Return(position,ctx.expression0().accept(expressionVisitor));
     }


    /**
     * Creates a Break node
     */

      @Override
     public Statement visitBreakStatement(CruxParser.BreakStatementContext ctx) {
        Position position = makePosition(ctx);
        return new Break(position);
      }

  }

  private final class ExpressionVisitor extends CruxBaseVisitor<Expression> {


    /**
     * Parse Expression0 to OpExpr Node Parsing the expression should be exactly as described in the
     * grammer
     */

    @Override
    public Expression visitExpression0(CruxParser.Expression0Context ctx) {
      CruxParser.Expression1Context lhsCtx = ctx.expression1(0);
      CruxParser.Op0Context op = ctx.op0();
      CruxParser.Expression1Context rhsCtx = ctx.expression1(1);
      Expression lhsExpression = lhsCtx.accept(expressionVisitor);
      if (op == null)
      {
        return lhsExpression;
      }
      else {
        Expression rhsExpression = rhsCtx.accept(expressionVisitor);
        String text = op.getText();
        Operation operation;
        if (text.equals(">=")) {
          operation = Operation.GE;
        } else if (text.equals("<=")) {
          operation = Operation.LE;
        } else if (text.equals("!=")) {
          operation = Operation.NE;
        } else if (text.equals("==")) {
          operation = Operation.EQ;
        } else if (text.equals(">")) {
          operation = Operation.GT;
        } else
          operation = Operation.LT;
        Position position = makePosition(ctx);
        return new OpExpr(position, operation, lhsExpression, rhsExpression);
      }
    }


    /**


    /**
     * Parse Expression1 to OpExpr Node Parsing the expression should be exactly as described in the
     * grammer
     */


     @Override
     public Expression visitExpression1(CruxParser.Expression1Context ctx) {
       if(ctx.op1() == null){
         return ctx.expression2().accept(expressionVisitor);
       }
       else{
         CruxParser.Op1Context opera1 = ctx.op1();
         Operation operation;
         String text = opera1.getText();
         Expression rhs = ctx.expression2().accept(expressionVisitor);
         Expression lhs = ctx.expression1().accept(expressionVisitor);
         if (text.equals("+")) {
           operation = Operation.ADD;
         } else if (text.equals("-") ){
           operation = Operation.SUB;}
         else{
           operation = Operation.LOGIC_OR;
         }
         Position position = makePosition(ctx);
         return new OpExpr(position,operation,lhs,rhs);
       }
     }

    /**
     * Parse Expression2 to OpExpr Node Parsing the expression should be exactly as described in the
     * grammer
     */

     @Override
     public Expression visitExpression2(CruxParser.Expression2Context ctx) {
       if(ctx.op2() == null){
         return ctx.expression3().accept(expressionVisitor);
       }
       else{
         CruxParser.Op2Context opera2 = ctx.op2();
         Operation operation;
         Position position = makePosition(ctx);
         String text = opera2.getText();
         Expression rhs = ctx.expression3().accept(expressionVisitor);
         Expression lhs = ctx.expression2().accept(expressionVisitor);
         if (text.equals("*")) {
           operation = Operation.MULT;
         } else if (text.equals("/") ){
           operation = Operation.DIV;}
         else{
           operation = Operation.LOGIC_AND;
         }
         return new OpExpr(position,operation,lhs,rhs);
       }
     }



    /**
     * Parse Expression3 to OpExpr Node Parsing the expression should be exactly as described in the
     * grammer
     */


     @Override
     public Expression visitExpression3(CruxParser.Expression3Context ctx) {
       if (ctx.expression3() != null)
       {
         Position position = makePosition(ctx);
         Expression lhs = ctx.expression3().accept(expressionVisitor);
         return new OpExpr(position,Operation.LOGIC_NOT,lhs,null);
       }
       else if (ctx.expression0() != null)
       {
         return ctx.expression0().accept(expressionVisitor);
       }
       else if (ctx.designator() != null)
       {
         return ctx.designator().accept(expressionVisitor);
       }
       else if (ctx.callExpression() != null)
       {
         return ctx.callExpression().accept(expressionVisitor);
       }
       else
       {
         return ctx.literal().accept(expressionVisitor);
       }
     }

    /**
     * Create a Call Node
     */

    @Override
    public Call visitCallExpression(CruxParser.CallExpressionContext ctx) {
      Position position = makePosition(ctx);

      Symbol callFunc = symTab.lookup(position, ctx.Identifier().getText());
      List<Expression> expList = new ArrayList<>();
      for (CruxParser.Expression0Context content : ctx.expressionList().expression0()) {
        expList.add(expressionVisitor.visitExpression0(content));
      }
      return new Call(position,callFunc,expList);
    }


    /**
     * visitDesignator will check for a name or ArrayAccess FYI it should account for the case when
     * the designator was dereferenced
     */

    @Override
    public Expression visitDesignator(CruxParser.DesignatorContext ctx) {
      Position position = makePosition(ctx);
      if (ctx.expression0() == null) {
        return new VarAccess(position, symTab.lookup(position, ctx.Identifier().getText()));
      } else {
        return new ArrayAccess(position, symTab.lookup(position, ctx.Identifier().getText()), ctx.expression0().accept(expressionVisitor));
      }
    }


    /**
     * Create an Literal Node
     */

    @Override
    public Expression visitLiteral(CruxParser.LiteralContext ctx) {
      Position position = makePosition(ctx);
      if (ctx.Integer() != null) {
        return new LiteralInt(position, Long.parseLong(ctx.Integer().toString()));
      }
      if (ctx.True() != null) {
        return new LiteralBool(position, Boolean.parseBoolean(ctx.True().toString()));
      } else {
        return new LiteralBool(position, Boolean.parseBoolean(ctx.False().toString()));
      }
    }
  }
}
