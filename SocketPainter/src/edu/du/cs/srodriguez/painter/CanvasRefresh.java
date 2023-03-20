package edu.du.cs.srodriguez.painter;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JPanel;

public class CanvasRefresh implements Runnable {

	PaintingPanel panel;
	ObjectInputStream ois;
	PaintingPrimitive recShape;

	public CanvasRefresh(PaintingPanel paintingPanel, ObjectInputStream ois) {
		panel = (PaintingPanel) paintingPanel;
		this.ois = ois;
	}

	@Override
	public void run() {
		while (true) {

			try {
				recShape = (PaintingPrimitive) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			((PaintingPanel) panel).addPrimitive(recShape);
		}

	}

}
