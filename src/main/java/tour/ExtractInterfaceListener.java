package tour;


import org.antlr.v4.runtime.TokenStream;

import tour.JavaParser.ClassDeclarationContext;
import tour.JavaParser.MethodDeclarationContext;

public class ExtractInterfaceListener extends JavaBaseListener {
	
	JavaParser parser;
	public ExtractInterfaceListener(JavaParser parser) {
		this.parser = parser;
	}
	
	@Override
	public void enterClassDeclaration(ClassDeclarationContext ctx) {
		
		System.out.println("interface /"+ctx.Identifier()+" {");
		super.enterClassDeclaration(ctx);
	}
	
	@Override
	public void exitClassDeclaration(ClassDeclarationContext ctx) {
		System.out.println("}");
		super.exitClassDeclaration(ctx);
	}
	
	@Override
	public void enterMethodDeclaration(MethodDeclarationContext ctx) {
		
		TokenStream tokens = parser.getTokenStream();
		
		String type = "void";
		
		if( ctx.type() != null) {
			type = tokens.getText(ctx.type());
		}
		
		String args = tokens.getText(ctx.formalParameters());
		System.out.println("\t"+type+" "+ctx.Identifier() + args +";");
		
		super.enterMethodDeclaration(ctx);
	}

}
