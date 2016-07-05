// $ANTLR 3.5.2 ParserExpr.g 2016-07-05 01:43:36

package adv.qbf.exprparser;


import org.antlr.runtime.*;

@SuppressWarnings("all")
public class ParserExprLexer extends Lexer {
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

    public ParserExprLexer() {
    }

    public ParserExprLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }

    public ParserExprLexer(CharStream input, RecognizerSharedState state) {
        super(input, state);
    }

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[]{};
    }

    @Override
    public String getGrammarFileName() {
        return "ParserExpr.g";
    }

    // $ANTLR start "T__6"
    public final void mT__6() throws RecognitionException {
        try {
            int _type = T__6;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:7:6: ( '!' )
            // ParserExpr.g:7:8: '!'
            {
                match('!');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__6"

    // $ANTLR start "T__7"
    public final void mT__7() throws RecognitionException {
        try {
            int _type = T__7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:8:6: ( '&' )
            // ParserExpr.g:8:8: '&'
            {
                match('&');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__7"

    // $ANTLR start "T__8"
    public final void mT__8() throws RecognitionException {
        try {
            int _type = T__8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:9:6: ( '(' )
            // ParserExpr.g:9:8: '('
            {
                match('(');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__8"

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:10:6: ( ')' )
            // ParserExpr.g:10:8: ')'
            {
                match(')');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:11:7: ( '[' )
            // ParserExpr.g:11:9: '['
            {
                match('[');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:12:7: ( ']' )
            // ParserExpr.g:12:9: ']'
            {
                match(']');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:13:7: ( 'exists' )
            // ParserExpr.g:13:9: 'exists'
            {
                match("exists");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:14:7: ( 'forall' )
            // ParserExpr.g:14:9: 'forall'
            {
                match("forall");

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:15:7: ( '|' )
            // ParserExpr.g:15:9: '|'
            {
                match('|');
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "VAR"
    public final void mVAR() throws RecognitionException {
        try {
            int _type = VAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:34:5: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // ParserExpr.g:34:7: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
                if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z') || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }
                // ParserExpr.g:34:26: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
                loop1:
                while (true) {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);
                    if (((LA1_0 >= '0' && LA1_0 <= '9') || (LA1_0 >= 'A' && LA1_0 <= 'Z') || (LA1_0 >= 'a' && LA1_0 <= 'z'))) {
                        alt1 = 1;
                    }

                    switch (alt1) {
                        case 1:
                            // ParserExpr.g:
                        {
                            if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }
                        }
                        break;

                        default:
                            break loop1;
                    }
                }

            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "VAR"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ParserExpr.g:35:5: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ParserExpr.g:35:7: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
                // ParserExpr.g:35:7: ( ' ' | '\\t' | '\\r' | '\\n' )+
                int cnt2 = 0;
                loop2:
                while (true) {
                    int alt2 = 2;
                    int LA2_0 = input.LA(1);
                    if (((LA2_0 >= '\t' && LA2_0 <= '\n') || LA2_0 == '\r' || LA2_0 == ' ')) {
                        alt2 = 1;
                    }

                    switch (alt2) {
                        case 1:
                            // ParserExpr.g:
                        {
                            if ((input.LA(1) >= '\t' && input.LA(1) <= '\n') || input.LA(1) == '\r' || input.LA(1) == ' ') {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }
                        }
                        break;

                        default:
                            if (cnt2 >= 1) break loop2;
                            EarlyExitException eee = new EarlyExitException(2, input);
                            throw eee;
                    }
                    cnt2++;
                }

                _channel = HIDDEN;
            }

            state.type = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    @Override
    public void mTokens() throws RecognitionException {
        // ParserExpr.g:1:8: ( T__6 | T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | VAR | WS )
        int alt3 = 11;
        switch (input.LA(1)) {
            case '!': {
                alt3 = 1;
            }
            break;
            case '&': {
                alt3 = 2;
            }
            break;
            case '(': {
                alt3 = 3;
            }
            break;
            case ')': {
                alt3 = 4;
            }
            break;
            case '[': {
                alt3 = 5;
            }
            break;
            case ']': {
                alt3 = 6;
            }
            break;
            case 'e': {
                int LA3_7 = input.LA(2);
                if ((LA3_7 == 'x')) {
                    int LA3_12 = input.LA(3);
                    if ((LA3_12 == 'i')) {
                        int LA3_14 = input.LA(4);
                        if ((LA3_14 == 's')) {
                            int LA3_16 = input.LA(5);
                            if ((LA3_16 == 't')) {
                                int LA3_18 = input.LA(6);
                                if ((LA3_18 == 's')) {
                                    int LA3_20 = input.LA(7);
                                    if (((LA3_20 >= '0' && LA3_20 <= '9') || (LA3_20 >= 'A' && LA3_20 <= 'Z') || (LA3_20 >= 'a' && LA3_20 <= 'z'))) {
                                        alt3 = 10;
                                    } else {
                                        alt3 = 7;
                                    }

                                } else {
                                    alt3 = 10;
                                }

                            } else {
                                alt3 = 10;
                            }

                        } else {
                            alt3 = 10;
                        }

                    } else {
                        alt3 = 10;
                    }

                } else {
                    alt3 = 10;
                }

            }
            break;
            case 'f': {
                int LA3_8 = input.LA(2);
                if ((LA3_8 == 'o')) {
                    int LA3_13 = input.LA(3);
                    if ((LA3_13 == 'r')) {
                        int LA3_15 = input.LA(4);
                        if ((LA3_15 == 'a')) {
                            int LA3_17 = input.LA(5);
                            if ((LA3_17 == 'l')) {
                                int LA3_19 = input.LA(6);
                                if ((LA3_19 == 'l')) {
                                    int LA3_21 = input.LA(7);
                                    if (((LA3_21 >= '0' && LA3_21 <= '9') || (LA3_21 >= 'A' && LA3_21 <= 'Z') || (LA3_21 >= 'a' && LA3_21 <= 'z'))) {
                                        alt3 = 10;
                                    } else {
                                        alt3 = 8;
                                    }

                                } else {
                                    alt3 = 10;
                                }

                            } else {
                                alt3 = 10;
                            }

                        } else {
                            alt3 = 10;
                        }

                    } else {
                        alt3 = 10;
                    }

                } else {
                    alt3 = 10;
                }

            }
            break;
            case '|': {
                alt3 = 9;
            }
            break;
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z': {
                alt3 = 10;
            }
            break;
            case '\t':
            case '\n':
            case '\r':
            case ' ': {
                alt3 = 11;
            }
            break;
            default:
                NoViableAltException nvae =
                        new NoViableAltException("", 3, 0, input);
                throw nvae;
        }
        switch (alt3) {
            case 1:
                // ParserExpr.g:1:10: T__6
            {
                mT__6();

            }
            break;
            case 2:
                // ParserExpr.g:1:15: T__7
            {
                mT__7();

            }
            break;
            case 3:
                // ParserExpr.g:1:20: T__8
            {
                mT__8();

            }
            break;
            case 4:
                // ParserExpr.g:1:25: T__9
            {
                mT__9();

            }
            break;
            case 5:
                // ParserExpr.g:1:30: T__10
            {
                mT__10();

            }
            break;
            case 6:
                // ParserExpr.g:1:36: T__11
            {
                mT__11();

            }
            break;
            case 7:
                // ParserExpr.g:1:42: T__12
            {
                mT__12();

            }
            break;
            case 8:
                // ParserExpr.g:1:48: T__13
            {
                mT__13();

            }
            break;
            case 9:
                // ParserExpr.g:1:54: T__14
            {
                mT__14();

            }
            break;
            case 10:
                // ParserExpr.g:1:60: VAR
            {
                mVAR();

            }
            break;
            case 11:
                // ParserExpr.g:1:64: WS
            {
                mWS();

            }
            break;

        }
    }


}
