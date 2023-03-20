import java.awt.Point;

public class TestRuntime {

	public static void main(String[] args) {
		
		int numTrials = 1000;
		int total = 1000;
		long start,stop;
		Point[] points;
		double bfr,dacr;
		
		System.out.println("N BruteForce DivideAndConquer");
		for(int i = 10; i < total; i++) {
			points = new Point[i];
			for (int t = 0; t < i; t++) {
				points[t] = new Point((int) (500 * Math.random()), (int) (500 * Math.random()));
			}
			start = System.currentTimeMillis();
			for(int j = 0; j < numTrials; j++) {
				BruteForcePairs.BruteForce(points);
			}
			stop = System.currentTimeMillis();
			
			bfr = ((stop-start)/(double)(numTrials));
			
			Point[] xSorted = DAC.quickSort(points, 0, points.length - 1, true);
			Point[] ySorted = DAC.quickSort(points, 0, points.length - 1, false);
			
			// When the sorting is addded the runtime becomes n^2
			start = System.currentTimeMillis();
			for(int j = 0; j < numTrials; j++) {
				
				DAC.DACPair(xSorted,ySorted);
			}
			stop = System.currentTimeMillis();
			
			dacr = (stop-start)/(double)(numTrials);
			
			System.out.println(i+"  "+(double)bfr+"  "+(double)dacr);
			
			
		}
		
	}
	
	

}
