grammar PCF;

// règles syntaxiques

program : term EOF ;
term : LIT                                   # Lit
     | X                                     # Var
     | term OP_MUL term                      # BinOp
     | term OP_ADD term                      # BinOp
     | 'ifz' term 'then' term 'else' term    # Cond
     | '(' term ')'                          # Par
     ;

// règles lexicales

OP_MUL  : '*' | '/' ;
OP_ADD  : '+' | '-' ;
X : [a-z][a-zA-Z0-9]* ;
LIT : '0' | [1-9][0-9]* ;
WS: ('\n' | '\r' | '\t' | ' ') -> skip;
