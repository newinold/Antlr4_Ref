package tour;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Col {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		CharStream input = CharStreams.fromFileName("files//tour//t.rows");
		
		RowsLexer lexer = new RowsLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		int col = 2; // column number ( 1 ~3 )
		
		RowsParser parser = new RowsParser(tokens,col);

		parser.setBuildParseTree(false);
		
		parser.file();
		
	}

}
