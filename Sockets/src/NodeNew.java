import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NodeNew {
	/*private int port;
	
	public NodeNew(int port) {
		this.port = port;
	}
	*/

	public static void main(String[] args) {
	
		try {
			System.out.println("About to call");
			Socket s = new Socket("localhost", 10000);
			System.out.println("Connected to port:"+9000);
			// DataOutputStream
			ObjectOutputStream oos = new 
			ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois = new 
			ObjectInputStream(s.getInputStream());
			int lowerBound = (int)ois.readObject();
			int upperBound = (int)ois.readObject();
			
			System.out.println("Lower bound "+lowerBound+" Upper Bound "+ upperBound);
			
			int localPrime = 0;
			for(int i = lowerBound; i < upperBound; i++) {
				if(isPrime(i)) {
					localPrime++;
				}
			}
			
			oos.writeObject(localPrime);
			
			//oos.writeObject(x+y);
			//s.close();

		} catch (Exception e) {
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
