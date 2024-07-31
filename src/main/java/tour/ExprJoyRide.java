package tour;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class ExprJoyRide {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		CharStream input = CharStreams.fromFileName("files/tour/t.expr");
		
		ExprLexer lexer = new ExprLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		ExprParser parser = new ExprParser(tokens);

		ParseTree tree = parser.prog();
		
		System.out.println(tree.toStringTree(parser));
		
//		ParseTreeWalker walker = new ParseTreeWalker();
//		
//		walker.walk(new ShortToUnicodeString(), tree);
//		
//		System.out.println();
//		
	}

}
