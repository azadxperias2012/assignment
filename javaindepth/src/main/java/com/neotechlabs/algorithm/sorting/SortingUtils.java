package com.neotechlabs.algorithm.sorting;

public class SortingUtils {
    public static void swap (int[] input, int iPos, int jPos) {
        int temp = input[iPos];
        input[iPos] = input[jPos];
        input[jPos] = temp;
    }

    public static void printArray(int[] input) {
        for (int i : input) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
