package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ca.ece.ubc.cpen221.mp5.restaurantFormula.ParserOutputCreator;
import ca.ece.ubc.cpen221.mp5.restaurantFormula.RestaurantFormula;

// Abstraction Function: This class represents a database which contains
// information on restaurants on planet earth, information on users of Yelp.com which
// rate these restaurants, and reviews on Yelp.com about these restaurants, written by the users.
//
// The database is represented by three lists, 
// restaurantData: contains a list of Restaurant objects
// reviewData: contains a list of Review objects
// userData: contains a list of User objects
//
// Rep invariant: each Restaurant in restaurantData is unique, ie. has a unique business ID
//				  each Review in reviewData is unique, ie. has a unique review ID
//				  for every Review given by some User, that User must be in userData
//				  for ever Review on some Restaurant, that restaurant must be in restaurantData
//				  each User in userData is unique, ie. has a unique user ID

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
	public RestaurantDB(String restaurantJSONfilename, String reviewsJSONfilename, String usersJSONfilename) {

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

				// add this Restaurant to the restaurantData
				restaurantData.add(restaurant);
				// System.out.println(restaurantData.size()); // for debugging

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

				// add this review to the reviewData
				reviewData.add(review);
				// System.out.println(reviewData.size()); // for debugging

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
				Double average_stars = (Double) jsonObject.get("average_stars");

				// create a new User with extracted details
				User user = new User(url, votes, review_count, type, user_id, name, average_stars);

				// add this User to the userData
				userData.add(user);
				// System.out.println(userData.size()); // for debugging

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get a list of all restaurants in the database.
	 * @return a new ArrayList containing all restaurants in the Database.
	 */
	public List<Restaurant> getRestaurantData() {
		return new ArrayList<Restaurant>(restaurantData);
	}

	/**
	 * Get a list of all reviews in the database.
	 * @return a new ArrayList containing all reviews in the Database.
	 */
	public List<Review> getReviewData() {
		return new ArrayList<Review>(reviewData);
	}

	/**
	 * Get a list of all users in the database.
	 * @return a new ArrayList containing all users in the Database.
	 */
	public List<User> getUserData() {
		return new ArrayList<User>(userData);
	}

	/**
	 * Return a set of restaurants satisfying a combination of restaurant names, 
     * neighborhood, categories, rating and price level. (Using && and || operators).
     * 
     * Acceptable queries have elements in the form (conjoined by && and || and () operators):
     * in("Some Area")
     * category("Some Category")
     * name("Some Name")
     * rating([1-5]..[1-5]) or rating([1-5])
     * price([1-5]..[1-5]) or price([1-5])
     * 
     * For example: in("Telegraph Ave") && (category("Chinese") || category("Italian")) && price(1..2)
     * returns a set of all Chinese or Italian restaurants in the Telegraph Avenue 
     * neighborhood that are in the price range [1,2].
     * 
     * @param queryString the query to try and satisfy
     * 
     * @return a set of restaurants satisfying a combination of restaurant names, 
     * neighborhood, categories, rating and price level. (Using && and || operators). 
	 */
	public Set<Restaurant> query(String queryString) {
		RestaurantFormula f = ParserOutputCreator.parse(queryString, restaurantData);
		return f.getRelevant();
	}

	/**
	 * Get a random review of a specified restaurant, in JSON format.
	 * 
	 * @param restaurantName the name of the restaurant for which to provide a random review
	 * @return a random review of the specified restaurant, in JSON format.
	 * If no restaurants with such a name are found, returns a suitable error String.
	 */
	public String randomReview(String restaurantName) {
		String nameForMatching = restaurantName.replaceAll("^\"|\"$", "");
		Set<String> matchingBusinessID = new HashSet<String>();
		Set<Review> matchingReviews = new HashSet<Review>();

		// save all business ID's of restaurants with matching names
		for (Restaurant restaurant : restaurantData) {
			if (restaurant.name.equals(nameForMatching))
				matchingBusinessID.add(restaurant.business_id);
		}

		// get all reviews for these business IDs
		for (Review review : reviewData) {
			for (String business_id : matchingBusinessID) {
				if (review.business_id.equals(business_id))
					matchingReviews.add(review);
			}
		}

		// get a random review by converting review set into list and generating
		// a random integer
		List<Review> matchingReviewsList = new ArrayList<Review>(matchingReviews);
		if (matchingReviewsList.size() != 0) {
			Random randomGenerator = new Random();
			int randomIndex = randomGenerator.nextInt(matchingReviewsList.size());

			return matchingReviewsList.get(randomIndex).toJSONString();
		} else {
			return "Error: no restaurants with such a name found!";
		}
	}

	/**
	 * Get the restaurant, in JSON format, with provided business ID.
	 * 
	 * @param businessID the business ID of the restaurant to get information on
	 * @return the restaurant corresponding to the provided business ID, in JSON format.
	 * If no restaurants with such a business ID are found, a suitable error String is returned.
	 */
	public String getRestaurant(String businessID) {
		String idForMatching = businessID.replaceAll("^\"|\"$", "");
		for (Restaurant restaurant : restaurantData) {
			if (restaurant.business_id.equals(idForMatching))
				return restaurant.toJSONString();
		}
		return "Error: no restaurant with such a business ID found!";
	}

	/**
	 * Add a Restaurant to the database.
	 * 
	 * @param JSONRestaurant the Restaurant to add to the database, in JSON format.  
	 * The added restaurant must be unique from other restaurants.
	 * @return a suitable message either confirming that the Restaurant was successfully added
	 * or a explaining an error that occurred.
	 */
	public String addRestaurant(String JSONRestaurant) {
		String JSONForMatching = JSONRestaurant.replaceAll("^\"|\"$", "");
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(JSONForMatching);
			JSONObject jsonObject = (JSONObject) obj;

			// retrieve a set of all keys in this JSON object
			Set<String> keys = jsonObject.keySet();

			// make sure that the JSON string is of the right length
			if (keys.size() > 17) {
				return "Error: invalid JSON format for this restaurant! (Extra JSON keys)";
			}

			// retrieve data from JSON and enter it into the
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

			// if any of these objects are null while parsing, then JSON for
			// restaurants was not in the right format
			if (open == null || url == null | longitude == null || neighborhoods == null || business_id == null
					|| name == null || categories == null || state == null || type == null || stars == null
					|| city == null || full_address == null || review_count == null || photo_url == null
					|| schools == null || latitude == null || price == null) {
				return "Error: invalid JSON format for this restaurant! (Missing a JSON key)";
			}

			// check if a same business ID already exists, or if a restaurant
			// with same name
			// at same longitude and latitude exists
			for (Restaurant restaurant : restaurantData) {
				if (restaurant.business_id.equals(business_id))
					return "Error: a restaurant with the same business ID already exists!";
				if (restaurant.latitude.equals(latitude) && restaurant.longitude.equals(longitude)
						&& restaurant.name.equals(name))
					return "Error: a restaurant with the same name at the same location already exists!";
			}

			// create a new Restaurant with extracted details
			Restaurant restaurant = new Restaurant(open, url, longitude, neighborhoods, business_id, name, categories,
					state, type, stars, city, full_address, review_count, photo_url, schools, latitude, price);

			// add this Restaurant to the restaurantData
			restaurantData.add(restaurant);

		} catch (ParseException e) {
			return "Error: invalid JSON format for this restaurant!";
		} catch (ClassCastException e) {
			return "Error: invalid JSON format for this restaurant!";
		}

		return "Restaurant successfully added to the database!";
	}

	/**
	 * Add a User to the database.
	 * 
	 * @param JSONUser the User to add to the database, in JSON format.  
	 * The added user must be unique from other users.
	 * @return a suitable message either confirming that the User was successfully added
	 * or a explaining an error that occurred.
	 */
	public String addUser(String JSONUser) {
		String JSONForMatching = JSONUser.replaceAll("^\"|\"$", "");
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(JSONForMatching);
			JSONObject jsonObject = (JSONObject) obj;

			// retrieve a set of all keys in this JSON object
			Set<String> keys = jsonObject.keySet();

			// make sure that the JSON string is of the right length
			if (keys.size() > 7) {
				return "Error: invalid JSON format for this user! (Extra JSON keys)";
			}

			// retrieve data from JSON and enter it into the
			// database by creating
			// a new user with extracted details
			String url = (String) jsonObject.get("url");
			Object votes = jsonObject.get("votes");
			Long review_count = (Long) jsonObject.get("review_count");
			String type = (String) jsonObject.get("type");
			String user_id = (String) jsonObject.get("user_id");
			String name = (String) jsonObject.get("name");
			Double average_stars = (Double) jsonObject.get("average_stars");

			// if any of these objects are null while parsing, then JSON for
			// users was not in the right format
			if (url == null || votes == null || review_count == null || type == null || user_id == null || name == null
					|| average_stars == null) {
				return "Error: invalid JSON format for this user! (Missing a JSON key)";
			}

			// check if a User with same ID already exists
			for (User user : userData) {
				if (user.user_id.equals(user_id))
					return "Error: A User with the same user ID already exists!";
			}

			// create a new User with extracted details
			User user = new User(url, votes, review_count, type, user_id, name, average_stars);

			// add this User to the userData
			userData.add(user);

		} catch (ParseException e) {
			return "Error: invalid JSON format for this user!";
		} catch (ClassCastException e) {
			return "Error: invalid JSON format for this user!";
		}

		return "User successfully added to the database!";
	}

	/**
	 * Add a Review to the database.
	 * 
	 * @param JSONReview the Review to add to the database, in JSON format.  
	 * The added review must be unique from other reviews.
	 * @return a suitable message either confirming that the Review was successfully added
	 * or a explaining an error that occurred.
	 */
	public String addReview(String JSONReview) {
		String JSONForMatching = JSONReview.replaceAll("^\"|\"$", "");
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(JSONForMatching);
			JSONObject jsonObject = (JSONObject) obj;

			// retrieve a set of all keys in this JSON object
			Set<String> keys = jsonObject.keySet();

			// make sure that the JSON string is of the right length
			if (keys.size() > 8) {
				return "Error: invalid JSON format for this review! (Extra JSON keys)";
			}

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

			// if any of these objects are null while parsing, then JSON for
			// users was not in the right format
			if (type == null || business_id == null || votes == null || review_id == null || text == null
					|| stars == null || user_id == null || date == null) {
				return "Error: invalid JSON format for this review! (Missing a JSON key)";
			}

			// check if a review with same ID already exists
			for (Review review : reviewData) {
				if (review.review_id.equals(review_id))
					return "Error: review with identical review ID already exists!";
			}

			// increment the review count of the reviewing user by one. If the
			// reviewing user
			// does not exist in the database, it is an error
			boolean flag = false;
			for (User user : userData) {
				if (user.user_id.equals(user_id)) {
					user.review_count++;
					flag = true;
				}
			}
			if (flag == false)
				return "Error: the user specified in this review does not exist!";

			// increment the review count of the reviewed restaurant by one. If the
			// reviewed restaurant
			// does not exist in the database, it is an error
			boolean flag2 = false;
			for (Restaurant restaurant : restaurantData) {
				if (restaurant.business_id.equals(business_id)) {
					restaurant.review_count++;
					flag2 = true;
				}
			}
			if (flag2 == false)
				return "Error: the restaurant specified in this review does not exist!";

			// create a new Review with extracted details
			Review review = new Review(type, business_id, votes, review_id, text, stars, user_id, date);

			// add this review to the reviewData
			reviewData.add(review);

		} catch (ParseException e) {
			return "Error: invalid JSON format for this review!";
		} catch (ClassCastException e) {
			return "Error: invalid JSON format for this review!";
		}

		return "Review successfully added to the database!";
	}
}
