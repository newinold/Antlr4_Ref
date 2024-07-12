package listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import listener.ExprParser.EContext;
import listener.ExprParser.SContext;

public class TestEvaluator {

	
	public static class EvaluatorWithProps extends ExprBaseListener{
		
		ParseTreeProperty<Integer> values = new ParseTreeProperty<Integer>();
		
		@Override
		public void exitS(SContext ctx) {
			values.put(ctx, values.get(ctx.getChild(0)));
			super.exitS(ctx);
		}
		
		@Override
		public void exitE(EContext ctx) {
			// TODO Auto-generated method stub
			
			if( ctx.getChildCount() ==3) {
				int left = values.get(ctx.e(0));
				int right = values.get(ctx.e(1));
				if( ctx.op.getType() == ExprParser.MULT) {
					values.put(ctx, left*right);
				} else {
					values.put(ctx, left+right);
				}
			} else {
				values.put(ctx, values.get(ctx.getChild(0))); // int
			}
			super.exitE(ctx);
		}
		
		@Override
		public void visitTerminal(TerminalNode node) {
			Token symbol = node.getSymbol();
			if( symbol.getType() == ExprParser.INT) {
				values.put(node, Integer.valueOf(symbol.getText()));
				
			}
			super.visitTerminal(node);
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
		ExprLexer lexer = new ExprLexer(chr);
		CommonTokenStream  token = new CommonTokenStream(lexer);
		
		ExprParser parser = new ExprParser(token);
		parser.setBuildParseTree(true);
		ParseTree tree =  parser.s();
		
		EvaluatorWithProps eval2 = new EvaluatorWithProps();
		
		
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(eval2, tree);
		
		System.out.println("result with props__"+eval2.values.get(tree));

	}

}
