package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ast.Term;
import interp.EmptyEnv;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ast.AST;
import parser.*;

public class Main {
    public static boolean verbose = false;

    /**
     * @param args - the filename of the file containing the program,
     *                otherwise the program is entered at the console.
     *             - "-v" indicates verbose mode.
     */
    static void main(String[] args) throws IOException {
        InputStream is = null;

        for (String arg: args) {
            if (arg.charAt(0) != '-') {
                is = new FileInputStream(arg);
            } else if ("-v".equals(arg)) {
                verbose = true;
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (is == null) {
            is = System.in;

            verbose = true;
        }
        Term term = (Term) analyze(is);
        if (verbose) {
            IO.println("AST: " + term);
        }
        IO.println("===> "+ term.interp(new EmptyEnv()));
    }

    public static AST analyze(InputStream is) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(is);
        /* Basic connection to ANTLR (does not capture syntactic errors)
        PCFLexer lexer = new PCFLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PCFParser parser = new PCFParser(tokens);
         */
        PCFLexer lexer = new ReportingPCFLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PCFParser parser = new PCFParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ErrorListener());

        // generating parse tree
        ParseTree tree = parser.term();
        if (verbose)
            IO.println("ANTLR Syntax Tree: " + tree.toStringTree(parser));
        if (ErrorFlag.getFlag()) // exit in case of an error
            throw new SyntaxError(ErrorFlag.getMsg());
        else {                   // else visit parse tree to generate AST
            ASTVisitor visitor = new ASTVisitor();
            return visitor.visit(tree);
        }
    }
}

