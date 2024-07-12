package listener;

import java.io.IOException;
import java.util.Map;

import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import listener.PropertyFileParser.PropContext;

public class TestPropertyFileVisitor {

	
	public static class PropertyFileVisitor extends PropertyFileBaseVisitor<Void>{
		
		Map<String,String> props = new OrderedHashMap<String,String>();
		
		@Override
		public Void visitProp(PropContext ctx) {
			
			String id = ctx.ID().getText();
			String value = ctx.STRING.getText();
			
			props.put(id, value);
			return super.visitProp(ctx);
		}
		 
	}
	
	public static void main(String[] args) throws IOException {
		

		CharStream input = CharStreams.fromFileName("C:\\\\Users\\\\newin\\\\eclipse-workspace\\\\hillssoft\\\\src\\\\main\\\\java\\\\listener\\\\t.properties");
		PropertyFileListenerLexer lexer = new PropertyFileListenerLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PropertyFileListenerParser parser = new PropertyFileListenerParser(tokens);
		ParseTree tree = parser.file();
		
		PropertyFileVisitor loader = new PropertyFileVisitor();
		
		loader.visit(tree);
		
		System.out.println(loader.props);

	}

}
