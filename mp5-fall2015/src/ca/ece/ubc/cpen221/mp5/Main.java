package ca.ece.ubc.cpen221.mp5;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.statlearning.*;

public class Main{
	
    public static void main(String[] args) {
    	
    	RestaurantDB testDatabase = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");
    	
    	List<Set<Restaurant>> testCluster = Algorithms.kMeansClustering(9, testDatabase);
    	
    	String testString = Algorithms.convertClustersToJSON(testCluster);
    	System.out.println(testString);
    	
    	try {
			FileWriter fw = new FileWriter("visualize/voronoi.json");
			fw.write(testString);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
}