package com.neotechlabs.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysDemo {
	
	private static void sequential() {
		System.out.println("\nInside sequential ...");
		// asList() ~ most commonly used
		String[] strArray = new String[] {"Raj", "Anita"};
		// Creating fixed size ArrayList from an Array
		List<String> strings = Arrays.asList(strArray);
		System.out.println("Fixed ArrayList: " + strings);
		// can't do the following throws exception
		//strings.remove(0);
		//strings.add("a");
		strings.set(0, "John");
		System.out.println("Updated Array: " + Arrays.toString(strArray));
		
		// Creating modifiable ArrayList from an Array
		strings = new ArrayList<>(Arrays.asList(strArray));
		strings.add("Raj");
		System.out.println("Modifiable ArrayList: " + strings);
		
		strings = Arrays.asList("Raj", "Anita");
		System.out.println("Fixed ArrayList using varargs: " + strings);
		
		List<String> fixedList = Arrays.asList(new String[3]);
		// prints 3
		System.out.println("fixedList.size(): " + fixedList.size());
		// recall from auto-boxing that arrays are not auto-boxable
		//List<Integer> fixedList2 = Arrays.asList(new int[2]);
		List<int[]> fixedList2 = Arrays.asList(new int[2]);
		// prints 1
		System.out.println("fixedList2.size(): " + fixedList2.size());
		
		// Sorting: void sort(Object[]) - Uses Merge-sort with natural ordering
		// Partially sorted array: far fewer than nlog(n) comparisons
		// Almost sorted array: approx. n comparisons, where n is array size
		Arrays.sort(strArray);
		System.out.println(Arrays.toString(strArray));
		// Additional Comments: Well-suited for merging 2 or more sorted arrays
		//						Concatenate the arrays & sort the resulting array!!
		
		// Sorting: void sort(int[]) - Uses Quick-sort
		int[] iArray = {47, 5, 23, 4, 78, 10, 59};
		Arrays.sort(iArray);
		System.out.println("Sorted iArray: " + Arrays.toString(iArray));
		
		// Binary Search: 	int binarySearch(int[], int)
		//					returns index if element found
		//					otherwise returns -(insertion point) - 1
		//					input array must be sorted. Otherwise, behavior is undefined
		System.out.println("index returned by binary search: " + Arrays.binarySearch(new int[] {4, 23, 59}, 4));
		System.out.println("index returned by binary search: " + Arrays.binarySearch(new int[] {4, 23, 59}, 25));
		
		int[] newArray = Arrays.copyOf(iArray, 10);
		System.out.println("newArray: " + Arrays.toString(newArray));
		
		int[] newArray1 = new int[10];
		System.arraycopy(iArray, 0, newArray1, 0, iArray.length);
		System.out.println("newArray1: " + Arrays.toString(newArray1));
		
		Arrays.fill(newArray, 13);
		System.out.println("Fill with 13: " + Arrays.toString(newArray));
		
		System.out.println("Equals?: " + Arrays.equals(iArray, newArray));
		
		//Arrays.deepEquals(Object[], Object[])
		// returns true if arrays are deeply equal to one another
		// appropriate for nested arrays
		//int[] deepArray1 = {1, 2, 3}; // Covariance: Does not work as int[] is not a subtype of Object[]
		//int[] deepArray2 = {1, 2, 3};
		//int[][] deepArray1 = { {1, 2, 3} }; // this works
		//int[][] deepArray2 = { {1, 2, 3} };
		int[][][] deepArray1 = { { {1, 2, 3}, {4, 5, 6} } }; // this works
		int[][][] deepArray2 = { { {1, 2, 3}, {4, 5, 6} } };
		Integer[] deepArray3 = {1,2,3}; // this works
		Integer[] deepArray4 = {1,2,3};
		System.out.println("Deep Array Equals? " + Arrays.deepEquals(deepArray1, deepArray2));
		System.out.println("Deep Array Equals? " + Arrays.deepEquals(deepArray3, deepArray4));
		
		Object[] objArray = new int[][][] { { {1, 2, 3} } };
		int[][] ia = (int[][]) objArray[0];
		System.out.println(ia[0][2]);
	}

	public static void main(String[] args) {
		sequential();
	}

}
