package rodriguezProject2;

public class Algorithm {

	public Algorithm() {

	}

	public static boolean Algorithm1(int[] array) { // Command Cost. array.length = n
		for (int i = 0; i < array.length; i++) { // c1 n+1
			for (int j = 0; j < array.length; j++) { // c2 n(n+1)
				if (array[i] == array[j] * -1 && array[i] != 0) { // c3  0 or 1
					return true; // c4 1
				}
			}
		}
		return false; // c5 1
		// Runtime  O(n^2);
	}

	public static boolean Algorithm2(int[] array) { // Command Cost. array.length = n
		for (int i = 0; i < array.length; i++) {
			if (binarySearch(array[i] * -1, array)) {
				return true;
			}
		}
		return false;

	}

	private static boolean binarySearch(int x, int[] array) { // Command Cost. array.length = n

		int first = 0;
		int last = array.length - 1;
		int middle;

		while (first <= last) { // c1 0 or n / 2
			middle = (first + last) / 2; // c2 1
			if (array[middle] == x && array[middle] != 0) { // c3 0 or 1
				return true; // c4 0 or 1
			} else if (x < array[middle]) { // c5 0 or 1
				last = middle - 1; // c6 0 or 1
			} else {
				first = middle + 1; // c7 0 or 1
			}
		}
		return false; // c8 0 or 1
		
		// Runtime O(n) ? 
	}

	public static boolean Algorithm3(int[] array) { // Command Cost. array.length = n
		int i = 0;
		int j = array.length - 1;

		while (i != j) { // c1 0 or n or n(n+1)/2
			if (array[i] + array[j] == 0) { // c2 0 or 1
				return true; // c3 0 or 1
			} else if (array[i] + array[j] > 0) { // c4 0 or 1
				i++; // c5 0 or 1
			} else {
				j--; // c6 0 or 1
			}
		}

		return false; // c7 0 or 1

	}
	
	// Runtime of O(n^2)

}
