package ca.ece.ubc.cpen221.mp5.statlearning;

import org.json.simple.JSONArray;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class CategoryFeature implements MP5Function{
	
	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		
		JSONArray ja = yelpRestaurant.categories;
		double result = 0.0;
		String cat = ja.get(0).toString();
		
		if (cat.equals("Cafes"))
			result = 0;
		else if (cat.equals("Thai"))
			result = 1;
		else if (cat.equals("Fondue"))
			result = 2;
		else if (cat.equals("Chinese"))
			result = 3;
		else if (cat.equals("Bars"))
			result = 4;
			
		return result;
		
	}
	
}