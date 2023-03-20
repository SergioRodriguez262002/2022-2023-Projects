package rodriguezProject2;

public class RodriguezProject2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Algorithm test = new Algorithm();

		int[] array1 = { 5, -7, 6, 3, 7 }; // Should return true
		int[] array0 = { 3, -4, 7 }; // Should return false
		
		System.out.println("Test algorithm 1 " + test.Algorithm1(array0));

		System.out.println("Test algorithm 2 " + test.Algorithm2(array0));

		System.out.println("Test algorithm 3 " + test.Algorithm3(array0));
		
		
		
		// Test Algorithim 1 
		
		// Make random array 
		int[] array4 = new int[50000];
		
		for(int j = 0; j < 50000; j++) {
			array4[j] =  (int) ((10*Math.random()) - 5);
			//System.out.println(array4[j]);
		}
		double numTrials = 10000;
		System.out.println(test.Algorithm1(array4));
		System.out.println("\nTest Algorithm 1 \n");
		System.out.println("n: \tTotal time \tTime per call");
		for(int i = 50000; i < 800001; i *= 2) {
			
			int numTrue = 0;
			int numFalse = 0;
			int[] array = new int[i];
			
			//for(int j = 0; j < i/2; j++) {
			//	array[j] =  2;
			//}
			for(int j = 0; j < i; j++) {
				array[j] =  (int) ((10*Math.random()) - 3);
			}
			
			
			
			long startTime = System.currentTimeMillis();
			for(int x = 0; x < numTrials; x++) {
				Algorithm.Algorithm1(array);
			}
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			
			System.out.println(i+"\t "+elapsedTime+"\t "+(elapsedTime/numTrials));
			
			
		}
		
		System.out.println("\nTest Algorithm 2 \n");
		System.out.println("n: \tTotal time \tTime per call");
		for(int i = 50000; i < 800001; i *= 2) {
			//double numTrials = 5000;
			int numTrue = 0;
			int numFalse = 0;
			int[] array = new int[i];
			
			//for(int j = 0; j < i/2; j++) {
			//	array[j] =  2;
			//}
			for(int j = 0; j < i; j++) {
				array[j] =  (int) ((10*Math.random()) - 3);
			}
			
			long startTime = System.currentTimeMillis();
			for(int x = 0; x < numTrials; x++) {
				Algorithm.Algorithm2(array);
			}
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			
			System.out.println(i+"\t "+elapsedTime+"\t "+(elapsedTime/numTrials));
			
			
		}
		
		System.out.println("\nTest Algorithm 3 \n");
		System.out.println("n: \tTotal time \tTime per call");
		for(int i = 50000; i < 800001; i *= 2) {
			//double numTrials = 5000;
			int numTrue = 0;
			int numFalse = 0;
			int[] array = new int[i];
			
			//for(int j = 0; j < i/2; j++) {
				//array[j] =  2;
			//}
			for(int j = 0; j < i; j++) {
				array[j] =  (int) ((10*Math.random()) - 3);
			}
			
			long startTime = System.currentTimeMillis();
			for(int x = 0; x < numTrials; x++) {
				Algorithm.Algorithm3(array);
			}
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			
			System.out.println(i+"\t "+elapsedTime+"\t "+(elapsedTime/numTrials));
			
			
		}
		
		
		
	}

}
