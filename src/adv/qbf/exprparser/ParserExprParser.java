// $ANTLR 3.5.2 ParserExpr.g 2016-07-05 01:43:35

package adv.qbf.exprparser;

import adv.qbf.formula.*;
import org.antlr.runtime.*;

@SuppressWarnings("all")
public class ParserExprParser extends Parser {
    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "VAR", "WS", "'!'", "'&'", "'('",
            "')'", "'['", "']'", "'exists'", "'forall'", "'|'"
    };
    public static final int EOF = -1;
    public static final int T__6 = 6;
    public static final int T__7 = 7;
    public static final int T__8 = 8;
    public static final int T__9 = 9;
    public static final int T__10 = 10;
    public static final int T__11 = 11;
    public static final int T__12 = 12;
    public static final int T__13 = 13;
    public static final int T__14 = 14;
    public static final int VAR = 4;
    public static final int WS = 5;
    public static final BitSet FOLLOW_exp_in_prog35 = new BitSet(new long[]{0x0000000000000000L});

    // delegators
    public static final BitSet FOLLOW_EOF_in_prog37 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_exp60 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_exp62 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_VAR_in_exp66 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_exp68 = new BitSet(new long[]{0x0000000000003150L});
    // $ANTLR end "prog"
    public static final BitSet FOLLOW_exp_in_exp72 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "exp"
    public static final BitSet FOLLOW_13_in_exp85 = new BitSet(new long[]{0x0000000000000400L});
    // $ANTLR end "opexp"

    // Delegated rules
    public static final BitSet FOLLOW_10_in_exp87 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_VAR_in_exp91 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_exp93 = new BitSet(new long[]{0x0000000000003150L});
    public static final BitSet FOLLOW_exp_in_exp97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_6_in_exp110 = new BitSet(new long[]{0x0000000000003150L});
    public static final BitSet FOLLOW_exp_in_exp114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_exp148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_exp184 = new BitSet(new long[]{0x0000000000003150L});
    public static final BitSet FOLLOW_opexp_in_exp188 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_exp190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_in_opexp231 = new BitSet(new long[]{0x0000000000004080L});
    public static final BitSet FOLLOW_14_in_opexp262 = new BitSet(new long[]{0x0000000000003150L});
    public static final BitSet FOLLOW_exp_in_opexp266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_7_in_opexp285 = new BitSet(new long[]{0x0000000000003150L});
    public static final BitSet FOLLOW_exp_in_opexp289 = new BitSet(new long[]{0x0000000000000002L});

    public ParserExprParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public ParserExprParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    // delegates
    public Parser[] getDelegates() {
        return new Parser[]{};
    }

    @Override
    public String[] getTokenNames() {
        return ParserExprParser.tokenNames;
    }

    @Override
    public String getGrammarFileName() {
        return "ParserExpr.g";
    }

    // $ANTLR start "prog"
    // ParserExpr.g:15:1: prog returns [Expr ex] : e= exp EOF ;
    public final Expr prog() throws RecognitionException {
        Expr ex = null;


        Expr e = null;

        try {
            // ParserExpr.g:16:5: (e= exp EOF )
            // ParserExpr.g:16:7: e= exp EOF
            {
                pushFollow(FOLLOW_exp_in_prog35);
                e = exp();
                state._fsp--;

                match(input, EOF, FOLLOW_EOF_in_prog37);
                ex = e;
            }

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return ex;
    }

    // $ANTLR start "exp"
    // ParserExpr.g:19:1: exp returns [Expr ex] : ( 'exists' '[' v= VAR ']' e= exp | 'forall' '[' v= VAR ']' e= exp | '!' e= exp |v= VAR | '(' e= opexp ')' );
    public final Expr exp() throws RecognitionException {
        Expr ex = null;


        Token v = null;
        Expr e = null;

        try {
            // ParserExpr.g:20:5: ( 'exists' '[' v= VAR ']' e= exp | 'forall' '[' v= VAR ']' e= exp | '!' e= exp |v= VAR | '(' e= opexp ')' )
            int alt1 = 5;
            switch (input.LA(1)) {
                case 12: {
                    alt1 = 1;
                }
                break;
                case 13: {
                    alt1 = 2;
                }
                break;
                case 6: {
                    alt1 = 3;
                }
                break;
                case VAR: {
                    alt1 = 4;
                }
                break;
                case 8: {
                    alt1 = 5;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 1, 0, input);
                    throw nvae;
            }
            switch (alt1) {
                case 1:
                    // ParserExpr.g:20:7: 'exists' '[' v= VAR ']' e= exp
                {
                    match(input, 12, FOLLOW_12_in_exp60);
                    match(input, 10, FOLLOW_10_in_exp62);
                    v = (Token) match(input, VAR, FOLLOW_VAR_in_exp66);
                    match(input, 11, FOLLOW_11_in_exp68);
                    pushFollow(FOLLOW_exp_in_exp72);
                    e = exp();
                    state._fsp--;

                    ex = new ExprExists(v.getText().hashCode(), e);
                }
                break;
                case 2:
                    // ParserExpr.g:21:7: 'forall' '[' v= VAR ']' e= exp
                {
                    match(input, 13, FOLLOW_13_in_exp85);
                    match(input, 10, FOLLOW_10_in_exp87);
                    v = (Token) match(input, VAR, FOLLOW_VAR_in_exp91);
                    match(input, 11, FOLLOW_11_in_exp93);
                    pushFollow(FOLLOW_exp_in_exp97);
                    e = exp();
                    state._fsp--;

                    ex = new ExprForAll(v.getText().hashCode(), e);
                }
                break;
                case 3:
                    // ParserExpr.g:22:7: '!' e= exp
                {
                    match(input, 6, FOLLOW_6_in_exp110);
                    pushFollow(FOLLOW_exp_in_exp114);
                    e = exp();
                    state._fsp--;

                    ex = new ExprNot(e);
                }
                break;
                case 4:
                    // ParserExpr.g:23:7: v= VAR
                {
                    v = (Token) match(input, VAR, FOLLOW_VAR_in_exp148);
                    ex = new ExprVariable(v.getText().hashCode());
                }
                break;
                case 5:
                    // ParserExpr.g:24:7: '(' e= opexp ')'
                {
                    match(input, 8, FOLLOW_8_in_exp184);
                    pushFollow(FOLLOW_opexp_in_exp188);
                    e = opexp();
                    state._fsp--;

                    match(input, 9, FOLLOW_9_in_exp190);
                    ex = e;
                }
                break;

            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return ex;
    }

    // $ANTLR start "opexp"
    // ParserExpr.g:27:1: opexp returns [Expr ex] : e1= exp ( '|' e2= exp | '&' e2= exp ) ;
    public final Expr opexp() throws RecognitionException {
        Expr ex = null;


        Expr e1 = null;
        Expr e2 = null;

        try {
            // ParserExpr.g:28:5: (e1= exp ( '|' e2= exp | '&' e2= exp ) )
            // ParserExpr.g:28:7: e1= exp ( '|' e2= exp | '&' e2= exp )
            {
                pushFollow(FOLLOW_exp_in_opexp231);
                e1 = exp();
                state._fsp--;

                ex = e1;
                // ParserExpr.g:29:7: ( '|' e2= exp | '&' e2= exp )
                int alt2 = 2;
                int LA2_0 = input.LA(1);
                if ((LA2_0 == 14)) {
                    alt2 = 1;
                } else if ((LA2_0 == 7)) {
                    alt2 = 2;
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 2, 0, input);
                    throw nvae;
                }

                switch (alt2) {
                    case 1:
                        // ParserExpr.g:29:9: '|' e2= exp
                    {
                        match(input, 14, FOLLOW_14_in_opexp262);
                        pushFollow(FOLLOW_exp_in_opexp266);
                        e2 = exp();
                        state._fsp--;

                        ex = new ExprOr(e1, e2);
                    }
                    break;
                    case 2:
                        // ParserExpr.g:30:9: '&' e2= exp
                    {
                        match(input, 7, FOLLOW_7_in_opexp285);
                        pushFollow(FOLLOW_exp_in_opexp289);
                        e2 = exp();
                        state._fsp--;

                        ex = new ExprAnd(e1, e2);
                    }
                    break;

                }

            }

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
        } finally {
            // do for sure before leaving
        }
        return ex;
    }
}
