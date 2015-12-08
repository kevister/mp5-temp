package ca.ece.ubc.cpen221.mp5;

//This class represents a Yelp User with the information discussed in the constructor method below.
//Abstraction function: represents a Yelp User with given information
//Rep invariant:				  
//				the votes corresponding to this review, must be in format {"cool": 0, "useful": 0, "funny": 0}.
//					with numbers corresponding to cool, useful, and funny >=0 (no downvoting)
//				  review count >= 0
//				  each User must have a unique user_id
//				  average stars given must be 1<= average_stars <=5
//				  type must be "user"

public class User {

	public String url;
	public Object votes;
	public long review_count;
	public String type;
	public String user_id;
	public String name;
	public double average_stars;

	/**
	 * Construct a new User object.
	 * 
	 * @param url the url representing the yelp.com web page of this user
	 * @param votes the votes corresponding to this user, must be in format {"cool": 0, "useful": 0, "funny": 0}.
	 * 				with numbers corresponding to cool, useful, and funny >=0 (no downvoting)
	 * @param review_count the number of reviews this user has given, must be >= 0
	 * @param type the type of this user, must be "user"
	 * @param user_id the user id of this user, must be unique
	 * @param name the name of this user
	 * @param average_stars the average stars given out by this user, must be 1 <= stars <= 5
	 */
	public User(String url, Object votes, long review_count, String type, String user_id, String name,
			double average_stars) {

		this.url = url;
		this.votes = votes;
		this.review_count = review_count;
		this.type = type;
		this.user_id = user_id;
		this.name = name;
		this.average_stars = average_stars;

	}

}
