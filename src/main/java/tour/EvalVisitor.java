package tour;

import java.util.HashMap;
import java.util.Map;

import tour.LabeledExprParser.AssignContext;
import tour.LabeledExprParser.PrintExprContext;

public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {
	
	Map<String,Integer> memory = new HashMap<String,Integer>();
	
	@Override
	public Integer visitAssign(AssignContext ctx) {
		
		String id = ctx.ID().getText();
		Integer value = visit(ctx.expr());
		memory.put(id, value);
		
		System.out.println("memory___"+memory);
		return value;
	}
	
	@Override
	public Integer visitPrintExpr(PrintExprContext ctx) {
		// TODO Auto-generated method stub
		Integer value = visit(ctx.expr());
		
		System.out.println("printExpr__"+value);
		
		return 0;
	}
	
	 /** INT */
    @Override
    public Integer visitInt(LabeledExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    /** ID */
    @Override
    public Integer visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        
        if ( memory.containsKey(id) ) return memory.get(id);
        return 0;
    }

    /** expr op=('*'|'/') expr */
    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));  // get value of left subexpression
        int right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == LabeledExprParser.MUL ) return left * right;
        return left / right; // must be DIV
    }

    /** expr op=('+'|'-') expr */
    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));  // get value of left subexpression
        int right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == LabeledExprParser.ADD ) return left + right;
        return left - right; // must be SUB
    }

    /** '(' expr ')' */
    @Override
    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr()); // return child expr's value
    }

}
