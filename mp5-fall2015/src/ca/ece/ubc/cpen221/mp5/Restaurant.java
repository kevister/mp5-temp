package ca.ece.ubc.cpen221.mp5;

import org.json.simple.JSONArray;

// TODO: Use this class to represent a restaurant.
// State the rep invariant and abs

public class Restaurant {

	private boolean open;
	private String url;
	public double longitude;
	private JSONArray neighborhoods;
	private String business_id;
	public String name;
	private JSONArray categories;
	private String state;
	private String type;
	private double stars;
	private String city;
	private String full_address;
	private long review_count;
	private String photo_url;
	private JSONArray schools;
	public double latitude;
	private long price;

	public Restaurant(boolean open, String url, double longitude, JSONArray neighborhoods, 
			String business_id, String name, JSONArray categories, String state, String type,
			double stars, String city, String full_address, long review_count, String photo_url,
			JSONArray schools, double latitude, long price){
		
		this.open = open;
		this.url = url;
		this.longitude = longitude;
		this.neighborhoods = neighborhoods;
		this.business_id = business_id;
		this.name = name;
		this.categories = categories;
		this.state = state;
		this.type = type;
		this.stars = stars;
		this.city = city;
		this.full_address = full_address;
		this.review_count = review_count;
		this.photo_url = photo_url;
		this.schools = schools;
		this.latitude = latitude;
		this.price = price;
		
	}
	
}
