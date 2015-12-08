// Generated from RestaurantFormula.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5.restaurantFormula;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RestaurantFormulaParser}.
 */
public interface RestaurantFormulaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RestaurantFormulaParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(@NotNull RestaurantFormulaParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link RestaurantFormulaParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(@NotNull RestaurantFormulaParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link RestaurantFormulaParser#price}.
	 * @param ctx the parse tree
	 */
	void enterPrice(@NotNull RestaurantFormulaParser.PriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RestaurantFormulaParser#price}.
	 * @param ctx the parse tree
	 */
	void exitPrice(@NotNull RestaurantFormulaParser.PriceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RestaurantFormulaParser#orexpr}.
	 * @param ctx the parse tree
	 */
	void enterOrexpr(@NotNull RestaurantFormulaParser.OrexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RestaurantFormulaParser#orexpr}.
	 * @param ctx the parse tree
	 */
	void exitOrexpr(@NotNull RestaurantFormulaParser.OrexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RestaurantFormulaParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(@NotNull RestaurantFormulaParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RestaurantFormulaParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(@NotNull RestaurantFormulaParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RestaurantFormulaParser#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(@NotNull RestaurantFormulaParser.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link RestaurantFormulaParser#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(@NotNull RestaurantFormulaParser.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link RestaurantFormulaParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull RestaurantFormulaParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link RestaurantFormulaParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull RestaurantFormulaParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link RestaurantFormulaParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(@NotNull RestaurantFormulaParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RestaurantFormulaParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(@NotNull RestaurantFormulaParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RestaurantFormulaParser#andexpr}.
	 * @param ctx the parse tree
	 */
	void enterAndexpr(@NotNull RestaurantFormulaParser.AndexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RestaurantFormulaParser#andexpr}.
	 * @param ctx the parse tree
	 */
	void exitAndexpr(@NotNull RestaurantFormulaParser.AndexprContext ctx);
}