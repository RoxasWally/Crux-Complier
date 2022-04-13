// Generated from crux\pt\Crux.g4 by ANTLR 4.7.2
package crux.pt;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CruxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, SemiColon=13, Integer=14, True=15, False=16, 
		AND=17, OR=18, NOT=19, If=20, Else=21, Return=22, Break=23, Continue=24, 
		For=25, Identifier=26, WhiteSpaces=27, Comment=28, GreaterThanEqual=29, 
		LessThanEqual=30, GreaterThan=31, LessThan=32, Equal=33, NotEqual=34, 
		OpenParen=35, ClosedParen=36, OpenBrack=37, ClosedBrack=38, OpenBrace=39, 
		ClosedBrace=40, Add=41, Subtract=42, Mult=43, Div=44, Comma=45, Assignment=46;
	public static final int
		RULE_program = 0, RULE_declarationList = 1, RULE_declaration = 2, RULE_variableDeclaration = 3, 
		RULE_type = 4, RULE_arrayDeclaration = 5, RULE_functionDefinition = 6, 
		RULE_literal = 7, RULE_parameter = 8, RULE_parameterType = 9, RULE_comparisons = 10, 
		RULE_operationTwo = 11, RULE_operationThree = 12, RULE_expression0 = 13, 
		RULE_expression1 = 14, RULE_expression2 = 15, RULE_expression3 = 16, RULE_assignmentStatement = 17, 
		RULE_assignmentNoSemi = 18, RULE_callStatement = 19, RULE_statement = 20, 
		RULE_ifStatement = 21, RULE_forStatement = 22, RULE_breakStatement = 23, 
		RULE_continueStatement = 24, RULE_returnStatement = 25, RULE_designator = 26, 
		RULE_statements = 27, RULE_statementBlocks = 28, RULE_callExpression = 29, 
		RULE_expressionList = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "declarationList", "declaration", "variableDeclaration", "type", 
			"arrayDeclaration", "functionDefinition", "literal", "parameter", "parameterType", 
			"comparisons", "operationTwo", "operationThree", "expression0", "expression1", 
			"expression2", "expression3", "assignmentStatement", "assignmentNoSemi", 
			"callStatement", "statement", "ifStatement", "forStatement", "breakStatement", 
			"continueStatement", "returnStatement", "designator", "statements", "statementBlocks", 
			"callExpression", "expressionList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'LessThan'", "'GreaterThan'", "'LessThanEqual'", "'GreaterThanEqual'", 
			"'NotEqual'", "'Equal'", "'Add'", "'Subtract'", "'OR'", "'Mult'", "'Div'", 
			"'AND'", "';'", null, "'true'", "'false'", "'&&'", "'||'", "'!'", "'if'", 
			"'else'", "'return'", "'break'", "'continue'", "'for'", null, null, null, 
			"'>='", "'<='", "'>'", "'<'", "'=='", "'!='", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "','", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "SemiColon", "Integer", "True", "False", "AND", "OR", "NOT", "If", 
			"Else", "Return", "Break", "Continue", "For", "Identifier", "WhiteSpaces", 
			"Comment", "GreaterThanEqual", "LessThanEqual", "GreaterThan", "LessThan", 
			"Equal", "NotEqual", "OpenParen", "ClosedParen", "OpenBrack", "ClosedBrack", 
			"OpenBrace", "ClosedBrace", "Add", "Subtract", "Mult", "Div", "Comma", 
			"Assignment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Crux.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CruxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public DeclarationListContext declarationList() {
			return getRuleContext(DeclarationListContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CruxParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			declarationList();
			setState(63);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationListContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public DeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterDeclarationList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitDeclarationList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitDeclarationList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationListContext declarationList() throws RecognitionException {
		DeclarationListContext _localctx = new DeclarationListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declarationList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Identifier) {
				{
				{
				setState(65);
				declaration();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ArrayDeclarationContext arrayDeclaration() {
			return getRuleContext(ArrayDeclarationContext.class,0);
		}
		public FunctionDefinitionContext functionDefinition() {
			return getRuleContext(FunctionDefinitionContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaration);
		try {
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				arrayDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				functionDefinition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			type();
			setState(77);
			match(Identifier);
			setState(78);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode OpenBrack() { return getToken(CruxParser.OpenBrack, 0); }
		public TerminalNode Integer() { return getToken(CruxParser.Integer, 0); }
		public TerminalNode ClosedBrack() { return getToken(CruxParser.ClosedBrack, 0); }
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public ArrayDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterArrayDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitArrayDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitArrayDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayDeclarationContext arrayDeclaration() throws RecognitionException {
		ArrayDeclarationContext _localctx = new ArrayDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_arrayDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			type();
			setState(83);
			match(Identifier);
			setState(84);
			match(OpenBrack);
			setState(85);
			match(Integer);
			setState(86);
			match(ClosedBrack);
			setState(87);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode OpenParen() { return getToken(CruxParser.OpenParen, 0); }
		public ParameterTypeContext parameterType() {
			return getRuleContext(ParameterTypeContext.class,0);
		}
		public TerminalNode ClosedParen() { return getToken(CruxParser.ClosedParen, 0); }
		public StatementBlocksContext statementBlocks() {
			return getRuleContext(StatementBlocksContext.class,0);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterFunctionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitFunctionDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitFunctionDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			type();
			setState(90);
			match(Identifier);
			setState(91);
			match(OpenParen);
			setState(92);
			parameterType();
			setState(93);
			match(ClosedParen);
			setState(94);
			statementBlocks();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode Integer() { return getToken(CruxParser.Integer, 0); }
		public TerminalNode True() { return getToken(CruxParser.True, 0); }
		public TerminalNode False() { return getToken(CruxParser.False, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Integer) | (1L << True) | (1L << False))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			type();
			setState(99);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterTypeContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CruxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CruxParser.Comma, i);
		}
		public ParameterTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterParameterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitParameterType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitParameterType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterTypeContext parameterType() throws RecognitionException {
		ParameterTypeContext _localctx = new ParameterTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameterType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(101);
				parameter();
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(102);
					match(Comma);
					setState(103);
					parameter();
					}
					}
					setState(108);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonsContext extends ParserRuleContext {
		public ComparisonsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisons; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterComparisons(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitComparisons(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitComparisons(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonsContext comparisons() throws RecognitionException {
		ComparisonsContext _localctx = new ComparisonsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_comparisons);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationTwoContext extends ParserRuleContext {
		public OperationTwoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationTwo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterOperationTwo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitOperationTwo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitOperationTwo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationTwoContext operationTwo() throws RecognitionException {
		OperationTwoContext _localctx = new OperationTwoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_operationTwo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationThreeContext extends ParserRuleContext {
		public OperationThreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationThree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterOperationThree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitOperationThree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitOperationThree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationThreeContext operationThree() throws RecognitionException {
		OperationThreeContext _localctx = new OperationThreeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operationThree);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression0Context extends ParserRuleContext {
		public List<Expression1Context> expression1() {
			return getRuleContexts(Expression1Context.class);
		}
		public Expression1Context expression1(int i) {
			return getRuleContext(Expression1Context.class,i);
		}
		public ComparisonsContext comparisons() {
			return getRuleContext(ComparisonsContext.class,0);
		}
		public Expression0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression0; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpression0(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpression0(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpression0(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression0Context expression0() throws RecognitionException {
		Expression0Context _localctx = new Expression0Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_expression0);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			expression1();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) {
				{
				setState(118);
				comparisons();
				setState(119);
				expression1();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression1Context extends ParserRuleContext {
		public List<Expression2Context> expression2() {
			return getRuleContexts(Expression2Context.class);
		}
		public Expression2Context expression2(int i) {
			return getRuleContext(Expression2Context.class,i);
		}
		public OperationTwoContext operationTwo() {
			return getRuleContext(OperationTwoContext.class,0);
		}
		public Expression1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpression1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpression1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpression1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression1Context expression1() throws RecognitionException {
		Expression1Context _localctx = new Expression1Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_expression1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			expression2();
			setState(124);
			operationTwo();
			setState(125);
			expression2();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression2Context extends ParserRuleContext {
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public OperationThreeContext operationThree() {
			return getRuleContext(OperationThreeContext.class,0);
		}
		public Expression2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpression2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpression2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression2Context expression2() throws RecognitionException {
		Expression2Context _localctx = new Expression2Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_expression2);
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Integer:
			case True:
			case False:
			case NOT:
			case Identifier:
			case OpenParen:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				expression3();
				}
				break;
			case T__9:
			case T__10:
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				operationThree();
				setState(129);
				expression3();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression3Context extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(CruxParser.NOT, 0); }
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public TerminalNode OpenParen() { return getToken(CruxParser.OpenParen, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode ClosedParen() { return getToken(CruxParser.ClosedParen, 0); }
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public CallExpressionContext callExpression() {
			return getRuleContext(CallExpressionContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Expression3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpression3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpression3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpression3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression3Context expression3() throws RecognitionException {
		Expression3Context _localctx = new Expression3Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_expression3);
		try {
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				match(NOT);
				setState(134);
				expression3();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				match(OpenParen);
				setState(136);
				expression0();
				setState(137);
				match(ClosedParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				designator();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				callExpression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(141);
				literal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentStatementContext extends ParserRuleContext {
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public TerminalNode Assignment() { return getToken(CruxParser.Assignment, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			designator();
			setState(145);
			match(Assignment);
			setState(146);
			expression0();
			setState(147);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentNoSemiContext extends ParserRuleContext {
		public DesignatorContext designator() {
			return getRuleContext(DesignatorContext.class,0);
		}
		public TerminalNode Assignment() { return getToken(CruxParser.Assignment, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public AssignmentNoSemiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentNoSemi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterAssignmentNoSemi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitAssignmentNoSemi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitAssignmentNoSemi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentNoSemiContext assignmentNoSemi() throws RecognitionException {
		AssignmentNoSemiContext _localctx = new AssignmentNoSemiContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_assignmentNoSemi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			designator();
			setState(150);
			match(Assignment);
			setState(151);
			expression0();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallStatementContext extends ParserRuleContext {
		public CallExpressionContext callExpression() {
			return getRuleContext(CallExpressionContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public CallStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterCallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitCallStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallStatementContext callStatement() throws RecognitionException {
		CallStatementContext _localctx = new CallStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_callStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			callExpression();
			setState(154);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public CallStatementContext callStatement() {
			return getRuleContext(CallStatementContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public AssignmentNoSemiContext assignmentNoSemi() {
			return getRuleContext(AssignmentNoSemiContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_statement);
		try {
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				returnStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(158);
				callStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(159);
				assignmentStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(160);
				assignmentNoSemi();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(161);
				breakStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(162);
				ifStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(163);
				continueStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(164);
				forStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(CruxParser.If, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public List<StatementBlocksContext> statementBlocks() {
			return getRuleContexts(StatementBlocksContext.class);
		}
		public StatementBlocksContext statementBlocks(int i) {
			return getRuleContext(StatementBlocksContext.class,i);
		}
		public TerminalNode Else() { return getToken(CruxParser.Else, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(If);
			setState(168);
			expression0();
			setState(169);
			statementBlocks();
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(170);
				match(Else);
				setState(171);
				statementBlocks();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(CruxParser.For, 0); }
		public TerminalNode OpenParen() { return getToken(CruxParser.OpenParen, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public AssignmentNoSemiContext assignmentNoSemi() {
			return getRuleContext(AssignmentNoSemiContext.class,0);
		}
		public TerminalNode ClosedParen() { return getToken(CruxParser.ClosedParen, 0); }
		public StatementBlocksContext statementBlocks() {
			return getRuleContext(StatementBlocksContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(For);
			setState(175);
			match(OpenParen);
			setState(176);
			assignmentStatement();
			setState(177);
			expression0();
			setState(178);
			match(SemiColon);
			setState(179);
			assignmentNoSemi();
			setState(180);
			match(ClosedParen);
			setState(181);
			statementBlocks();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(CruxParser.Break, 0); }
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(Break);
			setState(184);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(CruxParser.Continue, 0); }
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(Continue);
			setState(187);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(CruxParser.Return, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode SemiColon() { return getToken(CruxParser.SemiColon, 0); }
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(Return);
			setState(190);
			expression0();
			setState(191);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DesignatorContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode OpenBrack() { return getToken(CruxParser.OpenBrack, 0); }
		public Expression0Context expression0() {
			return getRuleContext(Expression0Context.class,0);
		}
		public TerminalNode ClosedBrack() { return getToken(CruxParser.ClosedBrack, 0); }
		public DesignatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_designator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterDesignator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitDesignator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitDesignator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DesignatorContext designator() throws RecognitionException {
		DesignatorContext _localctx = new DesignatorContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_designator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(Identifier);
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OpenBrack) {
				{
				setState(194);
				match(OpenBrack);
				setState(195);
				expression0();
				setState(196);
				match(ClosedBrack);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << If) | (1L << Return) | (1L << Break) | (1L << Continue) | (1L << For) | (1L << Identifier))) != 0)) {
				{
				{
				setState(200);
				statement();
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementBlocksContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(CruxParser.OpenBrace, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode ClosedBrace() { return getToken(CruxParser.ClosedBrace, 0); }
		public StatementBlocksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementBlocks; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterStatementBlocks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitStatementBlocks(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitStatementBlocks(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementBlocksContext statementBlocks() throws RecognitionException {
		StatementBlocksContext _localctx = new StatementBlocksContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_statementBlocks);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(OpenBrace);
			setState(207);
			statements();
			setState(208);
			match(ClosedBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallExpressionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(CruxParser.Identifier, 0); }
		public TerminalNode OpenParen() { return getToken(CruxParser.OpenParen, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode ClosedParen() { return getToken(CruxParser.ClosedParen, 0); }
		public CallExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallExpressionContext callExpression() throws RecognitionException {
		CallExpressionContext _localctx = new CallExpressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_callExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(Identifier);
			setState(211);
			match(OpenParen);
			setState(212);
			expressionList();
			setState(213);
			match(ClosedParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<Expression0Context> expression0() {
			return getRuleContexts(Expression0Context.class);
		}
		public Expression0Context expression0(int i) {
			return getRuleContext(Expression0Context.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(CruxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(CruxParser.Comma, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CruxListener ) ((CruxListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CruxVisitor ) return ((CruxVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << Integer) | (1L << True) | (1L << False) | (1L << NOT) | (1L << Identifier) | (1L << OpenParen))) != 0)) {
				{
				setState(215);
				expression0();
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(216);
					match(Comma);
					setState(217);
					expression0();
					}
					}
					setState(222);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\60\u00e4\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\3\7\3E\n\3\f\3\16\3H\13\3\3\4\3\4\3\4\5\4M\n\4\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\7\13k\n\13\f\13\16\13n\13\13\5\13p\n"+
		"\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\5\17|\n\17\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\5\21\u0086\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\5\22\u0091\n\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\5\26\u00a8\n\26\3\27\3\27\3\27\3\27\3\27\5\27\u00af\n\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\5\34\u00c9\n\34\3\35\7\35\u00cc"+
		"\n\35\f\35\16\35\u00cf\13\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3"+
		"\37\3 \3 \3 \7 \u00dd\n \f \16 \u00e0\13 \5 \u00e2\n \3 \2\2!\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\6\3\2\20\22"+
		"\3\2\3\b\3\2\t\13\3\2\f\16\2\u00dc\2@\3\2\2\2\4F\3\2\2\2\6L\3\2\2\2\b"+
		"N\3\2\2\2\nR\3\2\2\2\fT\3\2\2\2\16[\3\2\2\2\20b\3\2\2\2\22d\3\2\2\2\24"+
		"o\3\2\2\2\26q\3\2\2\2\30s\3\2\2\2\32u\3\2\2\2\34w\3\2\2\2\36}\3\2\2\2"+
		" \u0085\3\2\2\2\"\u0090\3\2\2\2$\u0092\3\2\2\2&\u0097\3\2\2\2(\u009b\3"+
		"\2\2\2*\u00a7\3\2\2\2,\u00a9\3\2\2\2.\u00b0\3\2\2\2\60\u00b9\3\2\2\2\62"+
		"\u00bc\3\2\2\2\64\u00bf\3\2\2\2\66\u00c3\3\2\2\28\u00cd\3\2\2\2:\u00d0"+
		"\3\2\2\2<\u00d4\3\2\2\2>\u00e1\3\2\2\2@A\5\4\3\2AB\7\2\2\3B\3\3\2\2\2"+
		"CE\5\6\4\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\5\3\2\2\2HF\3\2\2"+
		"\2IM\5\b\5\2JM\5\f\7\2KM\5\16\b\2LI\3\2\2\2LJ\3\2\2\2LK\3\2\2\2M\7\3\2"+
		"\2\2NO\5\n\6\2OP\7\34\2\2PQ\7\17\2\2Q\t\3\2\2\2RS\7\34\2\2S\13\3\2\2\2"+
		"TU\5\n\6\2UV\7\34\2\2VW\7\'\2\2WX\7\20\2\2XY\7(\2\2YZ\7\17\2\2Z\r\3\2"+
		"\2\2[\\\5\n\6\2\\]\7\34\2\2]^\7%\2\2^_\5\24\13\2_`\7&\2\2`a\5:\36\2a\17"+
		"\3\2\2\2bc\t\2\2\2c\21\3\2\2\2de\5\n\6\2ef\7\34\2\2f\23\3\2\2\2gl\5\22"+
		"\n\2hi\7/\2\2ik\5\22\n\2jh\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mp\3\2"+
		"\2\2nl\3\2\2\2og\3\2\2\2op\3\2\2\2p\25\3\2\2\2qr\t\3\2\2r\27\3\2\2\2s"+
		"t\t\4\2\2t\31\3\2\2\2uv\t\5\2\2v\33\3\2\2\2w{\5\36\20\2xy\5\26\f\2yz\5"+
		"\36\20\2z|\3\2\2\2{x\3\2\2\2{|\3\2\2\2|\35\3\2\2\2}~\5 \21\2~\177\5\30"+
		"\r\2\177\u0080\5 \21\2\u0080\37\3\2\2\2\u0081\u0086\5\"\22\2\u0082\u0083"+
		"\5\32\16\2\u0083\u0084\5\"\22\2\u0084\u0086\3\2\2\2\u0085\u0081\3\2\2"+
		"\2\u0085\u0082\3\2\2\2\u0086!\3\2\2\2\u0087\u0088\7\25\2\2\u0088\u0091"+
		"\5\"\22\2\u0089\u008a\7%\2\2\u008a\u008b\5\34\17\2\u008b\u008c\7&\2\2"+
		"\u008c\u0091\3\2\2\2\u008d\u0091\5\66\34\2\u008e\u0091\5<\37\2\u008f\u0091"+
		"\5\20\t\2\u0090\u0087\3\2\2\2\u0090\u0089\3\2\2\2\u0090\u008d\3\2\2\2"+
		"\u0090\u008e\3\2\2\2\u0090\u008f\3\2\2\2\u0091#\3\2\2\2\u0092\u0093\5"+
		"\66\34\2\u0093\u0094\7\60\2\2\u0094\u0095\5\34\17\2\u0095\u0096\7\17\2"+
		"\2\u0096%\3\2\2\2\u0097\u0098\5\66\34\2\u0098\u0099\7\60\2\2\u0099\u009a"+
		"\5\34\17\2\u009a\'\3\2\2\2\u009b\u009c\5<\37\2\u009c\u009d\7\17\2\2\u009d"+
		")\3\2\2\2\u009e\u00a8\5\b\5\2\u009f\u00a8\5\64\33\2\u00a0\u00a8\5(\25"+
		"\2\u00a1\u00a8\5$\23\2\u00a2\u00a8\5&\24\2\u00a3\u00a8\5\60\31\2\u00a4"+
		"\u00a8\5,\27\2\u00a5\u00a8\5\62\32\2\u00a6\u00a8\5.\30\2\u00a7\u009e\3"+
		"\2\2\2\u00a7\u009f\3\2\2\2\u00a7\u00a0\3\2\2\2\u00a7\u00a1\3\2\2\2\u00a7"+
		"\u00a2\3\2\2\2\u00a7\u00a3\3\2\2\2\u00a7\u00a4\3\2\2\2\u00a7\u00a5\3\2"+
		"\2\2\u00a7\u00a6\3\2\2\2\u00a8+\3\2\2\2\u00a9\u00aa\7\26\2\2\u00aa\u00ab"+
		"\5\34\17\2\u00ab\u00ae\5:\36\2\u00ac\u00ad\7\27\2\2\u00ad\u00af\5:\36"+
		"\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af-\3\2\2\2\u00b0\u00b1"+
		"\7\33\2\2\u00b1\u00b2\7%\2\2\u00b2\u00b3\5$\23\2\u00b3\u00b4\5\34\17\2"+
		"\u00b4\u00b5\7\17\2\2\u00b5\u00b6\5&\24\2\u00b6\u00b7\7&\2\2\u00b7\u00b8"+
		"\5:\36\2\u00b8/\3\2\2\2\u00b9\u00ba\7\31\2\2\u00ba\u00bb\7\17\2\2\u00bb"+
		"\61\3\2\2\2\u00bc\u00bd\7\32\2\2\u00bd\u00be\7\17\2\2\u00be\63\3\2\2\2"+
		"\u00bf\u00c0\7\30\2\2\u00c0\u00c1\5\34\17\2\u00c1\u00c2\7\17\2\2\u00c2"+
		"\65\3\2\2\2\u00c3\u00c8\7\34\2\2\u00c4\u00c5\7\'\2\2\u00c5\u00c6\5\34"+
		"\17\2\u00c6\u00c7\7(\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c4\3\2\2\2\u00c8"+
		"\u00c9\3\2\2\2\u00c9\67\3\2\2\2\u00ca\u00cc\5*\26\2\u00cb\u00ca\3\2\2"+
		"\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce9"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\7)\2\2\u00d1\u00d2\58\35\2\u00d2"+
		"\u00d3\7*\2\2\u00d3;\3\2\2\2\u00d4\u00d5\7\34\2\2\u00d5\u00d6\7%\2\2\u00d6"+
		"\u00d7\5> \2\u00d7\u00d8\7&\2\2\u00d8=\3\2\2\2\u00d9\u00de\5\34\17\2\u00da"+
		"\u00db\7/\2\2\u00db\u00dd\5\34\17\2\u00dc\u00da\3\2\2\2\u00dd\u00e0\3"+
		"\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e1\u00d9\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2?\3\2\2\2"+
		"\17FLlo{\u0085\u0090\u00a7\u00ae\u00c8\u00cd\u00de\u00e1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}