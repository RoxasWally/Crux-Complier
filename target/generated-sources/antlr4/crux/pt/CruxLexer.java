// Generated from crux\pt\Crux.g4 by ANTLR 4.7.2
package crux.pt;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CruxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SemiColon=1, Integer=2, True=3, False=4, AND=5, OR=6, NOT=7, If=8, Else=9, 
		Return=10, Break=11, For=12, Identifier=13, WhiteSpaces=14, Comment=15, 
		GreaterThanEqual=16, LessThanEqual=17, GreaterThan=18, LessThan=19, Equal=20, 
		NotEqual=21, OpenParen=22, ClosedParen=23, OpenBrack=24, ClosedBrack=25, 
		OpenBrace=26, ClosedBrace=27, Add=28, Subtract=29, Mult=30, Div=31, Comma=32, 
		Assignment=33;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SemiColon", "Integer", "True", "False", "AND", "OR", "NOT", "If", "Else", 
			"Return", "Break", "For", "Identifier", "WhiteSpaces", "Comment", "GreaterThanEqual", 
			"LessThanEqual", "GreaterThan", "LessThan", "Equal", "NotEqual", "OpenParen", 
			"ClosedParen", "OpenBrack", "ClosedBrack", "OpenBrace", "ClosedBrace", 
			"Add", "Subtract", "Mult", "Div", "Comma", "Assignment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", null, "'true'", "'false'", "'&&'", "'||'", "'!'", "'if'", 
			"'else'", "'return'", "'break'", "'for'", null, null, null, "'>='", "'<='", 
			"'>'", "'<'", "'=='", "'!='", "'('", "')'", "'['", "']'", "'{'", "'}'", 
			"'+'", "'-'", "'*'", "'/'", "','", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SemiColon", "Integer", "True", "False", "AND", "OR", "NOT", "If", 
			"Else", "Return", "Break", "For", "Identifier", "WhiteSpaces", "Comment", 
			"GreaterThanEqual", "LessThanEqual", "GreaterThan", "LessThan", "Equal", 
			"NotEqual", "OpenParen", "ClosedParen", "OpenBrack", "ClosedBrack", "OpenBrace", 
			"ClosedBrace", "Add", "Subtract", "Mult", "Div", "Comma", "Assignment"
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


	public CruxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Crux.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00be\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\5\3P\n\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\7\16\u0080\n\16\f\16"+
		"\16\16\u0083\13\16\3\17\6\17\u0086\n\17\r\17\16\17\u0087\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\7\20\u0090\n\20\f\20\16\20\u0093\13\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\2\2#\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#\3\2\b\3\2"+
		"\63;\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17"+
		"\2\u00c2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2"+
		"\5O\3\2\2\2\7Q\3\2\2\2\tV\3\2\2\2\13\\\3\2\2\2\r_\3\2\2\2\17b\3\2\2\2"+
		"\21d\3\2\2\2\23g\3\2\2\2\25l\3\2\2\2\27s\3\2\2\2\31y\3\2\2\2\33}\3\2\2"+
		"\2\35\u0085\3\2\2\2\37\u008b\3\2\2\2!\u0096\3\2\2\2#\u0099\3\2\2\2%\u009c"+
		"\3\2\2\2\'\u009e\3\2\2\2)\u00a0\3\2\2\2+\u00a3\3\2\2\2-\u00a6\3\2\2\2"+
		"/\u00a8\3\2\2\2\61\u00aa\3\2\2\2\63\u00ac\3\2\2\2\65\u00ae\3\2\2\2\67"+
		"\u00b0\3\2\2\29\u00b2\3\2\2\2;\u00b4\3\2\2\2=\u00b6\3\2\2\2?\u00b8\3\2"+
		"\2\2A\u00ba\3\2\2\2C\u00bc\3\2\2\2EF\7=\2\2F\4\3\2\2\2GP\7\62\2\2HL\t"+
		"\2\2\2IK\t\3\2\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MP\3\2\2\2NL\3"+
		"\2\2\2OG\3\2\2\2OH\3\2\2\2P\6\3\2\2\2QR\7v\2\2RS\7t\2\2ST\7w\2\2TU\7g"+
		"\2\2U\b\3\2\2\2VW\7h\2\2WX\7c\2\2XY\7n\2\2YZ\7u\2\2Z[\7g\2\2[\n\3\2\2"+
		"\2\\]\7(\2\2]^\7(\2\2^\f\3\2\2\2_`\7~\2\2`a\7~\2\2a\16\3\2\2\2bc\7#\2"+
		"\2c\20\3\2\2\2de\7k\2\2ef\7h\2\2f\22\3\2\2\2gh\7g\2\2hi\7n\2\2ij\7u\2"+
		"\2jk\7g\2\2k\24\3\2\2\2lm\7t\2\2mn\7g\2\2no\7v\2\2op\7w\2\2pq\7t\2\2q"+
		"r\7p\2\2r\26\3\2\2\2st\7d\2\2tu\7t\2\2uv\7g\2\2vw\7c\2\2wx\7m\2\2x\30"+
		"\3\2\2\2yz\7h\2\2z{\7q\2\2{|\7t\2\2|\32\3\2\2\2}\u0081\t\4\2\2~\u0080"+
		"\t\5\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\34\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0086\t\6\2\2\u0085"+
		"\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\u008a\b\17\2\2\u008a\36\3\2\2\2\u008b\u008c"+
		"\7\61\2\2\u008c\u008d\7\61\2\2\u008d\u0091\3\2\2\2\u008e\u0090\n\7\2\2"+
		"\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092"+
		"\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\b\20\2\2"+
		"\u0095 \3\2\2\2\u0096\u0097\7@\2\2\u0097\u0098\7?\2\2\u0098\"\3\2\2\2"+
		"\u0099\u009a\7>\2\2\u009a\u009b\7?\2\2\u009b$\3\2\2\2\u009c\u009d\7@\2"+
		"\2\u009d&\3\2\2\2\u009e\u009f\7>\2\2\u009f(\3\2\2\2\u00a0\u00a1\7?\2\2"+
		"\u00a1\u00a2\7?\2\2\u00a2*\3\2\2\2\u00a3\u00a4\7#\2\2\u00a4\u00a5\7?\2"+
		"\2\u00a5,\3\2\2\2\u00a6\u00a7\7*\2\2\u00a7.\3\2\2\2\u00a8\u00a9\7+\2\2"+
		"\u00a9\60\3\2\2\2\u00aa\u00ab\7]\2\2\u00ab\62\3\2\2\2\u00ac\u00ad\7_\2"+
		"\2\u00ad\64\3\2\2\2\u00ae\u00af\7}\2\2\u00af\66\3\2\2\2\u00b0\u00b1\7"+
		"\177\2\2\u00b18\3\2\2\2\u00b2\u00b3\7-\2\2\u00b3:\3\2\2\2\u00b4\u00b5"+
		"\7/\2\2\u00b5<\3\2\2\2\u00b6\u00b7\7,\2\2\u00b7>\3\2\2\2\u00b8\u00b9\7"+
		"\61\2\2\u00b9@\3\2\2\2\u00ba\u00bb\7.\2\2\u00bbB\3\2\2\2\u00bc\u00bd\7"+
		"?\2\2\u00bdD\3\2\2\2\b\2LO\u0081\u0087\u0091\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}