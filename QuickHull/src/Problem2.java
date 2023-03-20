import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Problem2 {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Main window");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.white);

		JFrame frame2 = new JFrame("Main window");
		frame2.setSize(500, 500);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setBackground(Color.white);

		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < 50; i++) {
			Point point = new Point((int) (Math.random() * 400), (int) (Math.random() * 400));
			points.add(point);
		}

		quickhull q = new quickhull();
		BruteForce b = new BruteForce();
		ArrayList<Point> farthest = new ArrayList<Point>();
		ArrayList<Point> CH1 = b.BruteForce(points);
		ArrayList<Point> CH2 = q.quickhullstart(points, farthest);

		Paint panel1 = new Paint(points, CH1);
		Paint panel2 = new Paint(points, CH2, farthest);

		frame.add(panel1);
		frame2.add(panel2);
		frame.setVisible(true);
		frame2.setVisible(true);

	}

	

}
