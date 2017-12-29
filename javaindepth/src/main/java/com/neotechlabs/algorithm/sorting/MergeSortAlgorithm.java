package com.neotechlabs.algorithm.sorting;

public class MergeSortAlgorithm {

    private static int[] aux;

    public static void sort (int[] input) {
        aux = new int[input.length];
        mergeSort(input,0, input.length - 1);
    }

    private static void mergeSort(int[] A, int startPos, int endPos) {
        if(endPos <= startPos)
            return;
        int midPos = startPos + (endPos - startPos) / 2;
        mergeSort(A, startPos, midPos);
        mergeSort(A, midPos + 1, endPos);
        merge(A, startPos, midPos, endPos);
    }

    private static void merge(int[] A, int startPos, int midPos, int endPos) {
        int i = startPos;
        int j = midPos + 1;

        for(int k = startPos; k <= endPos; k++) {
            aux[k] = A[k];
        }

        for(int k = startPos; k <= endPos; k++) {
            if(i > midPos) {
                A[k] = aux[j++];
            } else if(j > endPos) {
                A[k] = aux[i++];
            } else if(aux[j] < aux[i]) {
                A[k] = aux[j++];
            } else {
                A[k] = aux[i++];
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
