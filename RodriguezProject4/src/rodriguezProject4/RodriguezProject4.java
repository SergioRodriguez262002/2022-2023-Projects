package rodriguezProject4;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

import edu.du.dudraw.DUDraw;

public class RodriguezProject4 {

	public static void main(String[] args) {
		Scanner file = null;
		HashMap<String, ArrayList<Integer>> allNames = new HashMap<String, ArrayList<Integer>>();

		// Try catch block that will contain the algorithim to read and record the names
		// on every year since 1880
		try {
			// These variables hold the counts of all people in a year in count, all females
			// per year in female count, and all males per year in malecount. These values
			// will be stored in their own hash table index
			int count = 0;
			int femaleCount = 0;
			int maleCount = 0;
			for (int i = 0; i < 142; i++) { // This for loop loops from 1 to 142 according to the number of years since
											// 1880 to 2021.
				file = new Scanner(new File("names/yob" + (1880 + i) + ".txt")); // directory of all the names
				String[] str = null;

				// Reseting to variales holding the data for the people per year
				count = 0;
				maleCount = 0;
				femaleCount = 0;

				// Iterating through each line in each year in this loop
				while (file.hasNext()) {
					str = file.next().trim().split(",");
					String key = str[0] + "," + str[1] + "," + (1880 + i) + "";
					int value = Integer.parseInt(str[2]);
					if (allNames.get(key) == null) {
						ArrayList<Integer> arr = new ArrayList<Integer>();
						arr.add(value);
						allNames.put(key, arr);
					} else {
						System.out.println("Collision!");
						allNames.get(key).add(value);
					}

					if (str[1].equals("F")) {
						femaleCount += value;
					} else {
						maleCount += value;
					}
					count += value;
				}

				if (allNames.get("TOTAL-PEOPLE-IN-YEAR-" + (1880 + i)) == null) {
					ArrayList<Integer> arr = new ArrayList<Integer>();
					arr.add(count);
					allNames.put("TOTAL-PEOPLE-IN-YEAR-" + (1880 + i), arr);
				} else {
					System.out.println("Collision! Total population");
					allNames.get("TOTAL-PEOPLE-IN-YEAR-" + (1880 + i)).add(count);
				}

				if (allNames.get("TOTAL-FEMALES-IN-YEAR-" + (1880 + i)) == null) {
					ArrayList<Integer> arr = new ArrayList<Integer>();
					arr.add(femaleCount);
					allNames.put("TOTAL-FEMALES-IN-YEAR-" + (1880 + i), arr);
				} else {
					System.out.println("Collision! Female count");
					allNames.get("TOTAL-FEMALES-IN-YEAR-" + (1880 + i)).add(femaleCount);
				}

				if (allNames.get("TOTAL-MALES-IN-YEAR-" + (1880 + i)) == null) {
					ArrayList<Integer> arr = new ArrayList<Integer>();
					arr.add(maleCount);
					allNames.put("TOTAL-MALES-IN-YEAR-" + (1880 + i), arr);
				} else {
					System.out.println("Collision! Male count");
					allNames.get("TOTAL-MALES-IN-YEAR-" + (1880 + i)).add(maleCount);
				}
			}

			// System.out.println(allNames.toString());
			System.out.println("HashMap size: " + allNames.size());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		for (String e : allNames.keySet()) {
			if (e.substring(0, 5).equals("Emma,")) {
				// System.out.println(e+" "+allNames.get(e));
			}
		}

		while (true) {
			Scanner keyboard = new Scanner(System.in);
			String name = null;
			String gender = null;
			boolean realName = false;

			do {
				System.out.print("Input a name: ");
				name = keyboard.next();
				name = name.trim();
				name = name.toLowerCase();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				for (String e : allNames.keySet()) {
					if (e.substring(0, name.length() + 1).equals(name + ",")) {
						realName = true;
						break;
					}
				}
				if (!realName) {
					System.out.println("Name does not exist");
				}

			} while (!realName);

			do {
				System.out.print("Input a gender: ");
				gender = keyboard.next();
				gender = gender.trim();
				gender = gender.toUpperCase();

				if (!gender.equals("F") && !gender.equals("M")) {
					System.out.println("Input F or M");
				}

			} while (!gender.equals("F") && !gender.equals("M"));

			int year;
			do {

				System.out.println("Input a year for most popular names: ");
				year = keyboard.nextInt();

				if (year < 1880 || year > 2021) {
					System.out.println("Invalid year, input a year between and including 1880 and 2021");
				}

			} while (year < 1880 || year > 2021);

			int names;

			System.out.println("Input number of top names to be displayed: ");
			names = keyboard.nextInt();

			String[] pop = mostPopularNames(allNames, year, names);

			System.out.println("Most popular names of " + year);
			for (int i = pop.length - 1; i > 0; i--) {
				System.out.println(pop[i]);
			}

			ArrayList<Double> data = queryName(allNames, name, gender);

			drawGraph(data, allNames, name);

		}

	}

	public static ArrayList<Double> queryName(HashMap<String, ArrayList<Integer>> map, String name, String gender) {
		ArrayList<Double> array = new ArrayList<Double>();
		String key = name + "," + gender + ",";

		// First year
		int first = 0;
		for (int i = 0; i < 142; i++) {
			if (map.get(key + (1880 + i) + "") != null) {
				first = i;
				break;
			}
		}
		// System.out.println("First " + first);

		for (int i = 0; i < 142; i++) {
			if (map.get(key + (1880 + i) + "") != null) {
				// System.out.println(map.get(key + (1880 + i) + ""));
				array.add((double) map.get(key + (1880 + i) + "").get(0));
			}
		}

		if (gender.equals("F")) {
			array.add(0.0);
		} else {
			array.add(1.0);
		}
		array.add((double) first);
		return array;

	}

	public static String[] mostPopularNames(HashMap<String, ArrayList<Integer>> map, int year, int n) {
		String[] nameList = new String[n];
		int[] nameVal = new int[n];

		// String[] str = new String[3];

		for (int i = 0; i < nameVal.length; i++) {
			nameVal[i] = i;
			nameList[i] = "" + i + "";
		}

		for (String e : map.keySet()) {

			String[] str = e.split(",");

			if (str.length == 1) {
				// System.out.println("not a name");
			}
			// System.out.println(str[0]+str[1]+str[2]);
			if (str.length != 1 && str[2].equals("" + year)) {
				// System.out.println(e);

				if (map.get(e).get(0) > nameVal[0]) {
					nameList[0] = e;
					nameVal[0] = map.get(e).get(0);
				}

				for (int i = 1; i < nameVal.length; i++) {
					if (map.get(e).get(0) > nameVal[i]) {
						nameVal[i - 1] = nameVal[i];
						nameVal[i] = map.get(e).get(0);
						nameList[i - 1] = nameList[i];
						nameList[i] = e;
					}
				}

			}
		}

		for (int i = 0; i < nameList.length; i++) {
			nameList[i] = nameList[i] + " - " + nameVal[i];
		}

		return nameList;
	}

	public static void drawGraph(ArrayList<Double> data, HashMap<String, ArrayList<Integer>> map, String name) {
		DUDraw.setCanvasSize(600, 500);
		DUDraw.clear();
		DUDraw.enableDoubleBuffering();
		DUDraw.text(0.5, 0.95, "first occurrence of " + name + " " + ((int) (data.get(data.size() - 1) + 1880)));
		double population;
		double width = 1.0 / (double) (data.size() - 2);
		double height;
		double[] dispArray = new double[data.size() - 3];
		for (int i = 0; i < data.size() - 3; i++) {
			if (data.get(data.size() - 2) == 0.0) {
				height = data.get(i) / map.get("TOTAL-FEMALES-IN-YEAR-" + (1880 + i)).get(0);
			} else {
				height = data.get(i) / map.get("TOTAL-MALES-IN-YEAR-" + (1880 + i)).get(0);
			}
			dispArray[i] = height;
		}
		// Get largest
		double largestRatio = 0.0;
		int mostPopularYear = 0;
		for (int i = 0; i < dispArray.length; i++) {
			if (dispArray[i] > largestRatio) {
				largestRatio = dispArray[i];
				mostPopularYear = (int) (data.get(data.size() - 1) + 1880) + i;
			}
		}

		DecimalFormat df = new DecimalFormat("#.##");

		DUDraw.text(0.5, 0.90, "Maximum frequency of " + df.format(largestRatio * 100) + " % in" + mostPopularYear);

		// Getting ratio for display
		double dispRatio = 0.0;

		while (largestRatio * dispRatio < 0.85) {
			dispRatio += 1;
		}

		for (int i = 0; i < dispArray.length; i++) {
			DUDraw.line(i * width, 0, i * width, dispRatio * dispArray[i]);
		}
		DUDraw.show();
	}
}
