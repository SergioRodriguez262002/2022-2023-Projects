import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, String> dictionary = new HashMap<String, String>();
		try {
			System.out.println("Reading Dictionary and recording to hashmap:");
			Scanner file = new Scanner(new File("Dict.txt"));
			String key, value;
			while (file.hasNext()) {
				String next = file.next();
				dictionary.put(next, next);
			}

			// the dictionary is used to create a quick search to check if a word exists

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Reading Dictionary and recording to hashmap:PASS");

		HashMap<String, ArrayList<Node>> map = new HashMap<String, ArrayList<Node>>();

		System.out.println("Creating graph:");
		PriorityQueue queue = new PriorityQueue();
		for (String s : dictionary.keySet()) {
			map.put(s, everyWord(s, dictionary, queue));
		}
		System.out.println("Creating graph:PASS");

		// test cases
		System.out.println("Test case for game and kite:");
		ArrayList<Node> test1 = Dijkstra.algorithm("game", "kite", queue, map);
		System.out.println("test 1: " + test1);
		System.out.println("Test case for test and jest:");
		ArrayList<Node> test2 = Dijkstra.algorithm("test", "jest", queue, map);
		System.out.println("test 1: " + test2);

		// User input text box
		while (true) { // keep running until program fully closes
			String wordStart, wordStop;
			boolean wordExists, sameLength;
			ArrayList<Node> result;

			Scanner input = new Scanner(System.in);
			do {
				System.out.print("\nEnter start word: ");
				wordStart = input.nextLine();
				wordExists = true;
				if (dictionary.get(wordStart) == null) {
					System.out.println("Word not found.");
					wordExists = false;
				}
			} while (!wordExists);

			do {
				System.out.print("\nEnter stop word: ");
				wordStop = input.nextLine();
				wordExists = true;
				if (dictionary.get(wordStop) == null) {
					System.out.println("Word not found.");
					wordExists = false;
				}
				sameLength = true;
				if (wordStop.length() != wordStart.length()) {
					System.out.println("Stop and start words must be the same length");
					sameLength = false;
				}

			} while (!wordExists && !sameLength);
			System.out.println("valid words calculating shortest path.");
			try {
				result = Dijkstra.algorithm(wordStart, wordStop, queue, map);
				System.out.println("Path from " + wordStart + " to " + wordStop);
				System.out.println(result);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Path from " + wordStart + " to " + wordStop + " does not exist.");
				queue = new PriorityQueue();
				for (String s : dictionary.keySet()) {
					map.put(s, everyWord(s, dictionary, queue));
				}
				
			}

			

		}

	}

	public static ArrayList<Node> everyWord(String word, HashMap<String, String> dictionary, PriorityQueue queue) {
		// LinkedList<Node> nodes = new LinkedList<Node>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		String testWord = "";
		// the new word can be sorted to a spot on a linked list everytime a new word is
		// discovered
		Node originalNode;
		originalNode = new Node(word, 0, Integer.MAX_VALUE, null);
		originalNode.setPred(originalNode);
		nodes.add(originalNode);
		queue.initSource(originalNode);

		Node node;
		for (int i = 0; i < word.length(); i++) {
			for (int j = 97; j < 123; j++) {
				// a = 97 x = 123
				testWord += word.substring(0, i) + ((char) (j)) + word.substring(i + 1);
				if (dictionary.get(testWord) != null && !testWord.equals(word)) {

					int dist = Math.abs((int) word.charAt(i) - j); // direct distance
					node = new Node(testWord, dist, Integer.MAX_VALUE, originalNode);
					nodes.add(node);
					queue.initSource(node);
				}
				testWord = "";
			}
		}
		Collections.sort(nodes, originalNode);
		return nodes;
	}

	public static void printAsList(ArrayList<Node> nodes) {
		for (Node n : nodes) {
			System.out.println(n);
		}
	}

}
