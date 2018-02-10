package com.neotechlabs.interviews;

import java.util.Scanner;

public class ThoughtWorksSolutionTwo {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();
		for (int a = 0; a < T; a++) {
			int N = scanner.nextInt();
			int S = scanner.nextInt();
			
			int[] Arr = new int[N];
			for (int k = 0; k < N; k++) {
				Arr[k] = scanner.nextInt();
			}
			
			chooseKNumbers(Arr, S);
		}
		
		scanner.close();
	}

	private static void chooseKNumbers(int[] Arr, int S) {
		int largestDiffValue = 0;
		int kNumber = 0;
		for (int i = 2; i <= Arr.length; i++) {
			int[] aux = new int[i];
			for (int j = 0; j < i; j++) {
				aux[j] = Arr[j] * i;
			}
			sort(aux);
			for (int k = 1; k < aux.length; k++) {
				int diff = aux[k] - aux[0];
				if (diff < S) {
					if (diff > largestDiffValue) {
						largestDiffValue = diff;
						kNumber = i;
					}
				} else {
					break;
				}
			}
		}
		System.out.println(kNumber + " " + largestDiffValue);
	}
	
	private static void chooseKNumbers1(int[] Arr, int S) {
		int largestDiffValue = 1;
		int kNumber = 1;
		sort(Arr);
		
		for (int i = 2; i <= Arr.length; i++) {
			kNumber = i;
			int[] aux = new int[i];
			for (int j = 0; j < i; j++) {
				aux[j] = Arr[j] * i;
			}
			
			int min = aux[0];
			int max = aux[i];
			int diff = max - min;
			if (diff <= S && diff > largestDiffValue) {
				largestDiffValue = diff;
			}
		}
		
		
		System.out.println(kNumber + " " + largestDiffValue);
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
