package ast;

import interp.Env;
import interp.Value;

public abstract class Term extends AST {
    // Première règle : environnement qui prend une valeur X et la transforme en V
    public abstract Value interp(Env e);
}
