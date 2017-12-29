package com.neotechlabs.algorithm.sorting;

public class InsertionSortAlgorithm {

    public static void sort (int[] input) {
        for (int i = 1; i < input.length; i++) {
            int j = i;
            while ((j > 0) && (input[j] < input[j - 1])) {
                SortingUtils.swap(input, j, j - 1);
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = {10, 5, 2, 7, 12, 9};
        System.out.println("BEFORE SORTING: ");
        SortingUtils.printArray(A);
        sort(A);
        System.out.println("AFTER SORTING: ");
        SortingUtils.printArray(A);
    }
}
