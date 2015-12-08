package ca.ece.ubc.cpen221.mp5.restaurantFormula;

import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public interface RestaurantFormula {

	/**
	 * Returns a set of relevant restaurants based on the implementing class,
	 * i.e. restaurants in some category, some price range, some name, or
	 * some neighborhood.
	 * 
	 * @return Set<Restaurant> returns 
	 * relevant restaurants based on the implementing class, ie. an implementing class
	 * And returns valid restaurants with common qualities and an implementing class
	 * Or returns valid restaurants with any one of the desired qualities
	 * Returns a set, so duplicate restaurants are not allowed / possible.
	 */
	public Set<Restaurant> getRelevant();
	
}
