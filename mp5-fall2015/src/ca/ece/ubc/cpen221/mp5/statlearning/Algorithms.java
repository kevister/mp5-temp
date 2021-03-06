package ca.ece.ubc.cpen221.mp5.statlearning;
import ca.ece.ubc.cpen221.mp5.statlearning.Point;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
			
		}
		
		Map <Point, Set<Restaurant>> centroids = new HashMap<Point, Set<Restaurant>>();
		
		for (int i = 0; i < k; ++i) {
			
			Random r = new Random();
			double longitude = LONG_MIN + (LONG_MAX - LONG_MIN) * r.nextDouble();
			double latitude = LAT_MIN + (LAT_MAX - LAT_MIN) * r.nextDouble();;
			
			centroids.put(new Point(longitude, latitude), new HashSet<Restaurant>());
			
		}

		Map<Point, Set<Restaurant>> temp_centroids = new HashMap<Point, Set<Restaurant>>();
		Map<Point, Set<Restaurant>> newCentroids = null;
		
		do {
			
			if (newCentroids != null)
				centroids = newCentroids;
						
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
			
			for(Point p : centroids.keySet()) {
				System.out.println(p.toString() + ": ");
				for(Restaurant s : centroids.get(p)) {
					System.out.print(s.name + ", ");
				}
				System.out.println("");
			}
			System.out.println("\n\n");
			
			newCentroids = new HashMap<Point, Set<Restaurant>>();
			
			for (Point p : centroids.keySet()) {
				
				Point newPoint = null;
				
				if (centroids.get(p).size() == 0)
					newPoint = p;
				else {
					double long_ave = 0;
					double lat_ave = 0;
				
					for (Restaurant r : centroids.get(p)) {
					
						long_ave += r.longitude;
						lat_ave += r.latitude;
					
					}
								
					long_ave = long_ave / centroids.get(p).size();
					lat_ave = lat_ave / centroids.get(p).size();
				
					newPoint = new Point(long_ave, lat_ave);
				}
								
				newCentroids.put(newPoint, new HashSet<Restaurant>());
				
			}
						
		} while(!equalMap(newCentroids, temp_centroids));
		
		List<Set<Restaurant>> result = new ArrayList<Set<Restaurant>>();
		
		for (Point p : centroids.keySet()){
			
			Set<Restaurant> s = centroids.get(p);
			result.add(s);
			
		}
		
		return result;
		
	}
	
	public static boolean equalMap(Map<Point, Set<Restaurant>> m1, Map<Point, Set<Restaurant>> m2) {
		
    	for (Point p : m1.keySet()) {
			
    		Point temp = null;
    		
			if (m1.size() != m2.size())
				return false;
			
			for (Point p1 : m2.keySet()) {
				if(p.equals(p1))
					temp = p1;
			}
			
			if(temp == null)
				return false;
			
		}
		
		return true;
    }

	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		// TODO: Implement this method
		
		int count = 1;
		String result = "[";

		for (Set<Restaurant> s : clusters) {

			for (Restaurant r : s) {
				
				Map jo =new LinkedHashMap();
			
				jo.put("x", r.latitude);
				jo.put("y", r.longitude);
				jo.put("name", r.name);
				jo.put("cluster", count);
				jo.put("weight", 4.0);
				String jsonText = JSONValue.toJSONString(jo);
				
				result = result.concat(jsonText + ", ");
				
			}
			
			count++;
		}
		
		result = result.substring(0, result.length() - 2);
		result = result.concat("]");
		
		return result;

	}

	public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
		// TODO: Implement this method
		
		Map<Double, Long> featureOutputs = new HashMap<Double, Long>();
		double x_mean = 0.0;		//feature function output; x-axis
		double y_mean = 0.0;		//ratings; y-axis
		double Sxx = 0.0;
		double Syy = 0.0;
		double Sxy = 0.0;
		double b = 0.0;
		double a = 0.0;
		double R_sqr = 0.0;
		
		for (Review rev : db.getReviewData()) {
			
			if (rev.user_id == u.user_id) {
				
				for (Restaurant res : db.getRestaurantData()) {
					if (rev.business_id == res.business_id) {
						featureOutputs.put(featureFunction.f(res, db), rev.stars);
						break;
					}
				}
				
			}
				
		}
		
		for (Double d : featureOutputs.keySet())
			x_mean += d;
		x_mean /= featureOutputs.keySet().size();
		for (Double d : featureOutputs.keySet())
			Sxx += Math.pow(d - x_mean, 2);
		
		for (Double d : featureOutputs.keySet())
			y_mean += featureOutputs.get(d);
		y_mean /= featureOutputs.keySet().size();
		for (Double d : featureOutputs.keySet())
			Syy += Math.pow(featureOutputs.get(d) - y_mean, 2);
		
		for (Double d : featureOutputs.keySet()) {
			Sxy += (d - x_mean) * (featureOutputs.get(d) - y_mean);
		}
		
		b = Sxy / Sxx;
		a = y_mean - b * x_mean;
		R_sqr = Math.pow(Sxy, 2) / (Sxx * Syy);
		
		return new RegressionFunction(b, a, R_sqr);
	}

	public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
		
		double R_max = 0.0;
		RegressionFunction r = new RegressionFunction();
		
		for (MP5Function m : featureFunctionList) {
			
			RegressionFunction r_temp = r;
			r = (RegressionFunction) m;
			
			if (r.R_sqr > R_max)
				r = (RegressionFunction) m;
			else
				r = r_temp;
			
		}
		
		return r;
		
	}
}