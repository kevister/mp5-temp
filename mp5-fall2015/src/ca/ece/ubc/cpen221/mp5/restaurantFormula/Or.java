package ca.ece.ubc.cpen221.mp5.restaurantFormula;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class Or implements RestaurantFormula {

	public List<RestaurantFormula> formulaList;

	/**
	 * Create an Or.
	 * 
	 * @param formulaList a list of RestaurantFormulas, each containing a method which computes a 
	 * set of Restaurants with some common property.
	 */
	public Or(List<RestaurantFormula> formulaList) {
		this.formulaList = formulaList;
	}

	/**
	 * @return This method returns a set of Restaurants which represents all 
	 * of the sets of Restaurants (contained in RestaurantFormulas in formulaList)
	 * OR'd together (Returns a set of Restaurants containing any one queried quality).
	 */
	@Override
	public Set<Restaurant> getRelevant() {
		// initialize outputSet with restaurants obtained from first formula
		// our goal here is to OR all restaurant lists together, that is every restaurant
		// found in children lists will be found in this output list (no duplicates)
		HashSet<Restaurant> outputSet = new HashSet<Restaurant>(formulaList.get(0).getRelevant());
		for (int index = 1; index < formulaList.size(); index++) {
			outputSet.addAll(formulaList.get(index).getRelevant());
		}

		return new HashSet<Restaurant>(outputSet);
	}
}
