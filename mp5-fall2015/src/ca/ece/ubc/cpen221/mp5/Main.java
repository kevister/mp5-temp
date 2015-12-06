package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.statlearning.*;

public class Main{
	
    public static void main(String[] args) {
    	
    	RestaurantDB testDatabase = new RestaurantDB("data/restaurants.json", "data/reviews.json", "data/users.json");
    	List<Set<Restaurant>> testCluster = Algorithms.kMeansClustering(4, testDatabase);
    	
//    	System.out.println("\n\n");
//    	for (int i = 0; i < testCluster.size(); i++) {
//    		
//    		for (Restaurant r : testCluster.get(i)) {
//    			System.out.print(r.name);
//    		}
//    		
//    		System.out.println("");
//    		
//    	}
    	
    	String testString = Algorithms.convertClustersToJSON(testCluster);
    	
    	System.out.println(testString);
    	
    }
    
}