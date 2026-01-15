grammar PCF;

// règles syntaxiques

program : term EOF ;
term : LIT                                   # Lit
     | term OP_1 term                          # BinOp
     | term OP_2 term                          # BinOp
     | 'ifz' term 'then' term 'else' term    # Cond
     | '(' term ')'                          # Par
     ;

// règles lexicales

OP_1 : '*' | '/' ;
OP_2  : '+' | '-' ;
LIT : '0' | [1-9][0-9]* ;
WS: ('\n' | '\r' | '\t' | ' ') -> skip;