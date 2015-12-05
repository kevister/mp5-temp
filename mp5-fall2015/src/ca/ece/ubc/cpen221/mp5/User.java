package ca.ece.ubc.cpen221.mp5;

// TODO: Use this class to represent a Yelp user.

public class User {

	private String url;
	private Object votes;
	private long review_count;
	private String type;
	private String user_id;
	private String name;
	private double average_stars;

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
