
public class PrimeFinder {

	public static void main(String[] args) {
		int lowerRange = 0;
		int upperRange = 1000;
		int numPrimes = 0;
		
		long timeStart = System.currentTimeMillis();
		double maxRep = 1000;
		for(double timeRep = 0; timeRep < maxRep; timeRep++) {
			numPrimes = 0;
			for(int i = lowerRange; i <= upperRange; i++) {
				if(isPrime(i)) {
					numPrimes++;
					//System.out.print(i+" ");
				}
			}
		}
		long timeStop = System.currentTimeMillis();
		System.out.println("Primes in range "+lowerRange+" to "+upperRange+" is "+numPrimes);
		System.out.println("Runtime "+((timeStop - timeStart)/maxRep));
	}
	
	
	public static boolean isPrime(int num) {
		if(num == 0 || num == 1) {
			return false;
		}
		int div = 2;
		while(num % div != 0) {
			div++;
		}
		if(num == div) {
			return true;
		}
		return false;
	}

}

