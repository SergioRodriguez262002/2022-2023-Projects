import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HeadNew {

	public static void main(String[] args) {
		// Setting up nodes
		int numNodes = 8;
		ObjectInputStream ois;
		ObjectOutputStream oos;

		/*
		 * two loop set up one server socket, only need to set up one server socket
		 * 
		 * another loop that accepts store sockets into an array list of sockets
		 * 
		 * another loop that make input and output streams, figire out the start stop
		 * write info serialy
		 * 
		 * then use threads to send work out
		 * 
		 * run head once
		 * 
		 * 
		 * 
		 */
		

		int lowerBound = 1000;
		int upperBound = 1000000;
		int sep = (upperBound - lowerBound) / numNodes;
		int totalPrime = 0;
		
		
		System.out.println("No thread test");
		long start = System.currentTimeMillis();
		for(int i = lowerBound; i < upperBound; i++) {
			if(isPrime(i)) {
				totalPrime++;
			}
		}
		long stop = System.currentTimeMillis();
		
		System.out.println("No thread test, primes: "+totalPrime+" time: "+(int)(stop-start)/1000);
		
		totalPrime = 0;

		// Establishing connection with each node
		ArrayList node_sockets = new ArrayList();
		try {
			ServerSocket ss = new ServerSocket(10000);
			for (int i = 0; i < numNodes; i++) {

				System.out.println("Waiting for a call");
				Socket s = ss.accept(); // blocking
				System.out.println("Accepted");

				node_sockets.add(s);

			}

			/*
			 * for (int i = 0; i < numNodes; i++) { // Sending jobs serialy Socket s =
			 * (Socket) node_sockets.get(i);
			 * 
			 * ois = new ObjectInputStream(s.getInputStream()); oos = new
			 * ObjectOutputStream(s.getOutputStream());
			 * 
			 * oos.writeObject(lowerBound + i * sep); oos.writeObject(lowerBound + (i + 1) *
			 * sep);
			 * 
			 * System.out.println("Node: "+i+" range "+(lowerBound + i *
			 * sep)+" to "+(lowerBound + (i + 1) * sep));
			 * 
			 * // waiting for response serialy
			 * 
			 * int nodePrime = (int) ois.readObject();
			 * 
			 * totalPrime += nodePrime;
			 * 
			 * }
			 */

			System.out.println("Total prime serial " + totalPrime);

			// Threading
			ClientHandler node = new ClientHandler();

			Thread[] ths = new Thread[numNodes];
			
			
			for (int i = 0; i < numNodes; i++) {
				Socket s = (Socket) node_sockets.get(i);

				int lower = lowerBound + (i * sep);
				int upper = lowerBound + ((i + 1) * sep);
				// public ClientHandler(int tnum, Socket s, int lowerBound, int upperBound) {//
				// add aray

				node = new ClientHandler(i, s, lower, upper);
				Thread th = new Thread(node);
				ths[i] = th;
				th.start(); // fork
			}

			totalPrime = 0;
			
			start = System.currentTimeMillis();
			for (int i = 0; i < numNodes; i++) {
				try {
					ths[i].join();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			stop = System.currentTimeMillis();
			totalPrime += node.returnPrimes();
			
			System.out.println("Thread test, primes: "+totalPrime+" time: "+(int)(stop-start)/1000);

			// ois = new ObjectInputStream(s.getInputStream());
			// oos = new ObjectOutputStream(s.getOutputStream());

			/*
			 * oos.writeObject(5); oos.writeObject(10); int x = (int) ois.readObject();
			 * System.out.println("x+y "+x);
			 */

			/*
			 * 
			 * for(int x = 0; x < numNodes; x++) { oos.writeObject(lowerBound + x*sep);
			 * oos.writeObject(lowerBound + (x+1)*sep);
			 * 
			 * }
			 * 
			 * int x = (int) ois.readObject();
			 * 
			 * System.out.println("Total prime "+x);
			 */

		} catch (IOException e) {
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
