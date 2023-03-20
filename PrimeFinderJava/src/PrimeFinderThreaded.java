
public class PrimeFinderThreaded {

	public static void main(String[] args) {
		// ask about getting the tocal number of primes. 
		
		
		int lowerRange = 0;
		int upperRange = 10000;
		int numPrimes = 0;

		//int numPrimes = 0;
		
		int numThreads = 4;
		Thread[] ths = new Thread[numThreads];
		for (int i=0; i<numThreads; i++) {
			int start = ((upperRange - lowerRange)/numThreads) * i; // watch out for int div
			int stop = ((upperRange - lowerRange)/numThreads) * (i+1);
			
			PrimeThread pt = new PrimeThread(i, start, stop); // Pass the main prime count with an array containing all the prime counts
			Thread th = new Thread(pt);
			ths[i] = th;
			th.start();  // fork	
		}
		
		
		
		
		for (int i=0; i<numThreads; i++) {
			try {
				ths[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
