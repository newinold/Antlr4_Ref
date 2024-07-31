package example;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import javax.print.PrintException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import examples.CymbolLexer;
import examples.CymbolParser;
import tool.TreeViewer;
import tour.JavaParser;



public class CymbolGrunGui {

	public static void main(String[] args) throws IOException, PrintException {
		
		
		CharStream input = CharStreams.fromFileName("files/examples/t.cymbol");
		
		CymbolLexer lexer = new CymbolLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CymbolParser parser = new CymbolParser(tokens);

		ParseTree tree = parser.file();
		List<String> ruleNames = Arrays.asList(JavaParser.ruleNames);
        
		System.out.println(ruleNames);
        
		TreeViewer view = new TreeViewer(ruleNames, tree);
        
        view.open();
        //view.save("D:\\Back\\abc.svg");
		
		}

}
