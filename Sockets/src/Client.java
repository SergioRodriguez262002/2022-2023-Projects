import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try {
			System.out.println("About to call");
			Socket s = new Socket("localhost", 8000);
			System.out.println("Connected");
// DataOutputStream
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			oos.writeObject(5);
			int x = (int) ois.readObject();
			System.out.println(x);
			s.close();
		} catch (IOException | ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}