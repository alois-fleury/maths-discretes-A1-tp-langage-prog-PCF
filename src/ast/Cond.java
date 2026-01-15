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
        final Value value = test.interp(e); // evaluate test
        if (value instanceof IntVal) { // check it is an integer
            int t = ((IntVal) value).value();
            return t == 0 ? branchFalse.interp(e) : branchTrue.interp(e);
        } else {
            throw new IllegalArgumentException("Cond: test must be an integer"); // raise an error
        }
    }
}
