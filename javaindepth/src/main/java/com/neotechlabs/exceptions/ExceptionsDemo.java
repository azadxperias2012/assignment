package com.neotechlabs.exceptions;

import java.io.FileNotFoundException;

public class ExceptionsDemo {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("\nInside main ...");
		//try {
			share();
		//} catch (FileNotFoundException e) {
			//e.printStackTrace();
		//}
		System.out.println("\nEnd of main ...");
	}

	private static void share() throws FileNotFoundException {
		System.out.println("\nInside share ...");

		try {
			HttpConnect.send(0, "hello", "http://www.goodsnips.com");
		} catch (FileNotFoundException e) {
			throw e;
		}

		System.out.println("\nEnd of share ...");
	}

}
