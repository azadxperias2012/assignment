package com.neotechlabs.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class IODemo {
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

	static String inFileStr = "walden.jpg";
	static String outFileStr = "walden-out.jpg";

	private static void fileCopyNoBuffer() {
		System.out.println("\nInside fileCopyNoBuffer ...");

		long startTime, elapsedTime; // for speed benchmarking

		// Print file length
		File fileIn = new File(inFileStr);
		System.out.println("File size is " + fileIn.length() + " bytes");

		try (FileInputStream in = new FileInputStream(inFileStr);
				FileOutputStream out = new FileOutputStream(outFileStr)) {
			startTime = System.nanoTime();
			int byteRead;
			// Read a raw byte, returns an int of 0 to 255.
			while ((byteRead = in.read()) != -1) {
				// Write the least-significant byte of int, drop the upper 3 bytes
				out.write(byteRead);
			}
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void fileCopyWithBufferAndArray() {
		System.out.println("\nInside fileCopyWithBufferAndArray ...");

		long startTime, elapsedTime; // for speed benchmarking
		startTime = System.nanoTime();

		// Print file length
		File fileIn = new File(inFileStr);
		System.out.println("File size is " + fileIn.length() + " bytes");

		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {

			byte[] byteBuf = new byte[4000];
			int numBytesRead;
			while ((numBytesRead = in.read(byteBuf)) != -1) {
				out.write(byteBuf, 0, numBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		elapsedTime = System.nanoTime() - startTime;
		System.out.println("fileCopyWithBufferAndArray: " + (elapsedTime / 1000000.0) + " msec");
	}

	public static void main(String[] args) {
		// applyEncoding();
		// fileCopyNoBuffer();
		fileCopyWithBufferAndArray();
	}
}
