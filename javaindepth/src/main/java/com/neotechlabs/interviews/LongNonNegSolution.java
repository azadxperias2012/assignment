package com.neotechlabs.interviews;

public class LongNonNegSolution {

	public static void main(String[] args) {
		
		int[] A = {2, 3, 4, -1, -2, 1, 5, 6, 3};
		System.out.println("longestNonNegativeSubArrayLength: " + longestNonNegativeSubArrayLength(A));
		longestNonNegativeSubArray(A);
	}
	
	public static int longestNonNegativeSubArrayLength(int[] A) {
		int result = 0;
		int n = A.length;

		int currentCounter = 0;
		for (int i = 0; i < n; i++) {
			if (A[i] >= 0) {
				currentCounter++;
			} else {
				if (currentCounter > 0) {
					result = Math.max(result, currentCounter);
					currentCounter = 0;
				}
			}			
		}
		
		if (currentCounter > 0) {
			result = Math.max(result, currentCounter);
		}
		
		return result;
	}
	
	public static void longestNonNegativeSubArray(int[] A) {
		int result = 0;
		int n = A.length;
		
		int startIndex = 0;

		int currentCounter = 0;
		for (int i = 0; i < n; i++) {
			if (A[i] >= 0) {
				currentCounter++;
			} else {
				if (currentCounter > 0 && currentCounter > result) {
					result = currentCounter;
					startIndex = i + 1 - result;
					currentCounter = 0;
				}
			}			
		}
		
		if (currentCounter > 0 && currentCounter > result) {
			result = currentCounter;
			startIndex = n - result;
		}
		
		int endIndex = startIndex + result;
		for (int i = startIndex; i < endIndex; i++) {
			if(i == startIndex) {
				System.out.print("[");
			}
			System.out.print(A[i]);
			if (i != endIndex - 1) {
				System.out.print(", ");
			} else {
				System.out.println("]");
			}
		}
	}

}
