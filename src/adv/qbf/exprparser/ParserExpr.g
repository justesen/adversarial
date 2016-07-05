grammar ParserExpr;

@header {
package adv.qbf.exprparser;

import adv.qbf.formula.*;

}

@lexer::header {
package adv.qbf.exprparser;

}

prog returns [Expr ex]
    : e=exp EOF { $ex = e; }
    ;

exp returns [Expr ex]
    : 'exists' '[' v=VAR ']' e=exp    { $ex = new ExprExists($v.getText().hashCode(), e); }
    | 'forall' '[' v=VAR ']' e=exp    { $ex = new ExprForAll($v.getText().hashCode(), e); }
    | '!' e=exp                       { $ex = new ExprNot(e); }
    | v=VAR                           { $ex = new ExprVariable($v.getText().hashCode()); }
    | '(' e=opexp ')'                 { $ex = e; }
    ;

opexp returns [Expr ex]
    : e1=exp                    { $ex = e1; }
      ( '|' e2=exp        { $ex = new ExprOr(e1, e2); }
      | '&' e2=exp        { $ex = new ExprAnd(e1, e2); }
      )
    ;

VAR : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9')* ;
WS  : (' '|'\t'|'\r'|'\n')+ { $channel = HIDDEN; } ;