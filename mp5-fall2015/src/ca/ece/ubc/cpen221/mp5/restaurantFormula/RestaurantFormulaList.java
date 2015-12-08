package ca.ece.ubc.cpen221.mp5.restaurantFormula;

import java.util.HashSet;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class RestaurantFormulaList implements RestaurantFormula {
	
	private final Set<Restaurant> relevantSet;

	/**
	 * Create a RestaurantFormulaList.
	 * 
	 * @param relevantSet the set of Restaurants with some common property,
	 * to do something with depending on the 
	 * implementing class of RestaurantFormula.
	 */
	public RestaurantFormulaList(Set<Restaurant> relevantSet){
		this.relevantSet = relevantSet;
	}
	
	/**
	 * @return This method simply returns the contained set of Restaurants with some
	 * common property.
	 */
	@Override
	public Set<Restaurant> getRelevant() {
		// return a copy of the hashSet
		return new HashSet<Restaurant>(relevantSet);
	}

}
