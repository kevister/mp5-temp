package ca.ece.ubc.cpen221.mp5;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONValue;

//This class represents a Yelp review with the information discussed in the constructor method below.
//Abstraction function: represents a review of a given restaurant with given information
//Rep invariant: 
//				  the votes corresponding to this review, must be in format {"cool": 0, "useful": 0, "funny": 0}.
//						with numbers corresponding to cool, useful, and funny >=0 (no downvoting)
//				  the business ID of the reviewed restaurant must be in the database
//				  each Review must have a unique review ID
//				  the user ID of the reviewing user must be in the database
//				  stars given must be 1<= stars <=5
//				  type must be "review"


public class Review {

	public String type;
	public String business_id;
	public Object votes;
	public String review_id;
	public String text;
	public long stars;
	public String user_id;
	public String date;

	/**
	 * Construct a new Review object.
	 * 
	 * @param type the type of this review, must be "review"
	 * @param business_id the business id of the restaurant being reviewed.  Must be a valid business id
	 * 						which represents a restaurant which exists in the database
	 * @param votes	the votes corresponding to this review, must be in format {"cool": 0, "useful": 0, "funny": 0}.
	 * 						with numbers corresponding to cool, useful, and funny >=0 (no downvoting)
	 * @param review_id	  the review id of this review, must be unique
	 * @param text		  the text comments of this review
	 * @param stars		  the stars given to the reviewed restaurant, must be 1 <= stars <= 5
	 * @param user_id	  the user_id of the user giving the review.  Must be a valid user ID which represents
	 * 						a user which exists in the database
	 * @param date		  the date at which the review was given, in format YYYY-MM-DD.
	 * 						must be a valid date.
	 */
	public Review(String type, String business_id, Object votes, String review_id, String text, long stars,
			String user_id, String date) {

		this.type = type;
		this.business_id = business_id;
		this.votes = votes;
		this.review_id = review_id;
		this.text = text;
		this.stars = stars;
		this.user_id = user_id;
		this.date = date;
	}

	/**
	 * Returns an ordered String in JSON format containing details about this review
	 * The order of keys in the returned JSON string is as follows:
	 *  "tyype" , "business_id" , "votes" , "review_id", "text" , "stars" , 
	 *  "user_id" , "date"
	 *  
	 * @return an (ordered) String in JSON format
	 * containing details about this review
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String toJSONString() {
		Map obj = new LinkedHashMap();		//make a JSON formatted string
		obj.put("type", type);
		obj.put("business_id", business_id);
		obj.put("votes", votes);
		obj.put("review_id", review_id);
		obj.put("text", text);
		obj.put("stars", stars);
		obj.put("user_id", user_id);
		obj.put("date", date);

		String jsonText = JSONValue.toJSONString(obj);
		return jsonText;
	}
}
