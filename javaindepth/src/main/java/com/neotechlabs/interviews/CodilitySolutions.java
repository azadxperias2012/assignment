package com.neotechlabs.interviews;

public class CodilitySolutions {
	
	public static void main(String[] args) {
		int[] A = {1, 1, -1, -1, -1, -1, -1, 1, 1};
		int [] A1 = {-1, -1, 1, -1, 1, 0, 1, -1, -1};
		System.out.println(longestNonNegativeSumSlice(A));
		System.out.println(longestNonNegativeSumSlice(A1));
	}
	
	private static int longestNonNegativeSumSlice(int[] A) {
		//1, 1, -1, -1, -1, -1, -1, 1, 1
		int longestSlice = 0;
		
		for (int i = 0; i < A.length; i++) {
			int sum = A[i];
			int nonNegativeSumSlice = 0;
			for (int j = i + 1; j < A.length; j++) {
				sum += A[j];
				if (sum >= 0) {
					nonNegativeSumSlice = j + 1 - i;
				}
			}
			longestSlice = Math.max(nonNegativeSumSlice, longestSlice);
			if (i >= A.length / 2) {
				if (longestSlice > i) {
					break;
				}
			}
		}	
		
		return longestSlice;
	}
	
//	private static int longestNonNegativeSumSlice(int[] A) {
//		//1, 1, -1, -1, -1, -1, -1, 1, 1
//		int longestSlice = 0;
//		
//		longestSlice = longestNonNegativeSumSlice(A, 0, A.length - 1);
//		
//		return longestSlice;
//	}
//
//	private static int longestNonNegativeSumSlice(int[] A, int low, int high) {
//		int longestSlice = 0;
//		
//		if (low == high) {
//			return 0;
//		}
//		
//		int mid = low + ((high - low) / 2);
//		int leftLongestSlice = longestNonNegativeSumSlice(A, low, mid);
//		int rightLongestSlice = longestNonNegativeSumSlice(A, mid + 1, high);
//		
//		//1, 1, -1, -1, -1, -1, -1, 1, 1
//		int sum = A[low];
//		for (int i = mid; i > low; i--) {
//			sum += A[i];
//			if (sum >= 0) {
//				leftLongestSlice = i - low + 1;
//			}
//		}
//		sum = A[mid + 1];
//		for (int i = mid + 2; i <= high; i++) {
//			sum += A[i];
//			if (sum >= 0) {
//				rightLongestSlice = high - i + 1;
//			}
//		}
//		
//		System.out.println("longestSlice before: " + longestSlice);
//		longestSlice = Math.max(longestSlice, leftLongestSlice + rightLongestSlice);
//		System.out.println("longestSlice after: " + longestSlice);
//
//		return longestSlice;
//	}

}
