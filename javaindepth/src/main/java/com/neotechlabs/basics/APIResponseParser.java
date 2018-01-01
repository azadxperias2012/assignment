package com.neotechlabs.basics;

import com.neotechlabs.datastructures.linkedlist.DoubleLinkedList;

public class APIResponseParser {
    /**
     * Parses the input text and returns a Book instance containing
     * the parsed data.
     * @param response text to be parsed
     * @return Book instance containing parsed data
     */
    public static Book parse(String response) {
        Book book = new Book();
        String endRule = "<";

        String startRule = "<title>";
        String title = parse(response, startRule, endRule);
        book.setTitle(title);

        // Your code
        startRule = "<original_publication_year type=\"integer\">";
        String publicationYear = parse(response, startRule, endRule);
        book.setPublicationYear(Integer.valueOf(publicationYear));

        startRule = "<image_url>";
        String imageUrl = parse(response, startRule, endRule);
        book.setImageUrl(imageUrl);

        startRule = "<name>";
        String author = parse(response, startRule, endRule);
        book.setAuthor(author);

        startRule = "<average_rating>";
        String averageRating = parse(response, startRule, endRule);
        book.setAverageRating(Double.valueOf(averageRating));

        startRule = "<ratings_count type=\"integer\">";
        String ratingsCount = parse(response, startRule, endRule).replaceAll(",", "");
        book.setRatingsCount(Integer.valueOf(ratingsCount));
        return book;
    }

    // write overloaded parse method with the 3 parameters response, startRule, endRule
    private static String parse(String response, String startRule, String endRule) {
        int startIndex = response.indexOf(startRule);
        int endIndex = response.indexOf(endRule, startIndex + 1);
        int startResponseIndex = startIndex + startRule.length();
        return response.substring(startResponseIndex, endIndex);
    }

    public static void main(String[] args) {
        Book parse = parse("<average_rating>3.79</average_rating><name>Henry David Thoreau</name><ratings_count type=\"integer\">1,16,315</ratings_count><original_publication_year type=\"integer\">1854</original_publication_year><id type=\"integer\">16902</id><title>Walden</title><image_url>http://images.gr-assets.com/books/1465675526m/16902.jpg</image_url>");
        System.out.println(parse.getTitle());
        System.out.println(parse.getPublicationYear());
        System.out.println(parse.getImageUrl());
        System.out.println(parse.getAuthor());
        System.out.println(parse.getAverageRating());
        System.out.println(parse.getRatingsCount());
    }
}
