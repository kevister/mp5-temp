package ca.ece.ubc.cpen221.mp5;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

// This class represents a restaurant with the information discussed in the constructor method below.
// Abstraction function: represents a restaurant on the planet earth with given information
// Rep invariant: -180 <= longitude <= 180
//				  -90  <= latitude  <= 90
//				  contents of neighborhoods, state, city, full_address, and schools
//				  must be valid for the planet earth (must exist)
//				  1 <= stars <= 5
//				  review count >= 0
//				  1 <= price <= 5
//				  each Restaurant must have a unique business_id


public class Restaurant {

	public Boolean open;
	public String url;
	public Double longitude;
	public JSONArray neighborhoods;
	public String business_id;
	public String name;
	public JSONArray categories;
	public String state;
	public String type;
	public Double stars;
	public String city;
	public String full_address;
	public Long review_count;
	public String photo_url;
	public JSONArray schools;
	public Double latitude;
	public Long price;

	/**
	 * Construct a new Restaurant object.
	 * 
	 * @param open whether or not the restaurant is open
	 * @param url the url of yelp.com page for this restaurant
	 * @param longitude the longitude of this restaurant, must be within -180 to 180 inclusive
	 * @param neighborhoods the neighborhoods in which this restaurant belongs to
	 * @param business_id the business ID of this restaurant, must be unique
	 * @param name the name of this restaurant
	 * @param categories the categories in which this restaurant belongs to
	 * @param state the state in which this restaurant belongs to
	 * @param type the type of this restaurant eg. "business" or "non-profit" etc
	 * @param stars the average stars given to this restaurant by reviews, must be 0<= stars <= 5
	 * @param city the city in which this restaurant exists
	 * @param full_address the full street address of this restaurant
	 * @param review_count the number of reviews this restaurant has, must be >= 0
	 * @param photo_url the url of the yelp.com image for this restaurant
	 * @param schools the schools around which this restaurant is found
	 * @param latitude the latitude of this restaurant, must be within -90 to 90 inclusive 
	 * @param price the price of this restaurant, must be an integer from 1 to 5
	 */
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
	
	/**
	 * Returns an ordered String in JSON format containing details about this restaurant
	 * The order of keys in the returned JSON string is as follows:
	 *  "open" , "url" , "longitude" , "neighborhoods", "business_id" , "name" , 
	 *  "categories" , "state" , "type" , "stars" , "city" , "full_address" , "review_count"
	 *  "photo_url" , "schools" , "latitude" , "price"
	 *  
	 * @return an (ordered) String in JSON format
	 * containing details about this restaurant
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String toJSONString(){
		Map obj = new LinkedHashMap();		
		obj.put("open", new Boolean(true));			//make a JSON formatted string
		obj.put("url", url);
		obj.put("longitude", longitude);
		obj.put("neighborhoods", neighborhoods);
		obj.put("business_id", business_id);
		obj.put("name", name);
		obj.put("categories", categories);
		obj.put("state", state);
		obj.put("type", type);
		obj.put("stars", stars);
		obj.put("city", city);
		obj.put("full_address", full_address);
		obj.put("review_count", review_count);
		obj.put("photo_url", photo_url);
		obj.put("schools", schools);
		obj.put("latitude", latitude);
		obj.put("price", price);

		String jsonText = JSONValue.toJSONString(obj);
		return jsonText;
	}
	
}
