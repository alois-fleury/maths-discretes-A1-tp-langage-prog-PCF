package ast;

import interp.Closure;
import interp.Env;
import interp.Value;

public class Fun extends Term{
    public String var_name;
    public Term term;

    public Fun(String var, Term term) {
        this.var_name = var;
        this.term = term;
    }

    @Override
    public Value interp(Env e) {
        return new Closure(this.var_name, this.term, e);
    }
}
