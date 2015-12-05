package ca.ece.ubc.cpen221.mp5.statlearning;

public class Point{
	
	public double longitude;
	public double latitude;
	
	Point(double longitude, double latitude){
		
		this.longitude = longitude;
		this.latitude = latitude;
		
	}
	
	public String toString() {
		return String.valueOf(this.latitude) + " " + String.valueOf(this.longitude);
	}
	
}