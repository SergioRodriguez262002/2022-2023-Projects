import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Node implements Runnable {
	private int nodeID;
	private int nodeSK;

	public Node(int nodeID, int nodeSK) {
		this.nodeID = nodeID;
		this.nodeSK = nodeSK;
	}

	@Override
	public void run() {
		System.out.println("Node " + nodeID);
		try {
			ServerSocket ss = new ServerSocket(nodeSK);
			while (true) {
				System.out.println("Waiting for a call");
				Socket s = ss.accept(); // blocking
				System.out.println("Accepted");
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				int[] x = (int[]) ois.readObject();

				// Prime finder serial
				int lowerRange = x[0];
				int upperRange = x[1];
				int numPrimes = 0;

				System.out.println("Lower range: " + lowerRange);
				System.out.println("Upper range: " + upperRange);

				long timeStart = System.currentTimeMillis();
				System.out.println("Prime finding in range " + lowerRange + "-" + upperRange);

				numPrimes = 0;
				for (int i = lowerRange; i <= upperRange; i++) {
					if (isPrime(i)) {
						numPrimes++;
						// System.out.print(i+" ");
					}
				}

				System.out.println("Primes in range " + lowerRange + " to " + upperRange + " is " + numPrimes);

				oos.writeObject(numPrimes);
			}
		} catch (IOException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean isPrime(int num) {
		if (num == 0 || num == 1) {
			return false;
		}
		int div = 2;
		while (num % div != 0) {
			div++;
		}
		if (num == div) {
			return true;
		}
		return false;
	}

}
