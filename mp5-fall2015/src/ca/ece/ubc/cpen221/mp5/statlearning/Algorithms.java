package ca.ece.ubc.cpen221.mp5.statlearning;
import ca.ece.ubc.cpen221.mp5.statlearning.Point;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.*;
import org.json.simple.JSONObject;

import ca.ece.ubc.cpen221.mp5.*;

public class Algorithms {

	/**
	 * Use k-means clustering to compute k clusters for the restaurants in the
	 * database.
	 * 
	 * @param db
	 * @return
	 */
	
	private static double LONG_MAX = -(Double.MAX_VALUE);
	private static double LONG_MIN = Double.MAX_VALUE;
	private static double LAT_MAX = Double.MIN_VALUE;
	private static double LAT_MIN = Double.MAX_VALUE;
	
	public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db) {
		// TODO: Implement this method
		
		for (Restaurant r : db.getRestaurantData()) {
						
			if (r.longitude >= LONG_MAX) 
				LONG_MAX = r.longitude;
			else if (r.longitude <= LONG_MIN)
				LONG_MIN = r.longitude;
			
			if(r.latitude >= LAT_MAX)
				LAT_MAX = r.latitude;
			else if(r.latitude <= LAT_MIN)
				LAT_MIN = r.latitude;
			
//			System.out.println(LONG_MAX + " " + LONG_MIN + " " + LAT_MAX + " " + LAT_MIN);
		}
		
		Map <Point, Set<Restaurant>> centroids = new HashMap<Point, Set<Restaurant>>();
		
		for (int i = 0; i < k; ++i) {
			
			Random r = new Random();
			double longitude = LONG_MIN + (LONG_MAX - LONG_MIN) * r.nextDouble();
			double latitude = LAT_MIN + (LAT_MAX - LAT_MIN) * r.nextDouble();;
			
			centroids.put(new Point(longitude, latitude), new HashSet<Restaurant>());
			
		}
		
//		for(Point p : centroids.keySet()) {
//			System.out.println(p.toString());
//		}
//		System.out.println(" ");
		
		Map<Point, Set<Restaurant>> temp_centroids = new HashMap<Point, Set<Restaurant>>();
		
		do {
						
			for (Restaurant r : db.getRestaurantData()) {
				
				double dist = Double.MAX_VALUE;
				Point temp = null;
				
				for(Point p : centroids.keySet()) {
					
					double long_temp = Math.abs(r.longitude - p.longitude);
					double lat_temp = Math.abs(r.latitude - p.latitude);
					double dist_temp = Math.sqrt(Math.pow(long_temp, 2) + Math.pow(lat_temp, 2));
					
					if ( dist_temp < dist) {
						dist = dist_temp;
						temp = p;
					}
					
				}
				
				centroids.get(temp).add(r);
				
			}
			
			temp_centroids = centroids;
			
//			for(Point p : centroids.keySet()) {
//				System.out.println(p.toString() + ": ");
//				for(Restaurant s : centroids.get(p)) {
//					System.out.print(s.name + ", ");
//				}
//			}
			
			Map<Point, Set<Restaurant>> newCentroids = new HashMap<Point, Set<Restaurant>>();
			
			for (Point p : centroids.keySet()) {
				
				double long_ave = 0;
				double lat_ave = 0;
				
				for (Restaurant r : centroids.get(p)) {
					
					long_ave += r.longitude;
					lat_ave += r.latitude;
					
				}
				
//				System.out.println(long_ave + " " + lat_ave + " " + centroids.get(p).size());
				
				long_ave = long_ave / centroids.get(p).size();
				lat_ave = lat_ave / centroids.get(p).size();
				
				Point newPoint = new Point(long_ave, lat_ave);
				
				System.out.println(long_ave + " " + lat_ave);
				
				newCentroids.put(newPoint, new HashSet<Restaurant>());
//				for(Restaurant r : centroids.get(p)) 
//					newCentroids.get(newPoint).add(r);
				
			}
			
			centroids = newCentroids;
			
			System.out.println("" + "\n");
			
		} while(!centroids.equals(temp_centroids));
		
		List<Set<Restaurant>> result = new ArrayList<Set<Restaurant>>();
		
		for (Point p : centroids.keySet()){
			
			result.add(centroids.get(p));
			
		}
		
		return result;
		
	}

	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		// TODO: Implement this method
		
		int count = 1;
		String result = "[";
		
		for (Set<Restaurant> s : clusters) {
			
			for (Restaurant r : s) {
				
				JSONObject jo = new JSONObject();
			
				jo.put("x", r.latitude);
				jo.put("y", r.latitude);
				jo.put("name", r.name);
				jo.put("cluster", count);
				jo.put("weight", 4);
				
				result.concat(jo.toJSONString());
			}
			
			count++;
		}
		
		result.concat("]");
		
		return result;
	}

	public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
		// TODO: Implement this method
		return null;
	}
}