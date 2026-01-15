package parser;

import java.util.List;
import java.util.ArrayList;

import ast.*;

public class ASTVisitor extends PCFBaseVisitor<AST> {

    @Override
    public AST visitLit(PCFParser.LitContext ctx) {

        return new Lit(Integer.parseInt(ctx.getText()));
    }

    @Override
    public AST visitBinOp(PCFParser.BinOpContext ctx) {
        OP op = OP.parseOP(
                ctx.OP_ADD() != null ?
                        ctx.OP_ADD().getText() :
                        ctx.OP_MUL().getText()
        );
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
        return visit(ctx.term()) ;
    }
}