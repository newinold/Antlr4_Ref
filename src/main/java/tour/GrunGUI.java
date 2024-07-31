package tour;
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

import tool.TreeViewer;



public class GrunGUI {

	public static void main(String[] args) throws IOException, PrintException {
		
		InputStream in = GrunGUI.class.getClassLoader().getResourceAsStream("Demo.java.txt");
		CharStream s = CharStreams.fromStream(in,Charset.forName("euc-kr"));
		
		JavaLexer lexer = new JavaLexer(s);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		JavaParser parser = new JavaParser(tokens);

		ParseTree tree = parser.compilationUnit();
		List<String> ruleNames = Arrays.asList(JavaParser.ruleNames);
        
		System.out.println(ruleNames);
        
		TreeViewer view = new TreeViewer(ruleNames, tree);
        
        view.open();
        //view.save("D:\\Back\\abc.svg");
		
		}

}
