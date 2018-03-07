package com.neotechlabs.regexsamples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExSampleOne {
    public static void main(String[] args) {
        //firstMethod();
        String text = "This is the text which is to be searched " +
                "for occurrences of the word 'is'.";
        String regex = "is";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("found: " + count + " :"
                    + matcher.start() + " - " + matcher.end());
        }
    }

    private static void firstMethod() {
        String text = "This is the text to be searched " +
                "for occurrences of the http:// pattern.";
        String regex = ".*http://.*";
        boolean matches = Pattern.matches(regex, text);
        System.out.println("matches: " + matches);
    }
}
