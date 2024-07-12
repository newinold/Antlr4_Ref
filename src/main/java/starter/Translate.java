package starter;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Translate {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		CharStream input = CharStreams.fromStream(System.in);
		
		ArrayInitLexer lexer = new ArrayInitLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		ArrayInitParser parser = new ArrayInitParser(tokens);

		ParseTree tree = parser.init();
		
		System.out.println(tree.toStringTree(parser));
		
		ParseTreeWalker walker = new ParseTreeWalker();
		
		walker.walk(new ShortToUnicodeString(), tree);
		
		System.out.println();
		
	}

}
