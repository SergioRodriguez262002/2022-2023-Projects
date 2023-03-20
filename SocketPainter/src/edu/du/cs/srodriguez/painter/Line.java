package edu.du.cs.srodriguez.painter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Line extends PaintingPrimitive  {

	Point a, b;

	public Line(Color color, Point a, Point b) {
		super(color);
		this.a = a;
		this.b = b;
	}

	@Override
	protected void drawGeometry(Graphics g) {
		g.drawLine(a.x, a.y, b.x, b.y);
	}

}
