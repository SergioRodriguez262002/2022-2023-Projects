import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Paint extends JPanel{
	
	ArrayList<Point> point;
	ArrayList<Point> line;
	ArrayList<Point> farthest;
	public Paint(ArrayList<Point> point, ArrayList<Point> line, ArrayList<Point> farthest) {
		this.point = point;
		this.line = line;
		this.farthest = farthest;
		System.out.println("Line size "+ line.size());
	}
	
	public Paint(ArrayList<Point> point, ArrayList<Point> line) {
		this.point = point;
		this.line = line;
	}
	
	public void paintComponent(Graphics g) {
		
		for(Point p: point) {
			g.drawOval(p.x, p.y, 5, 5);
		}
		g.setColor(Color.red);
		for(Point p: line) {
			g.drawOval(p.x, p.y, 5, 5);
		}
		g.setColor(Color.green);
		if(farthest != null) {
			for(Point p: farthest) {
				g.drawOval(p.x, p.y, 5, 5);
			}
		}
		
		
	}

}
