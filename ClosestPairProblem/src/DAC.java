import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

public class DAC {
	public DAC() {
	}

	public static double DACPair(Point[] xSorted, Point[] ySorted) {

		if (xSorted.length == 0) {
			return Double.POSITIVE_INFINITY;
		}

		if (xSorted.length <= 3) {

			return BruteForce(xSorted);
		}

		// split x in half
		int midindex = xSorted.length / 2;
		int midline = xSorted[midindex - 1].x;

		ArrayList<Point> leftx = new ArrayList<Point>();
		ArrayList<Point> rightx = new ArrayList<Point>();
		ArrayList<Point> lefty = new ArrayList<Point>();
		ArrayList<Point> righty = new ArrayList<Point>();

		for (int i = 0; i < xSorted.length; i++) {
			if (i < midindex) {
				leftx.add(xSorted[i]);
			} else {
				lefty.add(xSorted[i]);
			}
		}

		for (int i = 0; i < ySorted.length; i++) {
			if (ySorted[i].x <= midline) {

				lefty.add(ySorted[i]);
			} else {

				righty.add(ySorted[i]);
			}
		}

		Point[] left_x = leftx.toArray(new Point[leftx.size()]);
		Point[] right_x = rightx.toArray(new Point[rightx.size()]);
		Point[] left_y = lefty.toArray(new Point[lefty.size()]);
		Point[] right_y = righty.toArray(new Point[righty.size()]);

		double leftDistance = DACPair(left_x, left_y);
		double rightDistance = DACPair(right_x, right_y);

		double closest = Math.min(leftDistance, rightDistance);

		// get the strip
		// Point[] strip = new Point[xSorted.length]; // there shouldn't be more than 6
		// points within the strip
		ArrayList<Point> strip = new ArrayList<Point>();
		for (Point p : ySorted) {
			if (Math.abs(p.x - midline) < closest) {
				strip.add(p);
			}
		}

		int testCount = 0;
		for (int i = 0; i < strip.size(); i++) {
			int j = i + 1;
			while (j < strip.size() && (strip.get(j).y - strip.get(i).y) < closest) {
				testCount++;

				double distance = distance(strip.get(j), strip.get(i));
				if (distance < closest) {
					closest = distance;
				}
				j++;
			}
		}

		return closest;

	}

	public static int partition(Point[] points, int low, int high, boolean x) {
		// int pivot = points[high].x;
		int pivot = (x) ? points[high].x : points[high].y;
		int smallest = low - 1;
		int testxy;
		for (int i = low; i < high; i++) {
			testxy = (x) ? points[i].x : points[i].y;
			if (testxy < pivot) {
				smallest++;
				swap(points, smallest, i);
			}
		}
		swap(points, smallest + 1, high);

		return smallest + 1;
	}

	public static Point[] quickSort(Point[] points, int low, int high, boolean x) {
		if (low < high) {
			int partition = partition(points, low, high, x);
			quickSort(points, low, partition - 1, x);
			quickSort(points, partition + 1, high, x);
		}

		// create a deep copy of array
		Point[] end = new Point[points.length];
		for (int i = 0; i < end.length; i++) {
			end[i] = new Point(points[i]);
		}
		return end;
	}

	public static void swap(Point[] arr, int a, int b) {
		Point temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public static double BruteForce(Point[] points) {
		double smallest = distance(points[0], points[1]);
		double test;
		for (Point a : points) {
			for (Point b : points) {
				test = distance(a, b);
				if (test < smallest && a != b) {
					smallest = test;

				}
			}
		}
		return smallest;
	}

	static public double distance(Point a, Point b) {
		return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
	}

}
