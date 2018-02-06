package com.neotechlabs.interviews;

import java.util.Scanner;

public class SolutionOne {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		for (int a = 0; a < T; a++) {
			int N = scanner.nextInt();
			
			long[] A = new long[N];
			for (int k = 0; k < N; k++) {
				A[k] = scanner.nextLong();
			}
			
			System.out.println(maxContiguousLoot(A, N));			
		}
		
		scanner.close();
	}
	
	static long maxContiguousLoot(long[] A, int N) {
		if (N == 1) {
			return A[0];
		} else if (N == 2) {
			return (2 * Math.min(A[0], A[1]));
		}
		
		long[] maxStolen = new long[N];
		
		maxStolen[0] = A[0];
		maxStolen[1] = Math.min(maxStolen[0], A[1]);
		
		for (int i = 2; i < N; i++) {
			maxStolen[i] = Math.min(maxStolen[i-1], A[i]);
		}
		
		long maxContiguous = maxStolen[0];
		
		long stolen = maxStolen[0];
		long cumulativeStolen = stolen;
		for (int i = 1; i < N; i++) {
			if (stolen == maxStolen[i]) {
				cumulativeStolen += maxStolen[i];
			} else {
				maxContiguous = Math.max(maxContiguous, cumulativeStolen);
				stolen = maxStolen[i];
				cumulativeStolen = stolen;
			}
		}
		maxContiguous = Math.max(maxContiguous, cumulativeStolen);
		
		return maxContiguous;
	}
	
	static int max(int a, int b) {
		return a > b ? a : b;
	}
	
	static void swap(int[] A, int i, int j) {		
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	
	static void SolutionAnswerOne() {
		Scanner scanner = new Scanner(System.in);
		
		int T = scanner.nextInt();
		for (int i = 0; i < T; i++) {
			boolean isPlayerOneTurn = true;
			String S = scanner.next();
			
			while (S.length() > 0) {				
				S = S.replaceAll(String.valueOf(S.charAt(0)), "");
				isPlayerOneTurn = !isPlayerOneTurn;
			}
			
			System.out.println(isPlayerOneTurn ? "Player2" : "Player1");
		}
		
		scanner.close();
	}
	
	static void SolutionAnswerTwo() {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		for (int a = 0; a < T; a++) {
			int N = scanner.nextInt();
			
			int[] A = new int[N];
			int minIndex = 0;
			int maxIndex = 0;
			for (int k = 0; k < N; k++) {
				A[k] = scanner.nextInt();
				if (A[k] > A[maxIndex]) {
					maxIndex = k;
				}
				if (A[k] < A[minIndex]) {
					minIndex = k;
				}
			}
			
			int max = 0;
			if (A.length == 2) {
				max = A[maxIndex] - A[minIndex];				
			} else {
				int j = 0;
				boolean swapped = false;
				for (int i = 1; i < A.length; i++) {
					j = i - 1;
					if (A[j] > A[i]) {
						if (!swapped) {
							swap(A, A.length - 1, j);
							swapped = true;
						} else {
							break;
						}
					}
					max = max((A[i] - A[j]), max);
				}
			}
			System.out.println(max);			
		}
		
		scanner.close();
	}

}
