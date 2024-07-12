package listener;

import java.io.IOException;
import java.util.Map;

import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import listener.PropertyFileListenerParser.PropContext;

public class TestPropertyListener {

	
	public static class PropertyFileListenerLoader extends PropertyFileListenerBaseListener {
		
		Map<String,String> props = new OrderedHashMap<String, String>();
		
		@Override
		public void exitProp(PropContext ctx) {
			
			String id = ctx.ID().getText();
			String value = ctx.STRING().getText();
			
			props.put(id, value);
			
			super.exitProp(ctx);
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		CharStream input = CharStreams.fromFileName("C:\\Users\\newin\\eclipse-workspace\\hillssoft\\src\\main\\java\\listener\\t.properties");

		PropertyFileListenerLexer lexer = new PropertyFileListenerLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PropertyFileListenerParser parser = new PropertyFileListenerParser(tokens);
		ParseTree tree = parser.file();

		ParseTreeWalker walker = new ParseTreeWalker();
		PropertyFileListenerLoader loader = new PropertyFileListenerLoader();
		walker.walk(loader, tree);
		
		System.out.println(loader.props);


	}

}
