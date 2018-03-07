package com.neotechlabs.interviews;

public class RakSolutionTwo {

	public static void main(String[] args) {
		int[] A = {1, 0, 2, 0, 0, 2};
		int[] A1 = {1, 5, 4};
		int[] A2 = {1, 1, 1};
		System.out.println(solution(A));
		System.out.println(solution(A1));
		System.out.println(solution(A2));
		//Math.pow(2, 10000) % 1000000007;
	}
	
	public static int solution(int[] A) {
        // write your code in Java SE 8
		int result = 0;
		
		long binarianOfA = binarianOf(A);
		if (binarianOfA >= 1) {
			result = secondArrLength(binarianOfA);
			if (result > A.length) {
				result = A.length;
			}
        }
		
		return result;
    }
	
	static int secondArrLength(long binarian) {
		int result = 0;
		long tempBinarian = binarian;
		if (binarian % 2 == 1) {			
			result++;
		}
		
		tempBinarian -= result;
		while(tempBinarian != 0) {
			long powerOfTwo = powerOfTwo(tempBinarian);
			tempBinarian -= powerOfTwo;
			result++;
		}
		return result;
	}
	
	static long powerOfTwo(long n) {
		long count = 0;
		while( n != 1) {
		    n  >>= 1;
		    count += 1;
		}
		
		Double val = Math.pow(2, count);
		return val.longValue();
	}
	
	static long binarianOf(int[] A) {
		long binarian = 0; 
		for (int i = 0; i < A.length; i++) {
			if (A[i] < 0) {
				binarian = -1;
				break;
			}		        
			Double val = (Math.pow(2, A[i]));
			binarian += val.longValue();
		}
		return binarian;
	}

}
