package listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import listener.CSVParser.EmptyContext;
import listener.CSVParser.HdrContext;
import listener.CSVParser.RowContext;
import listener.CSVParser.StringContext;
import listener.CSVParser.TextContext;

public class LoadCSV {

	
	public static class Loader extends CSVBaseListener {
		
		public static final String EMPTYP ="";
		
		List<Map<String,String>> rows = new ArrayList<Map<String,String>>();
		
		List<String> header;
		
		List<String> currentRowFieldValues;
		
		@Override
		public void exitString(StringContext ctx) {
			
			currentRowFieldValues.add(ctx.STRING().getText());
			super.exitString(ctx);
		}
		
		@Override
		public void exitText(TextContext ctx) {
			// TODO Auto-generated method stub
			currentRowFieldValues.add(ctx.TEXT().getText());
			super.exitText(ctx);
		}
		
		
		@Override
		public void exitEmpty(EmptyContext ctx) {
			// TODO Auto-generated method stub
			currentRowFieldValues.add(EMPTYP);
			super.exitEmpty(ctx);
		}
		
		@Override
		public void exitHdr(HdrContext ctx) {
			
			header = new ArrayList<String>();
			header.addAll(currentRowFieldValues);
			
			super.exitHdr(ctx);
		}
		
		@Override
		public void enterRow(RowContext ctx) {
			// TODO Auto-generated method stub
			
			currentRowFieldValues = new ArrayList<String>();
			
			super.enterRow(ctx);
		}
		
		@Override
		public void exitRow(RowContext ctx) {
			// TODO Auto-generated method stub
			if( ctx.getParent().getRuleIndex() == CSVParser.RULE_hdr) return;
			
			Map<String,String> m = new LinkedHashMap<String, String>();
			
			int i =0 ; 
			
			for( String v : currentRowFieldValues) {
				m.put(header.get(i), v);
				i++;
			}
			rows.add(m);
			super.exitRow(ctx);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String inputFile = "files/listener/t.csv";
		if( args.length > 0) inputFile = args[0];
		InputStream is = System.in;
		
		if(inputFile != null ) {
			is = new FileInputStream(inputFile);
		}
		
		CharStream chr = CharStreams.fromStream(is);
		CSVLexer lexer = new CSVLexer(chr);
		CommonTokenStream  token = new CommonTokenStream(lexer);
		
		CSVParser parser = new CSVParser(token);
		parser.setBuildParseTree(true);
		ParseTree tree =  parser.file();
		
		Loader loader = new Loader();
		
		
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(loader, tree);

		System.out.println(loader.rows);

	}

}
