package listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import listener.LExprParser.AddContext;
import listener.LExprParser.IntContext;
import listener.LExprParser.MultContext;
import listener.LExprParser.SContext;

public class TestLevaluatorWithProps {
	
	public static class EvaluatorWIthProps extends LExprBaseListener {
		
		ParseTreeProperty<Integer> values = new ParseTreeProperty<Integer>();
	
		public void setValue(ParseTree node, int value) {values.put(node, value); };
		
		public int getValue(ParseTree node) {return values.get(node) ;};
		
		@Override
		public void exitS(SContext ctx) {
			// TODO Auto-generated method stub
			
			setValue(ctx, getValue(ctx.e()));
			super.exitS(ctx);
		}
		
		@Override
		public void exitMult(MultContext ctx) {
			// TODO Auto-generated method stub
			int left = getValue(ctx.e(0));
			int right = getValue(ctx.e(1));
			
			setValue(ctx, left*right);
			
			super.exitMult(ctx);
		}
		
		@Override
		public void exitAdd(AddContext ctx) {

			int left = getValue(ctx.e(0));
			int right = getValue(ctx.e(1));
			
			setValue(ctx, left+right);
			
			super.exitAdd(ctx);
		}
		
		@Override
		public void exitInt(IntContext ctx) {
			// TODO Auto-generated method stub
			String intText = ctx.INT().getText();
			setValue(ctx, Integer.valueOf(intText));
			super.exitInt(ctx);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String inputFile = null;
		if( args.length > 0) inputFile = args[0];
		InputStream is = System.in;
		
		if(inputFile != null ) {
			is = new FileInputStream(inputFile);
		}
		
		CharStream chr = CharStreams.fromStream(is);
		LExprLexer lexer = new LExprLexer(chr);
		CommonTokenStream  token = new CommonTokenStream(lexer);
		
		LExprParser parser = new LExprParser(token);
		parser.setBuildParseTree(true);
		ParseTree tree =  parser.s();
		
		EvaluatorWIthProps eval2 = new EvaluatorWIthProps();
		
		
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(eval2, tree);
		
		System.out.println("result with props__"+eval2.getValue(tree));

	}

}
