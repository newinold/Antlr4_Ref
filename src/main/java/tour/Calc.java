package tour;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Calc {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		CharStream input = CharStreams.fromStream(System.in);
		
		LabeledExprLexer lexer = new LabeledExprLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		LabeledExprParser parser = new LabeledExprParser(tokens);

		ParseTree tree = parser.prog();
		
		
		EvalVisitor eval = new EvalVisitor();
		
		eval.visit(tree);
		
		//System.out.println(tree.toStringTree(parser));
		
		
//		ParseTreeWalker walker = new ParseTreeWalker();
//		
//		walker.walk(new ShortToUnicodeString(), tree);
//		
//		System.out.println();
//		
	}

}
