package com.neotechlabs.interviews;

import java.util.*;

public class RakSolution {

	public static void main(String[] args) {
		int[] A = { 1, 3, 6, 4, 1, 2 };
		System.out.println("Result: " + findMissing(A));
	}

	static int segregate(int arr[], int size) {
		int j = 0, i;
		for (i = 0; i < size; i++) {
			if (arr[i] <= 0) {
				int temp;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j++;
			}
		}
		return j;
	}

	static int findMissingPositive(int[] arr, int size) {
		int i;
		// Mark arr[i] as visited by making
		// arr[arr[i] - 1] negative. Note that
		// 1 is subtracted because index start
		// from 0 and positive numbers start from 1
		for (i = 0; i < size; i++) {
			if (Math.abs(arr[i]) - 1 < size && arr[Math.abs(arr[i]) - 1] > 0) {
				arr[Math.abs(arr[i]) - 1] = -arr[Math.abs(arr[i]) - 1];
			}
		}

		// Return the first index value at which
		// is positive
		for (i = 0; i < size; i++)
			if (arr[i] > 0)
				return i + 1;

		return size + 1;
	}

	static int findMissing(int arr[]) {
		// First separate positive and
		// negative numbers
		int size = arr.length;
		int shift = segregate(arr, size);
		int arr2[] = new int[size - shift];
		int j = 0;
		for (int i = shift; i < size; i++) {
			arr2[j] = arr[i];
			j++;
		}
		return findMissingPositive(arr2, j);
	}
}
