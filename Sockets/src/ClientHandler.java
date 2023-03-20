import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private int tnum;
	private Socket s;
	private int lowerBound;
	private int upperBound;
	private static int totalPrime;

	public ClientHandler(int tnum, Socket s, int lowerBound, int upperBound) {// add aray
		this.tnum = tnum;
		this.s = s;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public ClientHandler() {// add aray
		
	}
	
	public synchronized void syncPrimes(int prime) {
		totalPrime += prime;
	}
	
	public int returnPrimes() {
		return totalPrime;
	}

	@Override
	public void run() {

		try {
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

			oos.writeObject(lowerBound);
			oos.writeObject(upperBound);

			System.out.println("Node: thread " + tnum + " range " + (lowerBound) + " to " + upperBound);

			// waiting for response

			int nodePrime = (int) ois.readObject();
			
			syncPrimes(nodePrime);

			System.out.println("Thread " + tnum + " total prime " + nodePrime);

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
