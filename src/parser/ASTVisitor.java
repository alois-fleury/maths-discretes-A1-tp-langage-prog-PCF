package parser;

import java.util.List;
import java.util.ArrayList;

import ast.*;

/**
 * Implémentation du pattern visitor pour relier l'implémentation "code Java" dans le package ast et l'utilisation dans
 * le fichier grammaire PCF.g4.
 */
public class ASTVisitor extends PCFBaseVisitor<AST> {

    @Override
    public AST visitLit(PCFParser.LitContext ctx) {
        return new Lit(Integer.parseInt(ctx.getText()));
    }

    @Override
    public AST visitBinOp(PCFParser.BinOpContext ctx) {
        OP op = OP.parseOP(ctx.OP_1() != null ? ctx.OP_1().getText() : ctx.OP_2().getText());
        List<PCFParser.TermContext> ANTLRTerms = ctx.term();
        List<Term> terms = new ArrayList<>();
        for (PCFParser.TermContext ANTLRTerm : ANTLRTerms)
            terms.add((Term) visit(ANTLRTerm));
        return new BinOp(op, terms.get(0), terms.get(1));
    }

    @Override
    public AST visitCond(PCFParser.CondContext ctx) {
        List<PCFParser.TermContext> ANTLRTerms = ctx.term();
        List<Term> terms = new ArrayList<>();
        for (PCFParser.TermContext ANTLRTerm : ANTLRTerms)
            terms.add((Term) visit(ANTLRTerm));
        return new Cond(terms.get(0), terms.get(1), terms.get(2));
    }

    @Override
    public AST visitPar(PCFParser.ParContext ctx) {
        PCFParser.TermContext ANTLRTerm = ctx.term();
        return visit(ANTLRTerm);
    }

    @Override
    public AST visitLet(PCFParser.LetContext ctx) {
        String var = ctx.VAR().getText();
        List<PCFParser.TermContext> ANTLRTerms = ctx.term();
        List<Term> terms = new ArrayList<>();
        for (PCFParser.TermContext ANTLRTerm : ANTLRTerms)
            terms.add((Term) visit(ANTLRTerm));
        return new Let(var, terms.get(0), terms.get(1));
    }

    @Override
    public AST visitVar(PCFParser.VarContext ctx) {
        return new Var(ctx.getText());
    }
}