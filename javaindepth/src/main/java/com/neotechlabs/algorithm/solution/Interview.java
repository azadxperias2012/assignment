package com.neotechlabs.algorithm.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interview {

    public static final Pattern EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    private static final int MOD_NUMBER = 1000000007;

    public static int solution(int A, int B, int N) {
        int a = A % MOD_NUMBER;
        int b = B % MOD_NUMBER;

        if(N == 0) {
            return a;
        } else if(N == 1) {
            return b;
        } else {
            int result = 0, i;
            for (i = 2; i <= N; i++) {
                result = (a + b) % MOD_NUMBER;
                a = b;
                b = result;
            }
            return result;
        }
    }

    static int MAX = 1000;
    static int f[] = new int[MAX];

    public static int solution1(int A, int B, int N) {
        // Base cases
        if (N == 0)
            return (f[N] = A % MOD_NUMBER);

        if (N == 1)
            return (f[N] = B % MOD_NUMBER);

        if (f[N] != 0)
            return f[N];

        int k = (N & 1) == 1? (N + 1) / 2
                : N / 2;

        f[N] = (N & 1) == 1 ? ((solution1(A, B, k) + solution1(A, B, k - 1)) % MOD_NUMBER)
                : (((2 * solution1(A, B, k - 1) + solution1(A, B, k)) * solution1(A, B, k)) % MOD_NUMBER);

        return f[N];
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("RESULT: " + solution(3, 4, 5));
        System.out.println("RESULT: " + solution1(3, 4, 5));
//        System.out.println("RESULT: " + solution(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2, 1000000000));
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
//        System.out.println("TIME TAKEN: " + totalTime + " MS");

//        System.out.println(Math.log(999999999));
//        System.out.println("RESULT: " + solution(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2, 100));

    }
}
