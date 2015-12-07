package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class RegressionFunction implements MP5Function{
	
	public double a;
	public double b;
	public double R_sqr;
	
	public RegressionFunction(double b, double a, double R_sqr) {
		
		this.a = a;
		this.b = b;
		this.R_sqr = R_sqr;
		
	}
	
	public RegressionFunction() {
		
		this.a = 0.0;
		this.b = 0.0;
		this.R_sqr = 0.0;
		
	}

	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		return 0;		//not a feature function
	}
	
}