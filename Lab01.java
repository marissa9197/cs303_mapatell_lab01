

import java.util.Arrays;
import java.util.Random;

public class Lab01 {
	
	public static String name = "Marissa Patellaro";
	public static String blazerID = "mapatell";
	
	//#(1): LINEAR SEARCH
	public static int linearSearch(int[] arr, int x) {
		// code linear search

		for (int i = 0; i < arr.length; i++){
			if (arr[i] == x){
				return i;
			}
		}
		return -1; // return index of x, -1 if not found
	}
	
	//#(2): BINARY SEARCH
	public static int binarySearch(int[] arr, int low, int high, int x) {
		// code binary search

		while (low <=high){
			int middleIndex = (low + high) / 2; // recursively cuts list in half
			int middleNumber = arr[middleIndex]; //identifying the value of kiddle index
			
			if (x == middleNumber){
				return middleIndex;
			}
			if (x < middleNumber){
				high = middleIndex - 1;
			} else {
				low = middleIndex + 1;
			} 
		}
		return -1; // return index of x, -1 if not found
	}
	
	
	//#(3): TEST FUNCTIONS
	/*
	 * @param arr - randomly generated array
	 * @param keys - array of integers to search for in array
	 * 
	 * @return - an array of indices of each key's location, -1 if not found
	 */
	
	public static int[] linear_search_1000(int[] arr, int[] keys) {
		int[] result = new int [keys.length];

		for (int i = 0; i<keys.length; i++){
			result[i] = linearSearch(arr, keys[i]);
		} 
		return result;
			
		
	}
	
	public static int[] binary_search_1000(int[] arr, int[] keys) {
		//TODO: SORT THE ORIGINAL ARRAY (arr), DO NOT SET A NEW ARRAY EQUAL TO THE SORTED ARRAY
		Arrays.sort(arr);

		int [] result = new int[keys.length];
		for (int i = 0; i<keys.length; i++){
			result[i] = binarySearch(arr, 0, arr.length-1, keys[i]);
		}
		return result;
	}
	
	//############# DO NOT MODIFY CODE BELOW THIS LINE #############
	
	public static int[] gen_array(int n) {
		Random rand = new Random();
		int[] arr = new int[n];
		for (int i=0; i<n; i++) {
			arr[i] = rand.nextInt(n);
		}
		return arr;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Name: " + name);
		System.out.println("BlazerID: " + blazerID);
		
		System.out.println("------ Linear Search ------");
		
		int[] keys = gen_array(1000);
		for (int n=4; n<19; n++) {
			boolean is_true = true;
			int[] arr = gen_array((int)(Math.pow(2, n)));
			
			long start = System.nanoTime();
			int[] key_indices = linear_search_1000(arr, keys);
			long stop = System.nanoTime();
			
			for (int i=0; i<keys.length; i++) {
				int index = key_indices[i];
				if ((index != -1) && (keys[i] != arr[index])){
					is_true = false;
				}	
			}
			
			System.out.println("ArraySize: 2**" + n + " || Correct: " + is_true + " || Time: " + (stop-start) + "ns");
		}
		
		System.out.println("------ Binary Search ------");
		
		for (int n=4; n<19; n++) {
			boolean is_true = true;
			int[] arr = gen_array((int)(Math.pow(2, n)));
			
			long start = System.nanoTime();
			int[] key_indices = binary_search_1000(arr, keys);
			long stop = System.nanoTime();

			if (n == 4) {
				int[] sort_arr = arr.clone();
				Arrays.sort(sort_arr);
				boolean sorting = true;
				for (int i = 0; i < arr.length; i++) {
					if (arr[i] != sort_arr[i]) {
						sorting = false;
					}
				}
				System.out.println("Sorting array check: " + sorting);
			}
			
			for (int i=0; i<keys.length; i++) {
				int index = key_indices[i];
				if ((index != -1) && (keys[i] != arr[index])){
					is_true = false;
				}	
			}
			
			System.out.println("ArraySize: 2**" + n + " || Correct: " + is_true + " || Time: " + (stop-start) + "ns");
		}
	}
}

