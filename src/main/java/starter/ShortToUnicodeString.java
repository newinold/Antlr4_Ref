package starter;

import starter.ArrayInitParser.InitContext;
import starter.ArrayInitParser.ValueContext;

public class ShortToUnicodeString extends ArrayInitBaseListener {
	
	
	@Override
	public void enterInit(InitContext ctx) {
	
		System.out.println('"');
		super.enterInit(ctx);
	
	}
	
	@Override
	public void exitInit(InitContext ctx) {
		
		System.out.println('"');
		super.exitInit(ctx);
	
	}
	
	@Override
	public void enterValue(ValueContext ctx) {
		
		int value = Integer.valueOf(ctx.INT().getText());
		System.out.printf("\\u%04x",value);
		
		super.enterValue(ctx);
	}

}
