package tour;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

import tour.JavaParser.ClassBodyContext;
import tour.JavaParser.CompilationUnitContext;

public class InsertSerialDlistener extends JavaBaseListener  {
	
	
	TokenStreamRewriter rewriter;
	
	public InsertSerialDlistener(TokenStream tokens) {
		rewriter = new TokenStreamRewriter(tokens);
	}
	
	@Override
	public void enterClassBody(ClassBodyContext ctx) {
		
		String field = "\n\tpublic static final long serialVersionUID =1L;";
		
		rewriter.insertAfter(ctx.start, field);
		
		super.enterClassBody(ctx);
	}
	
	@Override
	public void enterCompilationUnit(CompilationUnitContext ctx) {
		String comments ="/* Demo page*/\n";
		
		rewriter.insertBefore(ctx.start, comments);
		
		// TODO Auto-generated method stub
		super.enterCompilationUnit(ctx);
	}
	
	

}
