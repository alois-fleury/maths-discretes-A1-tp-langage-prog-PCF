package ast;

import interp.Env;
import interp.Value;

public class Cond extends Term {
    public Term test, branchTrue, branchFalse ;

    public Cond(Term test, Term branchTrue, Term branchFalse) {
        this.test = test;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    @Override
    public Value interp(Env e) {
        // TODO : il faut aller regarder ce que vaut t pour regarder si c'est 0 ou n (évaluer le 2e terme, sinon le 3e)
        return null;
    }
}
