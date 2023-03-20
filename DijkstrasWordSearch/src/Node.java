import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Node implements Comparator<Node>{
	private String word;
	private int edgeWeight, vd;
	private Node predecesor; // this is a pointer

	public Node(String word, int edgeWeight, int vd, Node predecesor) {
		this.word = word;
		this.edgeWeight = edgeWeight;
		this.vd = vd;
		this.predecesor = predecesor;
	}

	public String toString() {
		return "->|edge weight: " + edgeWeight + "  word: " + word + " length: "+vd+" Predecesor: "+predecesor.getWord()+" |";
		//return "->|" + edgeWeight + "  " + word + " |";
		//return "->|" + edgeWeight + "  " + word + " Vd "+vd+" |";
	}

	public String getWord() {
		return word;
	}

	public int getWeight() {
		return edgeWeight;
	}

	public int getVd() {
		return vd;
	}
	
	public void setVd(int newVd) {
		vd = newVd;
	}

	public Node getPred() {
		return predecesor;
	}
	
	public void setPred(Node newPred) {
		predecesor = newPred;
	}
	
	public static Node getObject(String name, HashMap<String, ArrayList<Node>> map) {
		ArrayList<Node> test = map.get(name);
		for(Node n: test) {
			if(name.equals(n.getWord())) {
				return n;
			}
		}
		return null;
	}

	@Override
	public int compare(Node o1, Node o2) {
		return Integer.compare(o1.getVd(), o2.getVd());
	}

}
