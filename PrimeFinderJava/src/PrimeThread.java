import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrimeThread implements Runnable{
	private int tnum;
	private int start;
	private int stop;
	private int totalPrime;
	
	public PrimeThread(int tnum, int start, int stop) {// add aray
		this.tnum = tnum;
		this.start = start;
		this.stop = stop;
	}
	
	@Override
	public void run() {
		Lock lock = new ReentrantLock();
		System.out.println("in thread " + tnum + " " + start + " - " + stop);
		// Vec add
		int localPrimes = 0;
		for (int i = start; i < stop; i++) {
			if (isPrime(i)) {
				localPrimes++;
				// System.out.print(i+" ");
			}
		}
		
		
		 
		lock.lock();
		 
		totalPrime += localPrimes;
		 
		lock.unlock();
		
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
