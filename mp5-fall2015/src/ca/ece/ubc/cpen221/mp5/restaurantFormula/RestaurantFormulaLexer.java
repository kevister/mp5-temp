// Generated from RestaurantFormula.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5.restaurantFormula;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RestaurantFormulaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IN=1, CATEGORY=2, NAME=3, RATING=4, PRICE=5, LPAREN=6, RPAREN=7, AND=8, 
		OR=9, RANGE=10, CHARS=11, STRING=12, WHITESPACE=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'"
	};
	public static final String[] ruleNames = {
		"IN", "CATEGORY", "NAME", "RATING", "PRICE", "LPAREN", "RPAREN", "AND", 
		"OR", "RANGE", "CHARS", "STRING", "WHITESPACE"
	};


	    // This method makes the lexer or parser stop running if it encounters
	    // invalid input and throw a RuntimeException.
	    public void reportErrorsAsExceptions() {
	        //removeErrorListeners();
	        
	        addErrorListener(new ExceptionThrowingErrorListener());
	    }
	    
	    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer,
	                Object offendingSymbol, int line, int charPositionInLine,
	                String msg, RecognitionException e) {
	            throw new RuntimeException(msg);
	        }
	    }


	public RestaurantFormulaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RestaurantFormula.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17j\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\5\13L\n\13\3\f\3\f\3\r\6\rQ\n\r\r\r\16\rR\3\r\6\rV\n\r\r\r"+
		"\16\rW\3\r\6\r[\n\r\r\r\16\r\\\7\r_\n\r\f\r\16\rb\13\r\3\16\6\16e\n\16"+
		"\r\16\16\16f\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\3\2\5\3\2\63\67\6\2$$\62;C\\c|\5\2\13\f\17\17\""+
		"\"o\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3"+
		"\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2"+
		"\2\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5 \3\2\2\2\7)\3\2\2\2\t.\3\2"+
		"\2\2\13\65\3\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21?\3\2\2\2\23B\3\2\2\2\25K"+
		"\3\2\2\2\27M\3\2\2\2\31P\3\2\2\2\33d\3\2\2\2\35\36\7k\2\2\36\37\7p\2\2"+
		"\37\4\3\2\2\2 !\7e\2\2!\"\7c\2\2\"#\7v\2\2#$\7g\2\2$%\7i\2\2%&\7q\2\2"+
		"&\'\7t\2\2\'(\7{\2\2(\6\3\2\2\2)*\7p\2\2*+\7c\2\2+,\7o\2\2,-\7g\2\2-\b"+
		"\3\2\2\2./\7t\2\2/\60\7c\2\2\60\61\7v\2\2\61\62\7k\2\2\62\63\7p\2\2\63"+
		"\64\7i\2\2\64\n\3\2\2\2\65\66\7r\2\2\66\67\7t\2\2\678\7k\2\289\7e\2\2"+
		"9:\7g\2\2:\f\3\2\2\2;<\7*\2\2<\16\3\2\2\2=>\7+\2\2>\20\3\2\2\2?@\7(\2"+
		"\2@A\7(\2\2A\22\3\2\2\2BC\7~\2\2CD\7~\2\2D\24\3\2\2\2EF\t\2\2\2FG\7\60"+
		"\2\2GH\7\60\2\2HI\3\2\2\2IL\t\2\2\2JL\t\2\2\2KE\3\2\2\2KJ\3\2\2\2L\26"+
		"\3\2\2\2MN\t\3\2\2N\30\3\2\2\2OQ\5\27\f\2PO\3\2\2\2QR\3\2\2\2RP\3\2\2"+
		"\2RS\3\2\2\2S`\3\2\2\2TV\7\"\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2"+
		"\2XZ\3\2\2\2Y[\5\27\f\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3"+
		"\2\2\2^U\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\32\3\2\2\2b`\3\2\2\2c"+
		"e\t\4\2\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\b\16\2\2"+
		"i\34\3\2\2\2\t\2KRW\\`f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}