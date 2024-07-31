package lexmagic;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class CommentShiftMain {

	public static void main(String[] args) throws IOException {


		
		CharStream in = CharStreams.fromFileName("files/lexmagic/t.cymbol");
		SymbolLexer lexer = new SymbolLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SymbolParser parser = new SymbolParser(tokens);

		RuleContext tree = parser.file();
		

		ParseTreeWalker walker = new ParseTreeWalker();
		
		ShiftVarComments shifter = new ShiftVarComments(tokens);
		walker.walk(shifter, tree);


		System.out.println("rewrite__\n"+shifter.rewriter.getText());


	}

}
