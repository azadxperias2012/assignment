package com.neotechlabs.concurrency;

import java.util.concurrent.TimeUnit;

public class MyFirstThread {
	public static void main(String[] args) {
		Task task = new Task();
		Thread thread = new Thread(task); // NEW
		thread.start();
		
		try {
			//Thread.sleep(3000); // unit of time is milli-seconds
			TimeUnit.SECONDS.sleep(3);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Inside main ...");
	}
}

class Task implements Runnable {

	@Override
	public void run() {
		System.out.println("Inside run ...");
		try {
			TimeUnit.SECONDS.sleep(9);
		} catch (InterruptedException e) {
			System.out.println("interrupted!!!");
		}
		go();
		
	}
	
	private void go() {
		System.out.println("Inside go ...");
		more();
	}

	private void more() {
		System.out.println("Inside more ...");
	}
	
}
