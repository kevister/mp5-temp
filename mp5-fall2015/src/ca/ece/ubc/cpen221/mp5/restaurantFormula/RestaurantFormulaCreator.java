package ca.ece.ubc.cpen221.mp5.restaurantFormula;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.antlr.v4.runtime.misc.NotNull;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class RestaurantFormulaCreator extends RestaurantFormulaBaseListener {

	List<Restaurant> restaurantData;
	private Stack<RestaurantFormula> stack = new Stack<RestaurantFormula>();

	/**
	 * Construct a new RestaurantFormulaCreator.
	 * 
	 * @param restaurantData a list of Restaurants which serves as the database on which to retrieve
     * information from.
	 */
	public RestaurantFormulaCreator(List<Restaurant> restaurantData) {
		this.restaurantData = restaurantData;
	}

	/**
	 * Creates a RestaurantFormulaList containing a set of Restaurants in some neighborhood.
	 */
	@Override
	public void exitIn(@NotNull RestaurantFormulaParser.InContext ctx) {
		// retrieve the neighborhood of the query
		Object object = ctx.STRING().toString().replaceAll("^\"|\"$", "");
		Set<Restaurant> matchingNeighborhoods = new HashSet<Restaurant>();

		// search database for matching neighborhoods. If the neighborhood
		// matches, add it
		// to a set of restaurants representing restaurants which match this
		// query
		for (Restaurant thisRestaurant : restaurantData) {
			for (Object neighborhood : thisRestaurant.neighborhoods) {
				if (object.equals(neighborhood)) {
					matchingNeighborhoods.add(thisRestaurant);
				}
			}
		}
		RestaurantFormula neighborhoods = new RestaurantFormulaList(matchingNeighborhoods);
		stack.push(neighborhoods);
	}

	/**
	 * Creates a RestaurantFormulaList containing a set of Restaurants in some price range.
	 */
	@Override
	public void exitPrice(@NotNull RestaurantFormulaParser.PriceContext ctx) {
		// get integer values of price query bounds
		String price = ctx.RANGE().toString();
		int lowerBound = price.charAt(0) - '0';
		int upperBound = price.charAt(price.length() - 1) - '0';
		Set<Restaurant> matchingPrices = new HashSet<Restaurant>();

		// search database for matching prices. If the price matches, add this
		// restaurant to a set of restaurants representing restaurants which
		// match this
		// query
		for (Restaurant thisRestaurant : restaurantData) {
			if (thisRestaurant.price >= lowerBound && thisRestaurant.price <= upperBound) {
				matchingPrices.add(thisRestaurant);
			}
		}
		RestaurantFormula prices = new RestaurantFormulaList(matchingPrices);
		stack.push(prices);
	}

	/**
	 * Creates an Or containing a list of all RestaurantFormulas in its direct child nodes.
	 */
	@Override
	public void exitOrexpr(@NotNull RestaurantFormulaParser.OrexprContext ctx) {
		if (ctx.getChildCount() != 1) {
			// we matched the OR rule, so pop everything off of the stack,
			// and make a new OR object with a list that contains all
			// RestaurantFormulas
			// just popped
			
			List<RestaurantFormula> orChildren = new ArrayList<RestaurantFormula>();
			
			//pop all children of this node off the stack
			for (int index = 0; index < ctx.children.size() - ctx.OR().size(); index++) {
				orChildren.add(stack.pop());
			}
			// construct new OR object and then push it back onto the stack
			RestaurantFormula or = new Or(orChildren);
			stack.push(or);
		
		} else {
			// do nothing and retain stack
		}
	}

	/**
	 * Creates a RestaurantFormulaList containing a set of Restaurants with some name.
	 */
	@Override
	public void exitName(@NotNull RestaurantFormulaParser.NameContext ctx) {
		// retrieve the name of the restaurant queried
		String string = ctx.STRING().toString().replaceAll("^\"|\"$", "");
		Set<Restaurant> matchingNames = new HashSet<Restaurant>();

		// search database for matching names. If the name matches, add it
		// to a set of restaurants representing restaurants which match this
		// query
		for (Restaurant thisRestaurant : restaurantData) {
			if (thisRestaurant.name.equals(string)) {
				matchingNames.add(thisRestaurant);
			}
		}
		RestaurantFormula name = new RestaurantFormulaList(matchingNames);
		stack.push(name);
	}

	/**
	 * Creates a RestaurantFormulaList containing a set of Restaurants in some rating range.
	 */
	@Override
	public void exitRating(@NotNull RestaurantFormulaParser.RatingContext ctx) {
		// get integer values of rating query bounds
		String ratingRange = ctx.RANGE().toString();
		double lowerBound = (double) ratingRange.charAt(0) - '0';
		double upperBound = (double) ratingRange.charAt(ratingRange.length() - 1) - '0';
		Set<Restaurant> matchingRatings = new HashSet<Restaurant>();

		// search database for matching prices. If the price matches, add this
		// restaurant to a set of restaurants representing restaurants which
		// match this
		// query
		for (Restaurant thisRestaurant : restaurantData) {
			if (thisRestaurant.stars >= lowerBound && thisRestaurant.stars <= upperBound) {
				matchingRatings.add(thisRestaurant);
			}
		}
		RestaurantFormula rating = new RestaurantFormulaList(matchingRatings);
		stack.push(rating);
	}

	/**
	 * Creates a RestaurantFormulaList containing a set of Restaurants in some category.
	 */
	@Override
	public void exitCategory(@NotNull RestaurantFormulaParser.CategoryContext ctx) {
		// retrieve the category of the restaurant queried
		Object object = ctx.STRING().toString().replaceAll("^\"|\"$", "");
		Set<Restaurant> matchingCategory = new HashSet<Restaurant>();

		// search database for matching categories. If the category matches, add
		// it
		// to a set of restaurants representing restaurants which match this
		// query
		for (Restaurant thisRestaurant : restaurantData) {
			for (Object category : thisRestaurant.categories) {
				if (object.equals(category)) {
					matchingCategory.add(thisRestaurant);
				}
			}
		}
		RestaurantFormula category = new RestaurantFormulaList(matchingCategory);
		stack.push(category);
	}

	/**
	 * Creates an And containing a list of all RestaurantFormulas in its direct child nodes.
	 */
	@Override
	public void exitAndexpr(@NotNull RestaurantFormulaParser.AndexprContext ctx) {
		if (ctx.getChildCount() != 1) {
			// we matched the AND rule, so pop everything off of the stack,
			// and make a new And object with a list that contains all
			// RestaurantFormulas just popped
		
			//pop all children of this node off the stack
			List<RestaurantFormula> andChildren = new ArrayList<RestaurantFormula>();
			for (int index = 0; index < ctx.children.size() - ctx.AND().size(); index++) {
				andChildren.add(stack.pop());
			}
			// construct new And object and then push it back onto the stack
			RestaurantFormula and = new And(andChildren);
			stack.push(and);
			
		} else {
			// do nothing and retain stack
		}
	}

	/**
	 * 
	 * @return RestaurantFormula the formula on the top of the stack, which is the 
	 * RestaurantFormula which will create the Set<Restaurant> which matches the query.
	 */
	public RestaurantFormula getFormula() {
		return stack.get(0);
	}
}
