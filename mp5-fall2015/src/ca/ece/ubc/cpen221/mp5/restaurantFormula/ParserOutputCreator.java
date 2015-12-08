package ca.ece.ubc.cpen221.mp5.restaurantFormula;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class ParserOutputCreator {
	/**
     * @param string must contain a well-formed formula string of operators and query elements.
     * 
     * Acceptable queries have elements in the form (conjoined by && and || and () operators):
     * in("Some Area")
     * category("Some Category")
     * name("Some Name")
     * rating([1-5]..[1-5]) or rating([1-5])
     * price([1-5]..[1-5]) or price([1-5])
     * 
     * For example: in("Telegraph Ave") && (category("Chinese") || category("Italian")) && price(1..2)
     * returns a set of all Chinese or Italian restaurants in the Telegraph Avenue 
     * neighborhood that are in the price range [1,2].
     * 
     * @param restaurantData a list of Restaurants which serves as the database on which to retrieve
     * information from
     * @return RestaurantFormula corresponding to the string
     */
    public static RestaurantFormula parse(String string, List<Restaurant> restaurantData) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(string);
        RestaurantFormulaLexer lexer = new RestaurantFormulaLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        RestaurantFormulaParser parser = new RestaurantFormulaParser(tokens);
        parser.reportErrorsAsExceptions();
        
        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.orexpr(); // "root" is the starter rule.
        
        // Finally, construct a Document value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        RestaurantFormulaCreator listener = new RestaurantFormulaCreator(restaurantData);
        walker.walk(listener, tree);
        
        // return the Document value that the listener created
        return listener.getFormula();
    }
}


