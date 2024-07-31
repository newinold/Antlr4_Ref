package install;

import org.antlr.v4.gui.TestRig;

public class Grun {

	public static void main(String[] args) throws Exception {

		

		String[] args2 = {"install.Hello","r","-tokens"};
		
		String[] args3 = {"install.Hello","r","-tree"};

		String[] args4 = {"install.Hello","r","-gui"};

		TestRig.main(args4);
	}

}
