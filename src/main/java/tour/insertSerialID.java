package tour;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class insertSerialID {
	// TODO Auto-generated method stub
	
	public static void main(String args[]) throws IOException {

		CharStream input = CharStreams.fromFileName("files//tour//Demo.java.txt");
		
		JavaLexer lexer = new JavaLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		JavaParser parser = new JavaParser(tokens);

		ParseTree tree = parser.compilationUnit();
		
		ParseTreeWalker walker = new ParseTreeWalker();
		
		InsertSerialDlistener extractor = new InsertSerialDlistener(tokens);
		
		walker.walk(extractor, tree);
		
		System.out.println(extractor.rewriter.getText());
	}

	
	
}
