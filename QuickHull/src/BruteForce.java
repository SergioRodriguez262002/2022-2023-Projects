import java.awt.Point;
import java.util.ArrayList;

public class BruteForce {
	public BruteForce() {}
	public boolean LeftTurn(Point a, Point b, Point i) {
		int Vabx = b.x - a.x;
		int Vaby = b.y - a.y;
		int Vbix = i.x - b.x;
		int Vbiy = i.y - b.y;
		int crossProd = (Vabx * Vbiy) - (Vaby * Vbix);
		return crossProd > 0;
	}

	public ArrayList<Point> BruteForce(ArrayList<Point> points) {
		// convex hull
		ArrayList<Point> CH = new ArrayList<Point>();

		for (int i = 0; i < points.size(); i++) {
			for (int j = 0; j < points.size(); j++) {
				if (j != i) {
					int LeftTurnCount = 0;
					for (int k = 0; k < points.size(); k++) {
						if (k != i && k != j) {
							if (LeftTurn(points.get(i), points.get(j), points.get(k))) {
								LeftTurnCount += 1;
							}
						}
					}
					if (LeftTurnCount == 0 || LeftTurnCount == points.size() - 2) {
						CH.add(points.get(i));
						CH.add(points.get(j));
					}
				}
			}
		}
		return CH;
	}

}
