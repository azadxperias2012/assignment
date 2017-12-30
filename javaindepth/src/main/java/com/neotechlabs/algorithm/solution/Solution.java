package com.neotechlabs.algorithm.solution;

import java.util.Scanner;

public class Solution {

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
            int noOfElements = (A.length - posStart);
            if(noOfElements == 1)
                return max;
            for (int i = posStart; i < A.length; i++) {
                positiveMax += A[i];
            }
            if(positiveMax * noOfElements  > max) {
                max = positiveMax * noOfElements;
            }
            while (true) {
                positiveMax = (A[negStart--] + positiveMax);
                long tempMax = positiveMax * (++noOfElements);
                if(tempMax > max) {
                    max = tempMax;
                } else {
                    break;
                }
            }
        } else {
            max = max * A.length;
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        byte t = scanner.nextByte();
        if(t < 1 || t > 8)
            return;
        while (t-->0) {
            int N = scanner.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = scanner.nextInt();
            }
            sort(A);
            System.out.println(maxHappiness(A));
        }
        scanner.close();
    }
}
