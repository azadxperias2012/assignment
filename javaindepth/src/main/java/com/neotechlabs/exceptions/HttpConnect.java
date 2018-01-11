package com.neotechlabs.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HttpConnect {
	
	/**
	 * 
	 * @param destination
	 * @param data
	 * @param partner
	 * @throws IOException
	 * @throws IllegalArgumentException - if destination < 0 or > 1
	 */
	public static void send(int destination, String data, String partner) throws IOException {
		System.out.println("\nInside send ...");

		if (destination < 0 || destination > 1) {
			// unchecked exception, compiler will not warn
			throw new IllegalArgumentException();
		}
		
		if (destination == 0) {
			throw new FileNotFoundException();
		} else if (destination == 1) {
			throw new IOException();
		}

		System.out.println("\nEnd of send ...");
	}
}
