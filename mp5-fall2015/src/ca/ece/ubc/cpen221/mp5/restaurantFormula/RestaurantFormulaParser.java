// Generated from RestaurantFormula.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5.restaurantFormula;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RestaurantFormulaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IN=1, CATEGORY=2, NAME=3, RATING=4, PRICE=5, LPAREN=6, RPAREN=7, AND=8, 
		OR=9, RANGE=10, CHARS=11, STRING=12, WHITESPACE=13;
	public static final String[] tokenNames = {
		"<INVALID>", "'in'", "'category'", "'name'", "'rating'", "'price'", "'('", 
		"')'", "'&&'", "'||'", "RANGE", "CHARS", "STRING", "WHITESPACE"
	};
	public static final int
		RULE_orexpr = 0, RULE_andexpr = 1, RULE_atom = 2, RULE_in = 3, RULE_category = 4, 
		RULE_name = 5, RULE_rating = 6, RULE_price = 7;
	public static final String[] ruleNames = {
		"orexpr", "andexpr", "atom", "in", "category", "name", "rating", "price"
	};

	@Override
	public String getGrammarFileName() { return "RestaurantFormula.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public RestaurantFormulaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class OrexprContext extends ParserRuleContext {
		public AndexprContext andexpr(int i) {
			return getRuleContext(AndexprContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(RestaurantFormulaParser.OR); }
		public List<AndexprContext> andexpr() {
			return getRuleContexts(AndexprContext.class);
		}
		public TerminalNode OR(int i) {
			return getToken(RestaurantFormulaParser.OR, i);
		}
		public OrexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).enterOrexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).exitOrexpr(this);
		}
	}

	public final OrexprContext orexpr() throws RecognitionException {
		OrexprContext _localctx = new OrexprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_orexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16); andexpr();
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(17); match(OR);
				setState(18); andexpr();
				}
				}
				setState(23);
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

	public static class AndexprContext extends ParserRuleContext {
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public TerminalNode AND(int i) {
			return getToken(RestaurantFormulaParser.AND, i);
		}
		public List<TerminalNode> AND() { return getTokens(RestaurantFormulaParser.AND); }
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AndexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).enterAndexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).exitAndexpr(this);
		}
	}

	public final AndexprContext andexpr() throws RecognitionException {
		AndexprContext _localctx = new AndexprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_andexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24); atom();
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(25); match(AND);
				setState(26); atom();
				}
				}
				setState(31);
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

	public static class AtomContext extends ParserRuleContext {
		public InContext in() {
			return getRuleContext(InContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(RestaurantFormulaParser.LPAREN, 0); }
		public PriceContext price() {
			return getRuleContext(PriceContext.class,0);
		}
		public OrexprContext orexpr() {
			return getRuleContext(OrexprContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(RestaurantFormulaParser.RPAREN, 0); }
		public RatingContext rating() {
			return getRuleContext(RatingContext.class,0);
		}
		public CategoryContext category() {
			return getRuleContext(CategoryContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atom);
		try {
			setState(41);
			switch (_input.LA(1)) {
			case IN:
				enterOuterAlt(_localctx, 1);
				{
				setState(32); in();
				}
				break;
			case CATEGORY:
				enterOuterAlt(_localctx, 2);
				{
				setState(33); category();
				}
				break;
			case RATING:
				enterOuterAlt(_localctx, 3);
				{
				setState(34); rating();
				}
				break;
			case PRICE:
				enterOuterAlt(_localctx, 4);
				{
				setState(35); price();
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 5);
				{
				setState(36); name();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(37); match(LPAREN);
				setState(38); orexpr();
				setState(39); match(RPAREN);
				}
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

	public static class InContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(RestaurantFormulaParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(RestaurantFormulaParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(RestaurantFormulaParser.RPAREN, 0); }
		public TerminalNode IN() { return getToken(RestaurantFormulaParser.IN, 0); }
		public InContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_in; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).enterIn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).exitIn(this);
		}
	}

	public final InContext in() throws RecognitionException {
		InContext _localctx = new InContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_in);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(43); match(IN);
			setState(44); match(LPAREN);
			setState(45); match(STRING);
			setState(46); match(RPAREN);
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

	public static class CategoryContext extends ParserRuleContext {
		public TerminalNode CATEGORY() { return getToken(RestaurantFormulaParser.CATEGORY, 0); }
		public TerminalNode LPAREN() { return getToken(RestaurantFormulaParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(RestaurantFormulaParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(RestaurantFormulaParser.RPAREN, 0); }
		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).enterCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).exitCategory(this);
		}
	}

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_category);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(48); match(CATEGORY);
			setState(49); match(LPAREN);
			setState(50); match(STRING);
			setState(51); match(RPAREN);
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

	public static class NameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(RestaurantFormulaParser.NAME, 0); }
		public TerminalNode LPAREN() { return getToken(RestaurantFormulaParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(RestaurantFormulaParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(RestaurantFormulaParser.RPAREN, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(53); match(NAME);
			setState(54); match(LPAREN);
			setState(55); match(STRING);
			setState(56); match(RPAREN);
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

	public static class RatingContext extends ParserRuleContext {
		public TerminalNode RANGE() { return getToken(RestaurantFormulaParser.RANGE, 0); }
		public TerminalNode LPAREN() { return getToken(RestaurantFormulaParser.LPAREN, 0); }
		public TerminalNode RATING() { return getToken(RestaurantFormulaParser.RATING, 0); }
		public TerminalNode RPAREN() { return getToken(RestaurantFormulaParser.RPAREN, 0); }
		public RatingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rating; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).enterRating(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).exitRating(this);
		}
	}

	public final RatingContext rating() throws RecognitionException {
		RatingContext _localctx = new RatingContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_rating);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(58); match(RATING);
			setState(59); match(LPAREN);
			setState(60); match(RANGE);
			setState(61); match(RPAREN);
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

	public static class PriceContext extends ParserRuleContext {
		public TerminalNode PRICE() { return getToken(RestaurantFormulaParser.PRICE, 0); }
		public TerminalNode RANGE() { return getToken(RestaurantFormulaParser.RANGE, 0); }
		public TerminalNode LPAREN() { return getToken(RestaurantFormulaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(RestaurantFormulaParser.RPAREN, 0); }
		public PriceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_price; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).enterPrice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RestaurantFormulaListener ) ((RestaurantFormulaListener)listener).exitPrice(this);
		}
	}

	public final PriceContext price() throws RecognitionException {
		PriceContext _localctx = new PriceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_price);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(63); match(PRICE);
			setState(64); match(LPAREN);
			setState(65); match(RANGE);
			setState(66); match(RPAREN);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17G\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\7\2\26"+
		"\n\2\f\2\16\2\31\13\2\3\3\3\3\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4,\n\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\2\2\n\2\4\6\b\n\f\16\20\2\2E\2\22\3\2\2\2\4\32\3\2\2\2\6+\3\2\2\2\b-"+
		"\3\2\2\2\n\62\3\2\2\2\f\67\3\2\2\2\16<\3\2\2\2\20A\3\2\2\2\22\27\5\4\3"+
		"\2\23\24\7\13\2\2\24\26\5\4\3\2\25\23\3\2\2\2\26\31\3\2\2\2\27\25\3\2"+
		"\2\2\27\30\3\2\2\2\30\3\3\2\2\2\31\27\3\2\2\2\32\37\5\6\4\2\33\34\7\n"+
		"\2\2\34\36\5\6\4\2\35\33\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2"+
		" \5\3\2\2\2!\37\3\2\2\2\",\5\b\5\2#,\5\n\6\2$,\5\16\b\2%,\5\20\t\2&,\5"+
		"\f\7\2\'(\7\b\2\2()\5\2\2\2)*\7\t\2\2*,\3\2\2\2+\"\3\2\2\2+#\3\2\2\2+"+
		"$\3\2\2\2+%\3\2\2\2+&\3\2\2\2+\'\3\2\2\2,\7\3\2\2\2-.\7\3\2\2./\7\b\2"+
		"\2/\60\7\16\2\2\60\61\7\t\2\2\61\t\3\2\2\2\62\63\7\4\2\2\63\64\7\b\2\2"+
		"\64\65\7\16\2\2\65\66\7\t\2\2\66\13\3\2\2\2\678\7\5\2\289\7\b\2\29:\7"+
		"\16\2\2:;\7\t\2\2;\r\3\2\2\2<=\7\6\2\2=>\7\b\2\2>?\7\f\2\2?@\7\t\2\2@"+
		"\17\3\2\2\2AB\7\7\2\2BC\7\b\2\2CD\7\f\2\2DE\7\t\2\2E\21\3\2\2\2\5\27\37"+
		"+";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}