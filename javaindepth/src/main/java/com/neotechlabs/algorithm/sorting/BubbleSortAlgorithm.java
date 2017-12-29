package com.neotechlabs.algorithm.sorting;

public class BubbleSortAlgorithm {

    // TIME COMPLEXITY : O(n^2) (Quadratic)
    public static void sort (int[] input) {
        for (int i = input.length - 1; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                if(input[j] > input[j + 1]) {
                    SortingUtils.swap(input, j, j + 1);
                }
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
