// Generated from listener\PropertyFileListener.g4 by ANTLR 4.9.2
package listener;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PropertyFileListenerParser}.
 */
public interface PropertyFileListenerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PropertyFileListenerParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(PropertyFileListenerParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyFileListenerParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(PropertyFileListenerParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyFileListenerParser#prop}.
	 * @param ctx the parse tree
	 */
	void enterProp(PropertyFileListenerParser.PropContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyFileListenerParser#prop}.
	 * @param ctx the parse tree
	 */
	void exitProp(PropertyFileListenerParser.PropContext ctx);
}