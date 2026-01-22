package interp;

import ast.Term;

public record Closure(String var_name, Term term, Env env) implements Value {}
