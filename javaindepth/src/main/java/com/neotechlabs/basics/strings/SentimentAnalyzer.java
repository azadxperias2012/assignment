package com.neotechlabs.basics.strings;

import java.util.Arrays;

public class SentimentAnalyzer {
    // Tip: labeled continue can be used when iterating featureSet + convert review to lower-case
    public static int[] detectProsAndCons(String review, String[][] featureSet,
                                          String[] posOpinionWords,
                                          String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length]; // output
        // your code ~ you will be invoking getOpinionOnFeature
        label:
        for (int i = 0; i < featureSet.length; i++) {
            featureOpinions[i] = 0;
            for (String feature : featureSet[i]) {
                int opinion = getOpinionOnFeature(review.toLowerCase(), feature,
                        posOpinionWords, negOpinionWords);
                if(opinion != 0) {
                    featureOpinions[i] = opinion;
                    continue label;
                }
            }
        }
        return featureOpinions;
    }

    // First invoke checkForWasPhrasePattern and
    // if it cannot find an opinion only then invoke checkForOpinionFirstPattern
    private static int getOpinionOnFeature(String review, String feature,
                                           String[] posOpinionWords,
                                           String[] negOpinionWords) {
        // your code
        int opinion = 0;
        opinion = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        return (opinion == 0 ? checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords) : opinion);
    }

    // Tip: Look at String API doc. Methods like indexOf, length, substring(beginIndex), startsWith can come into play
    // Return 1 if positive opinion found, -1 for negative opinion, 0 for no opinion
    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private static int checkForWasPhrasePattern(String review, String feature,
                                                String[] posOpinionWords,
                                                String[] negOpinionWords) {
        int opinion = 0;
        String pattern = feature + " was ";
        // your code
        if(review.contains(pattern)) {
            for(String positiveOpinion : posOpinionWords) {
                int index = review.indexOf(pattern + positiveOpinion);
                if(index > -1) {
                    opinion = 1;
                    break;
                }
            }
            if(opinion == 0) {
                for (String negativeOpinion : negOpinionWords) {
                    int index = review.indexOf(pattern + negativeOpinion);
                    if (index > -1) {
                        opinion = -1;
                        break;
                    }
                }
            }
        }
        return opinion;
    }

    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private static int checkForOpinionFirstPattern(String review, String feature,
                                                   String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        // Extract sentences as feature might appear multiple times.
        // split() takes a regular expression and "." is a special character
        // for regular expression. So, escape it to make it work!!
        String[] sentences = review.split("\\.");
        int opinion = 0;
        // your code for processing each sentence. You can return if opinion is found in a sentence (no need to process subsequent ones)
        for (String sentence : sentences) {
            if(sentence.contains(feature)) {
                for (String positiveOpinion : posOpinionWords) {
                    String pattern = positiveOpinion + " " + feature;
                    if (sentence.indexOf(pattern) > -1) {
                        opinion = 1;
                        break;
                    }
                }
            }
        }
        if(opinion == 0) {
            for (String sentence : sentences) {
                if(sentence.contains(feature)) {
                    for (String negativeOpinion : negOpinionWords) {
                        String pattern = negativeOpinion + " " + feature;
                        if (sentence.indexOf(pattern) > -1) {
                            opinion = -1;
                            break;
                        }
                    }
                }
            }
        }
        return opinion;
    }

    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";

        //String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";

        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}};
        String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome",
                "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}
