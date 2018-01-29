package com.neotechlabs.concurrency;

import java.util.concurrent.TimeUnit;

public class StopThead {
	
	private static volatile boolean stop;

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stop) {
					System.out.println("In while ... ");
				}
			}
		}).start();
		
		TimeUnit.SECONDS.sleep(1);
		stop = true;
	}

}
