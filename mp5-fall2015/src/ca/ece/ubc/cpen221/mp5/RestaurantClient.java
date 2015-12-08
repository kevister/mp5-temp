package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class RestaurantClient {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	// Rep invariant: socket, in, out != null

	/**
	 * Make a RestaurantClient and connect it to a server running on hostname at
	 * the specified port.
	 * 
	 * @throws IOException
	 *             if can't connect
	 */
	public RestaurantClient(String hostname, int port) throws IOException {
		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	/**
	 * Send a request to the server. Requires this is "open".
	 * 
	 * @param query
	 *            to find some query: either
	 *            a rich query,
	 *            randomReview("Restaurant Name")
	 *            getRestaurant("Business ID")
	 *            addRestaurant("Restaurant details in JSON format")
	 *            addUser("User details in JSON format")
	 *            addReview("Review details in JSON format")
	 *            
	 *            
	 * @throws IOException
	 *             if network or server failure
	 */
	public void sendRequest(String query) throws IOException {
		out.print(query + "\n");
		out.flush(); // important! make sure x actually gets sent
	}

	/**
	 * Get a reply from the next request that was submitted. Requires this is
	 * "open".
	 * 
	 * @return the reply to the requested query
	 * @throws IOException
	 *             if network or server failure
	 */
	public String getReply() throws IOException {
		String reply = in.readLine();
		try {
			return reply;
		} catch (NumberFormatException nfe) {
			throw new IOException("misformatted reply: " + reply);
		}
	}

	/**
	 * Closes the client's connection to the server. This client is now
	 * "closed". Requires this is "open".
	 * 
	 * @throws IOException
	 *             if close fails
	 */
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	/**
	 * Start some RestaurantClients running on the default port.
	 * This is a client class which tests the functionality of the server, including whether
	 * or not it can handle multiple clients at once.  (PS. It can! YAY!)
	 */
	public static void main(String[] args) {
		try {
			RestaurantClient client = new RestaurantClient("localhost", RestaurantDBServer.RESTAURANT_PORT);
			RestaurantClient client2 = new RestaurantClient("localhost", RestaurantDBServer.RESTAURANT_PORT);
			RestaurantClient client3 = new RestaurantClient("localhost", RestaurantDBServer.RESTAURANT_PORT);

			// send some requests
			
			// test some VERY rich queries
			client.sendRequest(
					"(in(\"UC Campus Area\") && category(\"Italian\")) || price(3) || (rating(4..5) && in(\"Telegraph Ave\"))");
			System.out.println("request sent");
			System.out.println("reply: " + client.getReply());

			//test the last query elements of rich queries
			client2.sendRequest("name(\"Bongo Burger\")");
			System.out.println("request sent");
			System.out.println("reply: " + client2.getReply());
			
			//test a rich query syntax error, should not stop server
			client3.sendRequest("in(\"UC Campus Area\") & category(\"Italian\")");
			System.out.println("request sent");
			System.out.println("reply: " + client3.getReply());
					
			//all rich queries tested		

		
			//send some requests from multiple clients, also test the 5 additional queries
			//random review valid
			client2.sendRequest("randomReview(\"Cafe 3\")");
			System.out.println("request sent");
			System.out.println("reply: " + client2.getReply());
			
			//random review invalid
			client.sendRequest("randomReview(\"This Restaurant Name shouldn't exist!!!\")");
			System.out.println("request sent");
			System.out.println("reply: " + client.getReply());

			//get restaurant invalid
			client3.sendRequest("getRestaurant(\"soidfj234randombusinessIDoijdfsodf\")");
			System.out.println("request sent");
			System.out.println("reply: " + client3.getReply());
			
			//add a new restaurant and then try and get it
			//add restaurant valid
			client.sendRequest(
					"addRestaurant(\"{\"open\": true, \"url\": \"http://www.yelp.com/biz/sweetheart-cafe-berkeley\", \"longitude\": -122.2579199, \"neighborhoods\": [\"Telegraph Ave\", \"UC Campus Area\"], \"business_id\": \"12345\", \"name\": \"Nuts\", \"categories\": [\"Food\", \"Burgers\", \"Coffee & Tea\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.5, \"city\": \"Berkeley\", \"full_address\": \"2523 Durant AveTelegraph AveBerkeley, CA 94704\", \"review_count\": 338, \"photo_url\": \"http://s3-media4.ak.yelpcdn.com/bphoto/kUeeBL48D6GuH1e8NyBzaw/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8681116, \"price\": 1}\"");
			System.out.println("request sent");
			System.out.println("reply: " + client.getReply());
			
			//get restaurant valid
			client2.sendRequest("getRestaurant(\"12345\")");
			System.out.println("request sent");
			System.out.println("reply: " + client2.getReply());
			
			//add restaurant invalid JSON
			client.sendRequest(
					"addRestaurant(\"{\"open\": true, \"url: \"http://www.yelp.com/biz/sweetheart-cafe-berkeley\", \"longitude\": -122.2579199, \"neighborhoods\": [\"Telegraph Ave\", \"UC Campus Area\"], \"business_id\": \"12345\", \"name\": \"Nuts\", \"categories\": [\"Food\", \"Burgers\", \"Coffee & Tea\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.5, \"city\": \"Berkeley\", \"full_address\": \"2523 Durant AveTelegraph AveBerkeley, CA 94704\", \"review_count\": 338, \"photo_url\": \"http://s3-media4.ak.yelpcdn.com/bphoto/kUeeBL48D6GuH1e8NyBzaw/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8681116, \"price\": 1}\"");
			System.out.println("request sent");
			System.out.println("reply: " + client.getReply());
			
			//add restaurant already exists
			client.sendRequest(
					"addRestaurant(\"{\"open\": true, \"url\": \"http://www.yelp.com/biz/sweetheart-cafe-berkeley\", \"longitude\": -122.2579199, \"neighborhoods\": [\"Telegraph Ave\", \"UC Campus Area\"], \"business_id\": \"12345\", \"name\": \"Nuts\", \"categories\": [\"Food\", \"Burgers\", \"Coffee & Tea\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.5, \"city\": \"Berkeley\", \"full_address\": \"2523 Durant AveTelegraph AveBerkeley, CA 94704\", \"review_count\": 338, \"photo_url\": \"http://s3-media4.ak.yelpcdn.com/bphoto/kUeeBL48D6GuH1e8NyBzaw/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8681116, \"price\": 1}\"");
			System.out.println("request sent");
			System.out.println("reply: " + client.getReply());

			//addReview already exists (invalid)
			client3.sendRequest(
					"addReview(\"{\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vi9VSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"7k3tfGPXlbsbJuu3FGpArg\", \"text\": \"fast, fun, and has a nice vibe with outdoor seating and pool tables and such. thin crust ftw. great for a pre-concert meal because it's in walking distance to the Greek! :)\", \"stars\": 3, \"user_id\": \"ErJcnrBcG9-HXiB_gTp_zA\", \"date\": \"2011-09-26\"}\")");
			System.out.println("request sent");
			System.out.println("reply: " + client3.getReply());
			
			//addReview invalid JSON (invalid)
			client2.sendRequest(
					"addReview(\"{\"type\": \"review, \"business_id\": \"1CBs84C-a-cuA3vi9VSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"7k3tfGPXlbsbJuu3FGpArg\", \"text\": \"fast, fun, and has a nice vibe with outdoor seating and pool tables and such. thin crust ftw. great for a pre-concert meal because it's in walking distance to the Greek! :)\", \"stars\": 3, \"user_id\": \"ErJcnrBcG9-HXiB_gTp_zA\", \"date\": \"2011-09-26\"}\")");
			System.out.println("request sent");
			System.out.println("reply: " + client2.getReply());

			//addReview valid
			client3.sendRequest(
					"addReview(\"{\"type\": \"review\", \"business_id\": \"XzWUVPs5VcCTEVtWYZVYRg\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"7k3tfGessfbJuu3FGpArg\", \"text\": \"CPEN 221 is alot of work......\", \"stars\": 5, \"user_id\": \"ErJcnrBcG9-HXiB_gTp_zA\", \"date\": \"2011-09-26\"}\")");
			System.out.println("request sent");
			System.out.println("reply: " + client3.getReply());

			//addUser already exists
			client.sendRequest("addUser(\"{\"url\": \"http://www.yelp.com/user_details?userid=LJDblZ1ewTG3FfRbF1lmmQ\", \"votes\": {\"funny\": 35, \"useful\": 65, \"cool\": 32}, \"review_count\": 85, \"type\": \"user\", \"user_id\": \"LJDblZ1ewTG3FfRbF1lmmQ\", \"name\": \"Zohreh A.\", \"average_stars\": 4.12941176470588}\")");
			System.out.println("request sent");
			System.out.println("reply: " + client.getReply());
			
			//addUser invalid JSON
			client2.sendRequest("addUser(\"{\"url: \"http://www.yelp.com/user_details?userid=LJDblZ1ewTG3FfRbF1lmmQ\", \"votes\": {\"funny\": 35, \"useful\": 65, \"cool\": 32}, \"review_count\": 85, \"type\": \"user\", \"user_id\": \"LJDblZ1ewTG3FfRbF1lmmQ\", \"name\": \"Zohreh A.\", \"average_stars\": 4.12941176470588}\")");
			System.out.println("request sent");
			System.out.println("reply: " + client2.getReply());
			
			//addUser valid
			client.sendRequest("addUser(\"{\"url\": \"http://www.yelp.com/user_details?userid=LJDblZ1ewTG3FfRbF1lmmQ\", \"votes\": {\"funny\": 35, \"useful\": 65, \"cool\": 32}, \"review_count\": 85, \"type\": \"user\", \"user_id\": \"LJDblZ1ewdisfjeujemmQ\", \"name\": \"CPEN221 LOVER FOREVER\", \"average_stars\": 4.12941176470588}\")");
			System.out.println("request sent");
			System.out.println("reply: " + client.getReply());
			
			client.close();
			client2.close();
			client3.close();
		} catch (

		IOException ioe)

		{
			ioe.printStackTrace();
		}
	}

}
