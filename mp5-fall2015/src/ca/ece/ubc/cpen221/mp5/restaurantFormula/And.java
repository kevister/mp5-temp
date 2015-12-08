package ca.ece.ubc.cpen221.mp5.restaurantFormula;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class And implements RestaurantFormula {

	private final List<RestaurantFormula> formulaList;

	/**
	 * Create an And.
	 * 
	 * @param formulaList a list of RestaurantFormulas, each containing a method which computes a
	 * set of Restaurants with some common property.
	 */
	public And(List<RestaurantFormula> formulaList) {
		this.formulaList = formulaList;
	}

	/**
	 * 
	 * @return This method returns a set of Restaurants which represents all 
	 * of the sets of Restaurants (contained in RestaurantFormulas in formulaList)
	 * ANDed together (Returns a set of Restaurants containing all queried qualities).
	 */
	@Override
	public Set<Restaurant> getRelevant() {
		// initialize outputSet with restaurants obtained from first formula
		// our goal here is to AND all restaurant lists together, that is each
		// restaurant in the output list must have all common qualities found in each
		// individual list from children
		HashSet<Restaurant> outputSet = new HashSet<Restaurant>(formulaList.get(0).getRelevant());
		for(int index = 1 ; index < formulaList.size(); index++){
			outputSet.retainAll(formulaList.get(index).getRelevant());
		}

		return new HashSet<Restaurant>(outputSet);
	}
}
