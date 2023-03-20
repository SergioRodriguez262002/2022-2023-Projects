import java.util.Arrays;

public class Problem1 {

	public static void main(String[] args) {
		
		int[] array = {-5, 4, 3, -1, -3, 5, -2, 6, -7};
		int n = array.length;
		
		
		
		int offset = 0;
		for(int i = 0; i < n; i++) {
			if(array[i] > 0) {
				offset = i;
				i = n;
			}
		}	
		int temp;
		int current = offset;	
		for(int i = offset; i < n; i++) {
			if(array[i] < 0 && array[current] > 0) {
				temp = array[current];
				array[current] = array[i];
				array[i] = temp;	
				current+=1;
			}	
		}
		
		
		System.out.println("");
		for(int x = 0; x < n; x++) {
			System.out.print(array[x]+", ");
		}
		
		
	}
	
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}
