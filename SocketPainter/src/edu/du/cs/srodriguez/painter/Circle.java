package edu.du.cs.srodriguez.painter;
import java.awt.*;

public class Circle extends PaintingPrimitive  {

	Point radiusPoint;
	Point center;

	public Circle(Color color, Point center, Point radiusPoint) {
		super(color);
		this.center = center;
		this.radiusPoint = radiusPoint;
	}

	public void drawGeometry(Graphics g) {
		int radius = (int) Math.abs(center.distance(radiusPoint));
		g.drawOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
	}

}
