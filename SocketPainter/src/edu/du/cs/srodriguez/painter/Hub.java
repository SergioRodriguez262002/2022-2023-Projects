package edu.du.cs.srodriguez.painter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Hub {
	ArrayList<ObjectOutputStream> oosArray = new ArrayList<ObjectOutputStream>();
	public Hub() {
		try {
			ServerSocket ss = new ServerSocket(9000);
			while (true) {
				try {
					System.out.println("Waiting for a call");
					Socket s = ss.accept(); // blocking
					System.out.println("Accepted");

					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

					oosArray.add(oos);

					PRec p = new PRec(this, ois);
					Thread painter = new Thread(p);
					painter.start();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void redistributeAll(PaintingPrimitive shape) {
		// one at a time
		for (ObjectOutputStream obj : oosArray) {
			try {
				obj.writeObject(shape);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void redistributeTextAll(String text) {
		for (ObjectOutputStream obj : oosArray) {
			try {
				obj.writeObject(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Hub hub = new Hub();
	}

}
