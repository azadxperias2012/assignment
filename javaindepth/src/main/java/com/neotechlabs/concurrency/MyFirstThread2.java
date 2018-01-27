package com.neotechlabs.concurrency;

public class MyFirstThread2 extends Thread {

	public void run() {
		System.out.println("MyFirstThread2: Inside run ...");
		go();
		
	}
	
	private void go() {
		System.out.println("MyFirstThread2: Inside go ...");
		more();
	}

	private void more() {
		System.out.println("MyFirstThread2: Inside more ...");
	}

	public static void main(String[] args) {
		Thread thread = new MyFirstThread2();
		thread.start();
		
		System.out.println("MyFirstThread2: Inside main ...");
	}
}
