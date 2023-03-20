package edu.du.cs.srodriguez.painter;
import java.awt.*;
import java.io.Serializable;

public abstract class PaintingPrimitive implements Serializable{
	Color color;
	
	public PaintingPrimitive(Color color) {
		this.color = color;
	}
	
	//This is an example of the Template Design Pattern

	public final void draw(Graphics g) {
	    g.setColor(this.color);
	    drawGeometry(g);
	}

	protected abstract void drawGeometry(Graphics g);
	
	
}
