import java.awt.Point;

public class BruteForcePairs {
	public BruteForcePairs() {}
	
	public static Point[] BruteForce(Point[] points) {
		Point[] closest = new Point[2];
		double smallest = 500;
		double test;
		for(Point a: points) {
			for(Point b: points) {
				test = distance(a,b);
				if(test < smallest && a != b) {
					smallest = test;
					closest[0] = new Point(a);
					closest[1] = new Point(b);
				}
			}
		}
		return closest;
	}
	
	static public double distance(Point a, Point b) {
		return Math.sqrt(Math.pow(b.x-a.x, 2)+Math.pow(b.y - a.y,2));
	}

}
