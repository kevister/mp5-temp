package ca.ece.ubc.cpen221.mp5.statlearning;

public class Point{
	
	public double longitude;
	public double latitude;
	
	public Point(double longitude, double latitude){
		
		this.longitude = longitude;
		this.latitude = latitude;
		
	}
	
	public String toString() {
		return String.valueOf(this.longitude) + " " + String.valueOf(this.latitude);
	}
	
	public boolean equals(Point p) {
		if (!(this.longitude == p.longitude))
			return false;
		else if (!(this.latitude == p.latitude))
			return false;
		
		return true;
	}
	
}