import java.awt.Point;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame1 = new JFrame("Window 1");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(500, 500);

		// Generate random points
		Point[] points = new Point[20];
		for (int i = 0; i < 20; i++) {
			points[i] = new Point((int) (500 * Math.random()), (int) (500 * Math.random()));
		}
		Point[] pair1 = BruteForcePairs.BruteForce(points);

		Paint panel = new Paint(points, pair1);
		frame1.add(panel);

		frame1.setVisible(true);

		// sort points by x and y
		Point[] xSorted = DAC.quickSort(points, 0, points.length - 1, true);
		Point[] ySorted = DAC.quickSort(points, 0, points.length - 1, false);
		
		double closest = DAC.DACPair(xSorted,ySorted);
		
		double DACclosest = BruteForcePairs.distance(pair1[0], pair1[1]);
		
		System.out.println(closest);
		System.out.println(DACclosest);
		
		System.out.println("Equal results? "+(closest==DACclosest));

		
	}

}
