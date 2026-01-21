package ast;

import interp.Env;
import interp.Value;
import java.util.Optional;

/**
 * Représente une variable.
 */
public class Var extends Term{
    public String name;

    public Var(String name) {
        this.name = name;
    }

    @Override
    public Value interp(Env e) {
        Optional<Value> value = e.lookup(this.name);
        if(value.isEmpty())
            throw new RuntimeException("variable non définie");
        return value.get();
    }
}
