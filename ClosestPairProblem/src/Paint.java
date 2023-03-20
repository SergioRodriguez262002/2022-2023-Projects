import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Paint extends JPanel {

	Point[] points;
	Point[] pair;
	public Paint(Point[] points, Point[] pair) {
		this.points = points;
		this.pair = pair;
	}

	public void paintComponent(Graphics g) {
		for (Point p : points) {
			g.drawOval(p.x, p.y, 5, 5);
		}
		g.drawLine(pair[0].x, pair[0].y, pair[1].x, pair[1].y);
		g.setColor(Color.red);
		for(Point p: pair) {
			g.drawOval(p.x, p.y, 5, 5);
		}
	}

}
