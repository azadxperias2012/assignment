package com.neotechlabs.algorithm.solution;

import java.util.Scanner;

public class Interview3 {
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

    private static int negativeStartIndex(int[] A) {
        int lo = 0, hi = A.length - 1;
        return negativeStartIndex(A, lo, hi);
    }

    private static int negativeStartIndex(int[] A, int lo, int hi) {
        int index;
        if(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(A[mid] < 0) {
                index = negativeStartIndex(A, mid + 1, hi);
            } else {
                index = negativeStartIndex(A, lo, mid - 1);
            }
        } else {
            index = hi;
        }
        return index;
    }

    private static long maxHappiness(int[] A) {
        long max = 0;
        for (int i : A) {
            max += i;
        }
        int negativeIndex = negativeStartIndex(A);
        if(negativeIndex > -1) {
            int negStart = negativeIndex;
            int posStart = negativeIndex + 1;
            long positiveMax = 0;
            long negativeMax = 0;
            for (int i = negStart; i > -1 ; i--) {
                negativeMax += A[i];
            }
            for (int i = posStart; i < A.length; i++) {
                positiveMax += A[i];
            }
            int noOfPositives = (A.length - posStart);
            while (negStart > -1) {
                long subMax = negativeMax + (positiveMax * noOfPositives);
                if(subMax > max) {
                    max = subMax;
                }

                if(negStart == -1)
                    break;
                negativeMax -= A[negStart];
                positiveMax += A[negStart--];
                noOfPositives++;
            }
        } else {
            max = max * A.length;
        }
        return max;
    }

    public static void main(String args[] ) throws Exception {
        byte t = 1;
        if(t < 1 || t > 8)
            return;
        while (t-->0) {
            int N = 3;
            int[] A = {-8, 0, -2, 2, 8};
            sort(A);
            System.out.println(maxHappiness(A));
        }
    }
}
