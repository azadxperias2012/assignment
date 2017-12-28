package com.neotechlabs.datastructures;

import java.util.Scanner;

public class ArrayExample {

    private static void OneDArrayReverse() {
        // N - size of array 1 <= N <= 100
        // A - Array of N elements 0 <= A[i] <= 1000
        try(Scanner scanner = new Scanner(System.in)) {
            byte N = scanner.nextByte();

            if (N < 1) {
                return;
            }

            short[] A = new short[N];
            byte i = 0;
            for (; i < N; i++) {
                A[i] = scanner.nextShort();
            }

            i--;
            for (; i >= 0; i--) {
                System.out.println(A[i]);
            }
        }
    }

    private static void MicroAndArrayUpdate() {
        Scanner scanner = new Scanner(System.in);
        byte T = scanner.nextByte();
        while (T-- > 0) {
            int N = scanner.nextInt();
            int K = scanner.nextInt();
            int min = Integer.MAX_VALUE;
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = scanner.nextInt();
                if(A[i] < min) {
                    min = A[i];
                }
            }
            min = K - min;
            if(min < 0)
                min = 0;
            System.out.println(min);
        }
        scanner.close();
    }

    public static void main(String[] args) {
//        OneDArrayReverse();
//        MicroAndArrayUpdate();
    }
}
