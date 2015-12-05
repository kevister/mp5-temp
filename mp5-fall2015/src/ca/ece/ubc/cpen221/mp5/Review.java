package ca.ece.ubc.cpen221.mp5;

import org.json.simple.JSONArray;

// TODO: Use this class to represent a Yelp review.

public class Review {

	private String type;
	private String business_id;
	private Object votes;
	private String review_id;
	private String text;
	private long stars;
	private String user_id;
	private String date;

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
}
