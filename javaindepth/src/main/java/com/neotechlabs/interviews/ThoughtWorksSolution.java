package com.neotechlabs.interviews;

import java.util.Scanner;

public class ThoughtWorksSolution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		for (int a = 0; a < T; a++) {
			int N = scanner.nextInt();
			
			boolean isValidInput = true;
			int[] A = new int[N];			
			for (int k = 0; k < N; k++) {				
				A[k] = scanner.nextInt();
				if (A[k] < 1 || A[k] > 1000) {
					isValidInput = false;
					System.out.println("Invalid Data");
					break;
				}
			}
			
			if (isValidInput) {
				int result = distinctCountries(A);
				if (result > 0) {
					System.out.println(result);
				} else {
					System.out.println("Invalid Data");
				}
			}
		}
		
		scanner.close();
	}

	private static int distinctCountries(int[] A) {
		int numOfDistinctPeople = 0;
		int numOfPeople = A.length;
		
		int countNumOfPeople = 0;
		if (numOfPeople > 0 && numOfPeople <=100) {
			sort(A);
			boolean findDistinctPeople = false;
			int countOfSamePeople = 0;
			for (int i : A) {
				if (i > numOfPeople) {
					numOfDistinctPeople = 0;
					break;
				} else {
					
					if (i == 1) {
						numOfDistinctPeople++;
						countNumOfPeople++;
					} else {
						if (!findDistinctPeople) {
							findDistinctPeople = true;
							countOfSamePeople = i;
							countOfSamePeople--;
							numOfDistinctPeople++;
							countNumOfPeople += i;
						} else {
							if (countOfSamePeople == 0) {								
								findDistinctPeople = false;								
							} else if (countOfSamePeople < 0) {
								numOfDistinctPeople = 0;
								break;
							} else {
								countOfSamePeople--;
							}
						}
					}
					
				}				
			}
		}
		
		if (countNumOfPeople != numOfPeople) {
			numOfDistinctPeople = 0;
		}
		return numOfDistinctPeople;
	}
	
	public static void sort(int[] a) {		
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(int[] a, int lo, int hi) {
		if(hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	private static int partition(int[] a, int lo, int hi) {
		int i = lo, j = hi + 1;		
		int v = a[lo];
		while(true) {
			while(less(a[++i], v))
				if(i == hi)
					break;
			
			while(less(v, a[--j]))
				if(j == lo)
					break;
			
			if(i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	private static boolean less(Integer v, Integer w) {
		return v.compareTo(w) < 0;
	}
	
	private static void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

}
