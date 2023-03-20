import java.awt.Point;
import java.util.ArrayList;

public class quickhull {

	public quickhull() {
	}

	public ArrayList<Point> quickhullstart(ArrayList<Point> S, ArrayList<Point> farthest) {

		ArrayList<Point> result = new ArrayList<Point>();
		Point a = S.get(0);
		Point b = S.get(0);

		for (int i = 0; i < S.size(); i++) {
			if (S.get(i).x > a.x) {
				a = S.get(i);
			}
			if (S.get(i).x < b.x) {
				b = S.get(i);
			}
		}

		result.add(new Point(a));
		result.add(new Point(b));
		farthest.add(a);
		farthest.add(b);
		S.remove(a);
		S.remove(b);

		ArrayList<Point> upper = new ArrayList<Point>();
		ArrayList<Point> lower = new ArrayList<Point>();

		for (Point p : S) {
			if (crossProduct(a, b, p) > 0) {
				upper.add(p);
			}
			if (crossProduct(b, a, p) > 0) {
				lower.add(p);
			}
		}
		qh(upper, a, b, result);
		qh(lower, b, a, result);

		return result;

	}

	public void qh(ArrayList<Point> S, Point a, Point b, ArrayList<Point> result) {
		int insert = result.indexOf(b);
		if (S.size() == 0) {
			return;
		}
		if (S.size() == 1) {
			Point p = S.get(0);
			S.remove(p);
			result.add(insert, p);
			return;
		}

		int max = 0;
		Point c = S.get(0);
		int farthest = 0;
		int count = 0;
		int dist;
		for (Point p : S) {
			dist = ValueBasedOnLineDistance(a, b, p);
			if (dist > max) {
				max = dist;
				c = new Point(p);
				farthest = count;
			}
			count += 1;
		}

		result.add(c);
		S.remove(farthest);

		ArrayList<Point> left = new ArrayList<Point>();
		ArrayList<Point> right = new ArrayList<Point>();

		for (Point p : S) {
			if (crossProduct(a, c, p) > 0) {
				left.add(p);
			}
			if (crossProduct(c, b, p) > 0) {
				right.add(p);
			}
		}

		qh(left, a, c, result);
		qh(right, c, b, result);
	}

	static public int crossProduct(Point a, Point b, Point p) {
		int Vabx = b.x - a.x;
		int Vaby = b.y - a.y;
		int Vbix = p.x - b.x;
		int Vbiy = p.y - b.y;
		int crossProd = (Vabx * Vbiy) - (Vaby * Vbix);
		return crossProd;
	}

	static public int ValueBasedOnLineDistance(Point a, Point b, Point p) {
		int v1x = b.x - a.x;
		int v1y = b.y - a.y;
		int v2x = p.x - a.x;
		int v2y = p.y - a.y;
		return Math.abs((v1x * v2y) - (v1y * v2x));
	}

}
