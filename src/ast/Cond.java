package ast;

import interp.Env;
import interp.IntVal;
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
        Value test_value = test.interp(e);
        if (test_value instanceof IntVal) {
            if (((IntVal) test_value).value == 0) {
                return branchTrue.interp(e);
            } else {
                return branchFalse.interp(e);
            }
        }
        throw new IllegalArgumentException();
    }
}
