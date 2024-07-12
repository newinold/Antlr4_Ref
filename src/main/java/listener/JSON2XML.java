package listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import listener.JSONParser.AnObjectContext;
import listener.JSONParser.ArrayOfValuesContext;
import listener.JSONParser.ArrayValueContext;
import listener.JSONParser.AtomContext;
import listener.JSONParser.EmptyArrayContext;
import listener.JSONParser.EmptyObjectContext;
import listener.JSONParser.JsonContext;
import listener.JSONParser.ObjectValueContext;
import listener.JSONParser.PairContext;
import listener.JSONParser.StringContext;

public class JSON2XML {

	public static class XMLEmitter extends JSONBaseListener {

		ParseTreeProperty<String> xml = new ParseTreeProperty<String>();
		String getXML(ParseTree ctx) {return xml.get(ctx); };
		void setXML(ParseTree ctx, String s) { xml.put(ctx, s);};
		
		@Override
		public void exitJson(JsonContext ctx) {
			
			setXML(ctx,getXML(ctx.getChild(0)));
			super.exitJson(ctx);
		}
		
		
		@Override
		public void exitAnObject(AnObjectContext ctx) {
			StringBuilder buf = new StringBuilder();
			buf.append("\n");
			
			for( JSONParser.PairContext pctx : ctx.pair() ) {
				buf.append(getXML(pctx));
			}
			setXML(ctx,buf.toString());
			
			super.exitAnObject(ctx);
		}
		
		
		@Override
		public void exitEmptyObject(EmptyObjectContext ctx) {
			
			setXML(ctx,"");
			super.exitEmptyObject(ctx);
		}
		
		@Override
		public void exitArrayOfValues(ArrayOfValuesContext ctx) {
			
			StringBuilder buf = new StringBuilder();
			buf.append("\n");
			
			for( JSONParser.ValueContext vctx : ctx.value()) {
				buf.append("<element>");
				buf.append(getXML(vctx));
				buf.append("</element>");
				buf.append("\n");
			}
			
			setXML(ctx,buf.toString());
			
			super.exitArrayOfValues(ctx);
		}
		
		
		@Override
		public void exitEmptyArray(EmptyArrayContext ctx) {
			setXML(ctx,"");
			super.exitEmptyArray(ctx);
		}
		
		
		@Override
		public void exitPair(PairContext ctx) {
			
			String tag = stripQuotes(ctx.STRING().getText());
			
			JSONParser.ValueContext vctx = ctx.value();
			String x = String.format("<%s>%s</%s>\n", tag, getXML(vctx),tag);
			setXML(ctx,x);
			super.exitPair(ctx);
		
		
		}
		
		@Override
		public void exitObjectValue(ObjectValueContext ctx) {
			// TODO Auto-generated method stub
			
			setXML(ctx,getXML(ctx.object()));
			super.exitObjectValue(ctx);
		}
		
		@Override
		public void exitArrayValue(ArrayValueContext ctx) {
			setXML(ctx,getXML(ctx.array()));
			super.exitArrayValue(ctx);
		}
		
		
		@Override
		public void exitAtom(AtomContext ctx) {
			
			setXML(ctx,ctx.getText());
			super.exitAtom(ctx);
		}
		
		@Override
		public void exitString(StringContext ctx) {
			setXML(ctx,stripQuotes(ctx.getText()));
			super.exitString(ctx);
		}
		
		
		
        public static String stripQuotes(String s) {
            if ( s==null || s.charAt(0)!='"' ) return s;
            return s.substring(1, s.length() - 1);
        }
		
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String inputFile = "files/listener/t.json";
		if( args.length > 0) inputFile = args[0];
		InputStream is = System.in;
		
		if(inputFile != null ) {
			is = new FileInputStream(inputFile);
		}
		
		CharStream chr = CharStreams.fromStream(is);
		JSONLexer lexer = new JSONLexer(chr);
		CommonTokenStream  token = new CommonTokenStream(lexer);
		
		JSONParser parser = new JSONParser(token);
		parser.setBuildParseTree(true);
		ParseTree tree =  parser.json();
		
		XMLEmitter converter = new XMLEmitter();
		
		
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(converter, tree);
		
		System.out.println(converter.getXML(tree));

		//System.out.println(loader.rows);

	}

}
