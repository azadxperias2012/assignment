package com.neotechlabs.collections;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueDemo {

	private static void dequeTest() {
		System.out.println("\nInside dequeTest ...");
		
		// Queue - FIFO
		Deque<String> deque = new ArrayDeque<>();
		deque.add("Walden");
		deque.add("Harry Potter");
		deque.add("Head First Java");
		
		System.out.println("\nPrinting Queue ...");
		System.out.println(deque.remove()); // or removeFirst
		System.out.println(deque.removeFirst());
		System.out.println(deque.remove());
		
		// Stack - LIFO
		deque.push("Walden");
		deque.push("Harry Potter");
		deque.push("Head First Java");
		
		System.out.println("\nPrinting Stack ...");
		System.out.println(deque.pop());
		System.out.println(deque.pop());
		System.out.println(deque.pop());
	}
	
	public static void main(String[] args) {
		dequeTest();
	}

}
