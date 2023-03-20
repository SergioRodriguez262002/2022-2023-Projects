import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Head {

	public static void main(String[] args) {
		int numNodeThreads = 4;
		int lowerRange = 1000;
		int upperRange = 1000000;

		Thread[] ths = new Thread[numNodeThreads];
		
		
		// running with no nodes
		/*
		System.out.println("Running with no nodes on the main thread");
		int noNodePrime = 0;
		long start = System.currentTimeMillis();
		for(int i = lowerRange; i < upperRange; i++) {
			if(isPrime(i)) {
				noNodePrime++;
			}
		}
		long stop = System.currentTimeMillis();
		System.out.println("Total primes no nodes "+noNodePrime+" time: "+(int)((stop-start)/1000));
		
		*/
		
		// start nodes
		for(int i = 0; i < numNodeThreads; i++) {
			Node nd = new Node(i, 8000 + i);
			Thread th = new Thread(nd);
			ths[i] = th;
			th.start();
		}
		
		/*
		 * Running four nodes connected to one head 
		 * 
		 * each client has a certain range to find primes 
		 * the heads job is to write the highs and lows
		 * 
		 * clients read a write back to the head. 
		 * 
		 * threads arent running the nodes 
		 * 
		 * 
		 * For each client, 
		 * 
		 * For each connection the server accepts need a new thread
		 * that writes the range from the client and read.
		 * 
		 * Nodes set up the socket and the head accepts the call
		 * 
		 * 
		 * */
		
		try {
			Socket[] sk = new Socket[numNodeThreads];
			/*
			 * ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			 * */
			int[] job = new int[2];
			int sep = (upperRange-lowerRange)/numNodeThreads;
			
			
			
			ArrayList oosArray = new ArrayList();
			ArrayList oisArray = new ArrayList();
			//ObjectOutputStream[] oos;
			//ObjectInputStream[] ois;
			for(int i = 0; i < numNodeThreads; i++) {
				System.out.println("About to call node "+i);
				Socket s = new Socket("localhost", 8000+i);
				sk[i] = s;
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oosArray.add(oos);
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				oisArray.add(ois);
				System.out.println("Connected to node "+i);
			}
			
			System.out.println("Connected to all nodes");
			
			// Sending and recieving work serialy 
			long start = System.currentTimeMillis();
			int totalPrime = 0;
			for(int i = 0; i < numNodeThreads; i++) {
				// Sending a job
				ObjectOutputStream write = (ObjectOutputStream) oosArray.get(i);
				job[0] = lowerRange + i*sep;
				job[1] = lowerRange + (i+1)*sep;
				write.writeObject(job);
				
				// waiting for response
				ObjectInputStream read = (ObjectInputStream) oisArray.get(i);
				totalPrime += (int) read.readObject();
				write.close();
			}
			long stop = System.currentTimeMillis();
			
			System.out.println("Total prime "+totalPrime+" Time node threaded "+((stop-start)/1000)+" seconds");
			
			
			
			
			
			
			
			/*// Send work
			
			for(int i = 0; i < numNodeThreads; i++) {
				
				ObjectOutputStream write = (ObjectOutputStream) oosArray.get(i);
				job[0] = lowerRange + i*sep;
				job[1] = lowerRange + (i+1)*sep;
				write.writeObject(job);
				
			}
			
			
			// getting results back from nodes
			int totalPrime = 0;
			for(int i = 0; i < numNodeThreads; i++) {
				ObjectInputStream read = (ObjectInputStream) oisArray.get(i);
				totalPrime += (int) read.readObject();
				ObjectOutputStream write = (ObjectOutputStream) oosArray.get(i);
				write.close();
				
			}
			
			System.out.println("Total prime "+totalPrime);
			*/
			
			
			
			
			
			
			
			
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {
			System.out.println("About to call");
			Socket s = new Socket("localhost", 8000);
			System.out.println("Connected");
// DataOutputStream
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			// oos.writeObject(10);

			int numNodes = 2;

			int[] range = new int[2];
			range[0] = 0; // lower range of prime finding for node 0
			range[1] = 10000; // upper range of prime finding for node 0
			oos.writeObject(range);

			int x = (int) ois.readObject();
			System.out.println(x);
			s.close();
		} catch (IOException | ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
