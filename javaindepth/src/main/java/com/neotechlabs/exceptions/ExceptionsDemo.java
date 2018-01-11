package com.neotechlabs.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionsDemo {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("\nInside main ...");
		try {
			share();
			System.out.println("After invoking share ...");
		} catch (FileNotFoundException e) {
			System.out.println("main: FileNotFoundException ...");
		} finally {
			System.out.println("Inside main's finally ...");
		}
		System.out.println("\nEnd of main ...");
	}

	private static void share() throws FileNotFoundException {
		System.out.println("\nInside share ...");

		try {
			String response = HttpConnect.send(1, "hello", "http://www.goodsnips.com");
			System.out.println("After invoking send ...");
			APIParser.parseSendResponseCode(response, "hello", "http://www.goodsnips.com");
		} catch (FileNotFoundException e) {
			System.out.println("share: FileNotFoundException ...");
			throw e;
		} catch (IOException e) {
			System.out.println("Connecting to a different server ...");
		} catch (APIFormatChangeException e) {
			// Item 65: Don't ignore exception's
			e.printStackTrace();
		} finally {
			System.out.println("Inside share's finally ...");
		}

		System.out.println("\nEnd of share ...");
	}

}
