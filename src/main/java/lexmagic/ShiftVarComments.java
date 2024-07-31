package lexmagic;

import java.util.List;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class ShiftVarComments  extends SymbolBaseListener {


	BufferedTokenStream tokens;
	TokenStreamRewriter rewriter;

	public ShiftVarComments(BufferedTokenStream tokens) {
		

		this.tokens = tokens;
		rewriter = new TokenStreamRewriter(tokens);
		
	}
	@Override
	public void exitVarDecl(lexmagic.SymbolParser.VarDeclContext ctx) {

		
		Token semi = ctx.getStop();
		
		int i = semi.getTokenIndex();
		List<Token> cmtChannerl = tokens.getHiddenTokensToRight(i,SymbolLexer.COMMENTS);
		
		System.out.println("cmtChannerl_________"+cmtChannerl);
		
		if( cmtChannerl != null ) {
			
			Token cmt = cmtChannerl.get(0);
			
			if( cmt != null ) {
				
				
				System.out.println("txt____["+cmt.getText());
				String txt = cmt.getText().substring(2); // means , get all after //

				//System.out.println("-------"+txt);
				
				String newCmt = "/*" + txt.trim() + "*/\n";
				rewriter.insertBefore(ctx.start, newCmt);
				rewriter.replace(cmt, "\n");
			}
		}
		
	
		super.exitVarDecl(ctx);
	}
	
	
	
	
	


}
