package tour;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ExtractInterfaceTool {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		CharStream input = CharStreams.fromFileName("files//tour//Demo.java.txt");
		
		JavaLexer lexer = new JavaLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		JavaParser parser = new JavaParser(tokens);

		ParseTree tree = parser.compilationUnit();
		
		ParseTreeWalker walker = new ParseTreeWalker();
		ExtractInterfaceListener extractor = new ExtractInterfaceListener(parser);
		walker.walk(extractor, tree);
		
		
	}

}
