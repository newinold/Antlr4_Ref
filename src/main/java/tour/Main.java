package tour;

import org.antlr.v4.gui.TestRig;

public class Main {

	public static void main(String[] args) throws Exception {

		
		//String[] args2 = {"com.hillssoft.Hello","r","-tokens"};

		//String[] args2 = {"tour.Expr","prog","-gui","C:\\Users\\newin\\eclipse-workspace\\hillssoft\\src\\main\\java\\tour\\t.expr"};
		String[] args2 = {"tour.LibExpr","prog","-tree"};

		TestRig.main(args2);
	}

}
