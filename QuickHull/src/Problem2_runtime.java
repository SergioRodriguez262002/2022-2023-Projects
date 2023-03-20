import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Problem2_runtime {

	public static void main(String[] args) {
		
		quickhull q = new quickhull();
		BruteForce b = new BruteForce();
		ArrayList<Point> farthest = new ArrayList<Point>();
		
		int numTrials = 10;
		int numTotal = 1000;
		long start = 1, stop = 1;
		System.out.println("N Time Brute force");
		for(int i = 0; i < numTotal; i+=10) {
			start = System.currentTimeMillis();
			for(int j = 0; j < numTrials; j++) {
				b.BruteForce(RandPoints(i));
			}
			stop = System.currentTimeMillis();
			System.out.println(i+" "+((stop-start)/(double)numTrials));
		}
		
		
		System.out.println("N Time Quick hull");
		numTrials = 1000;
		for(int i = 10; i < numTotal; i+=10) {
			start = System.currentTimeMillis();
			for(int j = 0; j < numTrials; j++) {
				q.quickhullstart(RandPoints(i), farthest);
			}
			stop = System.currentTimeMillis();
			System.out.println(i+" "+((stop-start)/(double)numTrials));
		}
	}
	
	
	static public ArrayList<Point> RandPoints(int n){
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < n; i++) {
			Point point = new Point((int) (Math.random() * 400), (int) (Math.random() * 400));
			points.add(point);
		}
		return points;
	}

}
