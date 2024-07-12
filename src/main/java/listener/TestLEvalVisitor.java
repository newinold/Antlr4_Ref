package listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import listener.LExprParser.AddContext;
import listener.LExprParser.IntContext;
import listener.LExprParser.MultContext;

public class TestLEvalVisitor {

	public static class EvalVisitor extends LExprBaseVisitor<Integer> {
		
		@Override
		public Integer visitMult(MultContext ctx) {
			// TODO Auto-generated method stub
			return visit(ctx.e(0)) * visit(ctx.e(1));
		}
		
		@Override
		public Integer visitAdd(AddContext ctx) {
			// TODO Auto-generated method stub
			return visit(ctx.e(0)) + visit(ctx.e(1));
		}
		
		@Override
		public Integer visitInt(IntContext ctx) {
			// TODO Auto-generated method stub
			return Integer.valueOf(ctx.INT().getText());
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		String inputFile =  null;
		if( args.length > 0 ) inputFile = args[0];
		
		InputStream is = System.in;
		
		if( inputFile != null ) {
			is = new FileInputStream(inputFile);
		}
		
		CharStream chr = CharStreams.fromStream(is);
		
		LExprLexer lexer = new LExprLexer(chr);
		
		CommonTokenStream token = new CommonTokenStream(lexer);
		
		LExprParser parser = new LExprParser(token);
		
		ParseTree tree = parser.s();
		
		
		EvalVisitor eval = new EvalVisitor();
		
		int result = eval.visit(tree);
		
		System.out.println("result ___"+result);
		
		
	}

}
