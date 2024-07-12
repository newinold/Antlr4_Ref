package listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import listener.LExprParser.AddContext;
import listener.LExprParser.IntContext;
import listener.LExprParser.MultContext;

public class TestLEvaluator {

	public static class Evaluator extends LExprBaseListener {
		
		Stack<Integer> stack = new Stack<Integer>();
		
		@Override
		public void exitMult(MultContext ctx) {
			
			int right = stack.pop();
			System.out.println("right___"+right);
			int left = stack.pop();
			System.out.println("left___"+left);
			
			stack.push(right*left);
			super.exitMult(ctx);
		}
		
		@Override
		public void exitAdd(AddContext ctx) {
			int right = stack.pop();
			int left = stack.pop();
			
			stack.push(right+left);
			super.exitAdd(ctx);
		}
		
		@Override
		public void exitInt(IntContext ctx) {
			stack.push(Integer.valueOf(ctx.INT().getText()));
			super.exitInt(ctx);
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
		
		ParseTreeWalker walker = new ParseTreeWalker();
		
		Evaluator eval = new Evaluator();
		
		walker.walk(eval, tree);

		System.out.println("value __"+eval.stack.pop());
		
		
	}

}
