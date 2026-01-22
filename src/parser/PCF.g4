grammar PCF;

// règles syntaxiques

program : term EOF ;
term : LIT                                   # Lit
     | VAR                                   # Var
     | '(' term ')'                          # Par
     | term term                             # App
     | term OP_1 term                        # BinOp
     | term OP_2 term                        # BinOp
     | 'ifz' term 'then' term 'else' term    # Cond
     | 'let' VAR '=' term 'in' term          # Let
     | 'fun' VAR '->' term                   # Fun
     ;

// règles lexicales

OP_1 : '*' | '/' ;
OP_2  : '+' | '-' ;
VAR : [a-z][a-z0-9]*;
LIT : '0' | [1-9][0-9]* ;
WS: ('\n' | '\r' | '\t' | ' ') -> skip;