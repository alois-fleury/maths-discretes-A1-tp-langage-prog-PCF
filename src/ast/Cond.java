package ast;

import interp.Env;
import interp.IntVal;
import interp.Value;

public class Cond extends Term {
    public Term test, branchTrue, branchFalse;

    public Cond(Term test, Term branchTrue, Term branchFalse) {
        this.test = test;
        this.branchTrue = branchTrue;
        this.branchFalse = branchFalse;
    }

    @Override
    public Value interp(Env e) {
        return switch (test.interp(e)) { // evaluate test
            case IntVal v -> v.value() == 0 ? branchTrue.interp(e) : branchFalse.interp(e); // check it is an integer and unbox
            default -> throw new IllegalArgumentException("Cond: test must be an integer"); // raise en error
        };
    }
}
