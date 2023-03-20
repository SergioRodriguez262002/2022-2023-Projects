package edu.du.cs.srodriguez.painter;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintingPanel extends JPanel {

	ArrayList<PaintingPrimitive> primitives = new ArrayList<PaintingPrimitive>();

	public PaintingPanel() {
		this.setBackground(Color.WHITE);
	}

	public void addPrimitive(PaintingPrimitive obj) {
		System.out.println("Primitive added");
		this.primitives.add(obj);
		this.repaint();
	}

	public Object returnPrimitives() {
		return primitives;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (PaintingPrimitive obj : primitives) { // I named my ArrayList primitives -- could also use a standard for
													// loop if you wish
			obj.draw(g);
		}
	}

}
