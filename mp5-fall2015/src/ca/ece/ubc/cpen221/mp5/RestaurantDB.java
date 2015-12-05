package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.simple.*;
import org.json.simple.parser.JSONParser;

// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.

public class RestaurantDB {

	private List<Restaurant> restaurantData;
	private List<Review> reviewData;
	private List<User> userData;

	/**
	 * Create a database from the Yelp dataset given the names of three files:
	 * <ul>
	 * <li>One that contains data about the restaurants;</li>
	 * <li>One that contains reviews of the restaurants;</li>
	 * <li>One that contains information about the users that submitted reviews.
	 * </li>
	 * </ul>
	 * The files contain data in JSON format.
	 * 
	 * @param restaurantJSONfilename
	 *            the filename for the restaurant data
	 * @param reviewsJSONfilename
	 *            the filename for the reviews
	 * @param usersJSONfilename
	 *            the filename for the users
	 */
	public RestaurantDB(String restaurantJSONfilename, String reviewsJSONfilename, 
			String usersJSONfilename){
																																
		// create list of restaurants for the database
		restaurantData = new ArrayList<Restaurant>();

		JSONParser parser = new JSONParser();
		try {
			FileReader fr = new FileReader(restaurantJSONfilename);
			BufferedReader br = new BufferedReader(fr);
			String line;

			while ((line = br.readLine()) != null) {

				Object obj = parser.parse(line);

				// each new line is a new JSONObject representing a restaurant
				JSONObject jsonObject = (JSONObject) obj;

				// retrieve data from each restaurant and enter it into the
				// database by creating
				// a new restaurant with extracted details
				Boolean open = (Boolean) jsonObject.get("open");
				String url = (String) jsonObject.get("url");
				Double longitude = (Double) jsonObject.get("longitude");
				JSONArray neighborhoods = (JSONArray) jsonObject.get("neighborhoods");
				String business_id = (String) jsonObject.get("business_id");
				String name = (String) jsonObject.get("name");
				JSONArray categories = (JSONArray) jsonObject.get("categories");
				String state = (String) jsonObject.get("state");
				String type = (String) jsonObject.get("type");
				Double stars = (Double) jsonObject.get("stars");
				String city = (String) jsonObject.get("city");
				String full_address = (String) jsonObject.get("full_address");
				Long review_count = (Long) jsonObject.get("review_count");
				String photo_url = (String) jsonObject.get("photo_url");
				JSONArray schools = (JSONArray) jsonObject.get("schools");
				Double latitude = (Double) jsonObject.get("latitude");
				Long price = (Long) jsonObject.get("price");

				// create a new Restaurant with extracted details
				Restaurant restaurant = new Restaurant(open, url, longitude, neighborhoods, business_id, name,
						categories, state, type, stars, city, full_address, review_count, photo_url, schools, latitude,
						price);

				//add this Restaurant to the restaurantData
				restaurantData.add(restaurant);
				//System.out.println(restaurantData.size()); // for debugging

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// create list of reviews for the database
		reviewData = new ArrayList<Review>();

		try {
			FileReader fr = new FileReader(reviewsJSONfilename);
			BufferedReader br = new BufferedReader(fr);
			String line;

			while ((line = br.readLine()) != null) {

				Object obj = parser.parse(line);

				// each new line is a new JSONObject representing a review
				JSONObject jsonObject = (JSONObject) obj;

				// retrieve data from each review and enter it into the
				// database by creating
				// a new Review object with extracted details
				String type = (String) jsonObject.get("type");
				String business_id = (String) jsonObject.get("business_id");
				Object votes = jsonObject.get("votes");
				String review_id = (String) jsonObject.get("review_id");
				String text = (String) jsonObject.get("text");
				Long stars = (Long) jsonObject.get("stars");
				String user_id = (String) jsonObject.get("user_id");
				String date = (String) jsonObject.get("date");

				// create a new Review with extracted details
				Review review = new Review(type, business_id, votes, review_id, text, stars, user_id, date);

				//add this review to the reviewData
				reviewData.add(review);
				//System.out.println(reviewData.size()); // for debugging

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// create list of users for the database
		userData = new ArrayList<User>();

		try {
			FileReader fr = new FileReader(usersJSONfilename);
			BufferedReader br = new BufferedReader(fr);
			String line;

			while ((line = br.readLine()) != null) {

				Object obj = parser.parse(line);

				// each new line is a new JSONObject representing a user
				JSONObject jsonObject = (JSONObject) obj;

				// retrieve data from each User and enter it into the
				// database by creating
				// a new User with extracted details
				String url = (String) jsonObject.get("url");
				Object votes = jsonObject.get("votes");		
				Long review_count = (Long) jsonObject.get("review_count");
				String type = (String) jsonObject.get("type");
				String user_id = (String) jsonObject.get("user_id");
				String name = (String) jsonObject.get("name");
				double average_stars = (Double) jsonObject.get("average_stars");

				// create a new User with extracted details
				User user = new User(url, votes, review_count, type, user_id, name, average_stars);

				//add this User to the userData
				userData.add(user);
				//System.out.println(userData.size()); // for debugging

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// get restaurant data... for testing purposes
	public List<Restaurant> getRestaurantData() {
		return restaurantData;
	}

	// get review data... for testing purposes
	public List<Review> getReviewData() {
		return reviewData;
	}

	// get user data... for testing purposes
	public List<User> getUserData() {
		return userData;
	}

	public Set<Restaurant> query(String queryString) {
		// TODO: Implement this method
		// Write specs, etc.
		return null;
	}
}
