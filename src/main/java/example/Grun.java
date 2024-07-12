package example;

import org.antlr.v4.gui.TestRig;

public class Grun {

	public static void main(String[] args) throws Exception {

		
		//String[] args2 = {"com.hillssoft.Hello","r","-tokens"};

		String[] args2 = {"example.CSV","file","-tokens","files/examples/data.csv"};

		TestRig.main(args2);
	}

}
