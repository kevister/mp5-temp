package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;
import ca.ece.ubc.cpen221.mp5.Review;

public class MeanRatingFeature implements MP5Function{
	
	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		double meanRating = 0.0;
		double count = 0.0;
		
		for(Review rev : db.getReviewData()) {
			
			if (rev.business_id == yelpRestaurant.business_id) {
				meanRating += rev.stars;
				count++;
			}
			
		}
		
		meanRating /= count;
		
		return meanRating;
	}
	
}