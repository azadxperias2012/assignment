package com.neotechlabs.io;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class IODemo {
	public static void main(String[] args) {
		applyEncoding();
	}

	private static void applyEncoding() {
		System.out.println("\nInside applyEncoding ...");
		
		// Ensure Eclipse property is set as UTF8
		printEncodingDetails("a");
		printEncodingDetails("€");
		printEncodingDetails("\u1F602");
	}

	private static void printEncodingDetails(String symbol) {
		System.out.println("\nSymbol: " + symbol);
		
		try {
			System.out.println("ASCII: " + Arrays.toString(symbol.getBytes("US-ASCII")));
			System.out.println("ISO-8859-1: " + Arrays.toString(symbol.getBytes("ISO-8859-1")));
			System.out.println("UTF-8: " + Arrays.toString(symbol.getBytes("UTF-8")));
			System.out.println("UTF-16: " + Arrays.toString(symbol.getBytes("UTF-16")));
			System.out.println("UTF-16BE: " + Arrays.toString(symbol.getBytes("UTF-16BE")));
			System.out.println("UTF-16LE: " + Arrays.toString(symbol.getBytes("UTF-16LE")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
