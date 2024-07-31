package starter;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Test {

	public static void main(String[] args) throws IOException {
		
		//ANTLRInputStream input = new ANTLRInputStream(System.in);
		
		CharStream input = CharStreams.fromStream(System.in);
		
		ArrayInitLexer lexer = new ArrayInitLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		ArrayInitParser parser = new ArrayInitParser(tokens);
		
		ParseTree tree = parser.init();
		
		System.out.println(tree.toStringTree(parser));
		

	}

}
