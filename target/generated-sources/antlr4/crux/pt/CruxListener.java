// Generated from crux\pt\Crux.g4 by ANTLR 4.7.2
package crux.pt;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CruxParser}.
 */
public interface CruxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CruxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CruxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CruxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationList(CruxParser.DeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationList(CruxParser.DeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(CruxParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(CruxParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(CruxParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(CruxParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(CruxParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(CruxParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclaration(CruxParser.ArrayDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclaration(CruxParser.ArrayDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(CruxParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(CruxParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CruxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CruxParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(CruxParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(CruxParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void enterParameterType(CruxParser.ParameterTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void exitParameterType(CruxParser.ParameterTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#comparisons}.
	 * @param ctx the parse tree
	 */
	void enterComparisons(CruxParser.ComparisonsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#comparisons}.
	 * @param ctx the parse tree
	 */
	void exitComparisons(CruxParser.ComparisonsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#operationTwo}.
	 * @param ctx the parse tree
	 */
	void enterOperationTwo(CruxParser.OperationTwoContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#operationTwo}.
	 * @param ctx the parse tree
	 */
	void exitOperationTwo(CruxParser.OperationTwoContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#operationThree}.
	 * @param ctx the parse tree
	 */
	void enterOperationThree(CruxParser.OperationThreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#operationThree}.
	 * @param ctx the parse tree
	 */
	void exitOperationThree(CruxParser.OperationThreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#expression0}.
	 * @param ctx the parse tree
	 */
	void enterExpression0(CruxParser.Expression0Context ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#expression0}.
	 * @param ctx the parse tree
	 */
	void exitExpression0(CruxParser.Expression0Context ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#expression1}.
	 * @param ctx the parse tree
	 */
	void enterExpression1(CruxParser.Expression1Context ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#expression1}.
	 * @param ctx the parse tree
	 */
	void exitExpression1(CruxParser.Expression1Context ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#expression2}.
	 * @param ctx the parse tree
	 */
	void enterExpression2(CruxParser.Expression2Context ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#expression2}.
	 * @param ctx the parse tree
	 */
	void exitExpression2(CruxParser.Expression2Context ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#expression3}.
	 * @param ctx the parse tree
	 */
	void enterExpression3(CruxParser.Expression3Context ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#expression3}.
	 * @param ctx the parse tree
	 */
	void exitExpression3(CruxParser.Expression3Context ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(CruxParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(CruxParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#assignmentNoSemi}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentNoSemi(CruxParser.AssignmentNoSemiContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#assignmentNoSemi}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentNoSemi(CruxParser.AssignmentNoSemiContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#callStatement}.
	 * @param ctx the parse tree
	 */
	void enterCallStatement(CruxParser.CallStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#callStatement}.
	 * @param ctx the parse tree
	 */
	void exitCallStatement(CruxParser.CallStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CruxParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CruxParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(CruxParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(CruxParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(CruxParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(CruxParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(CruxParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(CruxParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(CruxParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(CruxParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(CruxParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(CruxParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#designator}.
	 * @param ctx the parse tree
	 */
	void enterDesignator(CruxParser.DesignatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#designator}.
	 * @param ctx the parse tree
	 */
	void exitDesignator(CruxParser.DesignatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(CruxParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(CruxParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#statementBlocks}.
	 * @param ctx the parse tree
	 */
	void enterStatementBlocks(CruxParser.StatementBlocksContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#statementBlocks}.
	 * @param ctx the parse tree
	 */
	void exitStatementBlocks(CruxParser.StatementBlocksContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void enterCallExpression(CruxParser.CallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#callExpression}.
	 * @param ctx the parse tree
	 */
	void exitCallExpression(CruxParser.CallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CruxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(CruxParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CruxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(CruxParser.ExpressionListContext ctx);
}