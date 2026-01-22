package ast;

import interp.Closure;
import interp.Env;
import interp.Value;

public class App extends Term{
    public Term term1, term2;

    public App(Term term1, Term term2) {
        this.term1 = term1;
        this.term2 = term2;
    }

    @Override
    public Value interp(Env e) {
        Value fun = term1.interp(e);
        Value arg_val = term2.interp(e);

        return switch(fun) {
            // vérifie que fun est de type Closure
            case Closure closure -> {
                Env newEnv = closure.env().add(closure.var_name(), arg_val);
                yield closure.term().interp(newEnv);
            }
            default -> throw new RuntimeException("Pas une fonction");
        };
    }
}
