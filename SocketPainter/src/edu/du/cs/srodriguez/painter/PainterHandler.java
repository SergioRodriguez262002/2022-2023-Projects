package edu.du.cs.srodriguez.painter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class PainterHandler implements Runnable {

	static ArrayList<Socket> socket = new ArrayList<Socket>();
	ServerSocket ss = null;

	public PainterHandler(ServerSocket ss) {
		this.ss = ss;
	}

	public PainterHandler() {

	}

	public synchronized void addSocket(Socket s) {
		socket.add(s);
	}
	
	public ArrayList<Socket> returnSockets(){
		return socket;
	}

	@Override
	public void run() {

		

		while (true) {
			try {

				System.out.println("Waiting for a call");
				Socket s = ss.accept(); // blocking
				System.out.println("Accepted");

				addSocket(s);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
