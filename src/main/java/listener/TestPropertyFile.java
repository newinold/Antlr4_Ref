package listener;

import java.io.IOException;
import java.util.Map;

import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class TestPropertyFile {

    public static class PropertyFileLoader extends PropertyFileParser {
        public PropertyFileLoader(TokenStream input) {
			super(input);
		}
		Map<String,String> props = new OrderedHashMap<String, String>();
//        public void exitProp(PropertyFileParser.PropContext ctx) {
//            String id = ctx.ID().getText(); // prop : ID '=' STRING '\n' ;
//            String value = ctx.STRING().getText();
//            props.put(id, value);
//        }
        void defineProperty(Token name, Token value) {
        	System.out.println(name.getText()+"==="+value.getText());
        	props.put(name.getText(), value.getText());
        }
        
        
    }
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//
//        String inputFile = null;
//        if ( args.length>0 ) inputFile = args[0];
//        InputStream is = System.in;
//        if ( inputFile!=null ) {
//            is = new FileInputStream(inputFile);
//        }
        
        CharStream input = CharStreams.fromFileName("files/listener/t.properties");
        
        PropertyFileLexer lexer = new PropertyFileLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PropertyFileLoader parser = new PropertyFileLoader(tokens);
        ParseTree tree = parser.file();

        // create a standard ANTLR parse tree walker
//        ParseTreeWalker walker = new ParseTreeWalker();
//        // create listener then feed to walker
//        PropertyFileLoader loader = new PropertyFileLoader(tokens);
//        walker.walk(lo, tree); walk(loader, tree);        // walk parse tree
//        System.out.println(loader.props); // print results

	}

}
