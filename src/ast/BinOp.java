package ast;

import interp.Env;
import interp.IntVal;
import interp.Value;

public class BinOp extends Term {
    public OP op;
    public Term term1, term2;
    public BinOp(OP op, Term term1, Term term2) {
        this.op = op;
        this.term1 = term1;
        this.term2 = term2;
    }

    @Override
    public Value interp(Env e) {
        int v1 = ((IntVal) term1.interp(e)).value;
        int v2 = ((IntVal) term2.interp(e)).value;

        int result = switch (this.op) {
            case PLUS -> v1 + v2;
            case MINUS -> v1 - v2;
            case TIMES -> v1 * v2;
            case DIVIDE -> v1 / v2;
        };
        return new IntVal(result);
    }
}
