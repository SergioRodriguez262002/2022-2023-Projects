package edu.du.cs.srodriguez.painter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class PRec implements Runnable {

	private ObjectInputStream ois;
	private Hub hub;

	public PRec(Hub hub, ObjectInputStream ois) {
		// to call a method from the hub use hub
		this.hub = hub;
		this.ois = ois;
	}

	synchronized void addShape(PaintingPrimitive sh) {
		System.out.println("Shape added");
		hub.redistributeAll(sh);
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				PaintingPrimitive sh = (PaintingPrimitive) ois.readObject();
				addShape(sh);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
