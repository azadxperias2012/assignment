package com.neotechlabs.nestedclasses;

import java.io.StringWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class DataTranslator {
	
	public static String getBookAsXml(int id, String title, double rating, int fbLikesCount, int tweetCount) {
		class Book {
			int id;
			String title;
			double rating;
			int fbLikesCount;
			int tweetCount;
			
			public Book(int id, String title, double rating, int fbLikesCount, int tweetCount) {
				this.id = id;
				this.title = title;
				this.rating = rating;
				this.fbLikesCount = fbLikesCount;
				this.tweetCount = tweetCount;
			}
		}
		
		Book book = new Book(id, title, rating, fbLikesCount, tweetCount);
		
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("book", Book.class);
		StringWriter writer = new StringWriter();
		xstream.marshal(book, new PrettyPrintWriter(writer));
		
		return writer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(DataTranslator.getBookAsXml(2002, "Interface vs Abstaract Classes", 3.5, 120, 35));
	}

}