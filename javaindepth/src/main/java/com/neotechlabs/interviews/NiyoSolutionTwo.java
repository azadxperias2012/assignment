package com.neotechlabs.interviews;

import java.util.Scanner;

public class NiyoSolutionTwo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int Q = scanner.nextInt();
		
		char[] A = new char[N];
		for (int a = 0; a < N; a++) {
			String input = scanner.next();
			A[a] = input.charAt(0);
		}

		for (int a = 0; a < N - 1; a++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
		}
		
		for (int a = 0; a < Q; a++) {
			int x = scanner.nextInt();
			String s = scanner.next();
		}
		scanner.close();	
	}
}
