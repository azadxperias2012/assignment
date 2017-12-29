package com.neotechlabs.algorithm.sorting;

public class QuickSortAlgorithm {

    public static void sort (int[] input) {
        sort(input,0, input.length - 1);
    }

    private static void sort (int[] input, int startPos, int endPos) {
        if(endPos <= startPos)
            return;
        int j = partition(input, startPos, endPos);
        sort(input, startPos, j - 1);
        sort(input, j + 1, endPos);
    }

    private static int partition(int[] input, int lo, int hi) {
        int i = lo, j = hi + 1;
        int a = input[lo];
        while (true) {
            while (input[++i] < a) {
                if(i == hi)
                    break;
            }

            while (a < input[--j]) {
                if(j == lo)
                    break;
            }

            if(i >= j)
                break;

            swap(input, i, j);
        }
        swap(input, lo, j);
        return j;
    }

    private static void swap (int[] input, int iPos, int jPos) {
        int temp = input[iPos];
        input[iPos] = input[jPos];
        input[jPos] = temp;
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
