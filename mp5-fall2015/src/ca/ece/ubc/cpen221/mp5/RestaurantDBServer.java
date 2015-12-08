package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// This server instantiates a database and processes queries concurrently.
// It uses the port 4949.
// Thread safety arguments:
// Each client is handled by a thread, so all local variables in each client are confined (always on the stack).
// The shared database is only mutated by addUser, addReview, and addRestaurant methods, but we use
// message passing and each client has its own message passing buffer.  This means that messages
// never interleave between clients and deadlock isn't a possibility.
// Internal to each client thread, there are no race conditions.

public class RestaurantDBServer {

	public static final int RESTAURANT_PORT = 4949;

	private ServerSocket serverSocket;
	private RestaurantDB database;

	/**
	 * Construct a new RestaurantDBServer object.
	 * 
	 * @param port  the port this server will connect through.  Must be a valid port from 0 to 65535 inclusive
	 * @param restaurantFileName the file containing JSON encoded information about restaurants for the database
	 * @param reviewFileName the file containing JSON encoded information about reviews for the database
	 * @param userFileName the file containing JSON encoded information about users for the database
	 * @throws IOException if the main server socket is broken
	 */
	public RestaurantDBServer(int port, String restaurantFileName, String reviewFileName, String userFileName)
			throws IOException {
		serverSocket = new ServerSocket(port);
		database = new RestaurantDB(restaurantFileName, reviewFileName, userFileName);
	}

	/**
	 * Run the server, listening for connections and handling them.
	 * When a client connects, it is handled by a new thread.
	 * 
	 * @throws IOException
	 *             if the main server socket is broken
	 */
	public void serve() throws IOException {
		while (true) {
			// block until a client connects
			final Socket socket = serverSocket.accept();
			// create a new thread to handle that client
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							handle(socket);
						} finally {
							socket.close();
						}
					} catch (IOException ioe) {
						// this exception wouldn't terminate serve(),
						// since we're now on a different thread, but
						// we still need to handle it
						ioe.printStackTrace();
					}
				}
			});
			// start the thread
			handler.start();
		}
	}

	/**
	 * Handle one client connection. Returns when client disconnects.
	 * Possible requests that can be handled take the form:
	 * 
	 * randomReview("Restaurant Name"): To this request, the server responds by providing a random review (in JSON format)
	 * 	 for the restaurant that matches the provided name. If more than one restaurant matches the name then any restaurant that satisfies the match can be selected.
	 * getRestaurant("businessId"): To this request, the server responds with the restaurant details in JSON format for 
	 * 	 the restaurant that has the provided business identifier.
 	 * addRestaurant("Restaurant Details in JSON format"): The server adds a new restaurant to the database
     * addUser("User details in JSON format"): The server adds a new user to the database  
     * addReview("Review details in JSON format"): The server adds a new review to the database
     * 
     * The server also handles rich queries, ie a combination of restaurant names, 
     * neighborhood, categories, rating and price level. (Using && and || operators).
     * 
     * For example: in("Telegraph Ave") && (category("Chinese") || category("Italian")) && price(1..2)
     * returns a list of all Chinese or Italian restaurants in the Telegraph Avenue 
     * neighborhood that are in the price range [1,2].
     * 
     * For rich queries, the server accepts query elements of the form
     * in("Some Area")
     * category("Some Category")
     * name("Some Name")
     * rating([1-5]..[1-5]) or rating([1-5])
     * price([1-5]..[1-5]) or price([1-5])
	 * 
	 * @param socket
	 *            socket where client is connected
	 * @throws IOException
	 *             if connection encounters an error
	 */
	private void handle(Socket socket) throws IOException {
		System.err.println("client connected");

		// get the socket's input stream, and wrap converters around it
		// that convert it from a byte stream to a character stream,
		// and that buffer it so that we can read a line at a time
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// similarly, wrap character=>bytestream converter around the
		// socket output stream, and wrap a PrintWriter around that so
		// that we have more convenient ways to write Java primitive
		// types to it.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {
			// each request is a single line containing a query
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request: " + line);

				// see if line is a randomReview request
				if (line.substring(0, 12).equals("randomReview")) {
					String input = line.substring(13).replaceAll("\\(|\\)", "");
					String reply = database.randomReview(input);
					out.println(reply);
					System.err.println("reply: " + reply);
				} else {

					// see if line is a getRestaurant request
					if (line.substring(0, 13).equals("getRestaurant")) {
						String input = line.substring(14).replaceAll("\\(|\\)", "");
						String reply = database.getRestaurant(input);
						out.println(reply);
						System.err.println("reply: " + reply);
					} else {

						// see if line is an addRestaurant request
						if (line.substring(0, 13).equals("addRestaurant")) {
							String input = line.substring(14).replaceAll("\\(|\\)", "");
							String reply = database.addRestaurant(input);
							out.println(reply);
							System.err.println("reply: " + reply);
						} else {

							// see if line is a addReview request
							if (line.substring(0, 9).equals("addReview")) {
								String input = line.substring(10).replaceAll("\\(|\\)", "");
								String reply = database.addReview(input);
								out.println(reply);
								System.err.println("reply: " + reply);
							} else {

								// see if line is an addUser request
								if (line.substring(0, 7).equals("addUser")) {
									String input = line.substring(8).replaceAll("\\(|\\)", "");
									String reply = database.addUser(input);
									out.println(reply);
									System.err.println("reply: " + reply);
								} else {

									try {
										// otherwise the request is a rich query
										// compute list of restaurants and send
										// back
										// to client
										Set<Restaurant> answerSet = database.query(line);

										List<Restaurant> replyList = new ArrayList<Restaurant>(answerSet);
										String reply = replyList.get(0).toJSONString();
										for (int index = 1; index < replyList.size(); index++) {
											reply = reply + replyList.get(index).toJSONString();
										}

										out.println(reply);
										System.err.println("reply: " + reply);

									//catch the runtime exception produced by a misformatted query
									// so that the server will continue to function if a misformatted query is received
									} catch (RuntimeException e) {
										out.println("Invalid query!");
										System.err.println("Invalid query!");
									}
								}
							}
						}
					}
				}
			}
		} finally {
			out.close();
			in.close();
		}
	}
	/**
	 * Start a RestaurantDBServer running on ports specified by the command line arguments.
	 */
	public static void main(String[] args) {
		try {
			RestaurantDBServer server = new RestaurantDBServer(Integer.parseInt(args[0]), 
					args[1], args[2], args[3]);
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


