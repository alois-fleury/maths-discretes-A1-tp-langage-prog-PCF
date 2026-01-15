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
        IntVal v1 = (IntVal) term1.interp(e);
        IntVal v2 = (IntVal) term2.interp(e);

        int result = switch (this.op) {
            case PLUS -> v1.value + v2.value;
            case MINUS -> v1.value - v2.value;
            case TIMES -> v1.value * v2.value;
            case DIVIDE -> v1.value / v2.value;
        };
        return new IntVal(result);
    }
}
