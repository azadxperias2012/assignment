package com.neotechlabs.works;

import java.util.Scanner;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        TreeSet<String> reorderedInput = new TreeSet<>();

        for (String s : input.split(",")) {
            reorderedInput.add(s);
        }

        for (String o : reorderedInput) {
            System.out.println(o);
        }

        scanner.close();
    }
}
