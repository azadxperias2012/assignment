package com.neotechlabs.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MeetupEventSimulator {
	
	public static class MeetupEvent {
		private String name;
		private AtomicInteger count = new AtomicInteger(1); // 1 for organizer
		
		public MeetupEvent(String name) {
			this.name = name;
		}
		
		public void attending(int guestCount) {
			if (guestCount == 1) {
				count.incrementAndGet();
			} else {
				count.addAndGet(guestCount);
			}
		}
		
		public void notAttending(int guestCount) {
			if (guestCount == 1) {
				count.decrementAndGet();
			} else {
				boolean updated = false;
				
				while (!updated) {
					int currentCount = count.get();
					int newCount = currentCount - guestCount;
					updated = count.compareAndSet(currentCount, newCount);
				}
			}
		}
		
		public int getCount() {
			return count.get();
		}
	}

	public static void main(String[] args) {
		MeetupEvent jugTrivandrum = new MeetupEvent("Trivandrum Java User Group");
		
		Thread user1 = new Thread(new Runnable() {
			@Override
			public void run() {
				jugTrivandrum.attending(4);
				System.out.println(Thread.currentThread().getName() + " : " + jugTrivandrum.getCount());
			}
		});
		
		Thread user2 = new Thread(new Runnable() {
			@Override
			public void run() {
				jugTrivandrum.attending(3);
				System.out.println(Thread.currentThread().getName() + " : " + jugTrivandrum.getCount());
				jugTrivandrum.notAttending(3);
				System.out.println(Thread.currentThread().getName() + " : " + jugTrivandrum.getCount());
			}
		});
		
		Thread user3 = new Thread(new Runnable() {
			@Override
			public void run() {
				jugTrivandrum.attending(1);
				System.out.println(Thread.currentThread().getName() + " : " + jugTrivandrum.getCount());
			}
		});
		
		user1.setName("User 1");
		user2.setName("User 2");
		user3.setName("User 3");
		
		user1.start();
		sleep(1);
		user2.start();
		sleep(2);
		user3.start();
		sleep(3);
		System.out.println("Total attending: " + jugTrivandrum.getCount());
	}

	private static void sleep(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
