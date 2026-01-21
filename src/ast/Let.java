package ast;

import interp.Env;
import interp.NonEmptyEnv;
import interp.Value;

import java.util.Optional;

public class Let  extends Term{
    public String var;
    public Term term1, term2;

    public Let(String var, Term term1, Term term2) {
        this.var = var;
        this.term1 = term1;
        this.term2 = term2;
    }

    @Override
    public Value interp(Env e) {
        Value v = term1.interp(e);
        Env NewEnv = e.add(var, v);
        return term2.interp(NewEnv);
    }
}
