package com.neotechlabs.jvm;

public class GCDemo {
	static int[] iArray = new int[2*1024*1024];

	public static void main(String[] args) {
		System.out.println("HELLO GC ...");
	}
}
