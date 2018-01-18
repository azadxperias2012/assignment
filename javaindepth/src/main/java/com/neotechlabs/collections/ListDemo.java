package com.neotechlabs.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class ListDemo {

	private static List<Integer> arrayListDemo() {
		System.out.println("\nInside arrayListDemo ...");
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(3);
		list1.add(null);
		System.out.println("list1: "+ list1);
		
		list1.remove(3);
		System.out.println("list1: "+ list1);
		list1.remove(3);
		System.out.println("list1: "+ list1);
		
		list1.add(0, 10);
		System.out.println("list1: "+ list1);
		list1.set(0, 9);
		System.out.println("list1: "+ list1);
		
		// Bulk Operations
		Collection<Integer> list2 = new ArrayList<>();
		list2.add(9);
		list2.add(3);
		
		list1.removeAll(list2);
		System.out.println("list1: "+ list1);
		
		list2.add(1);
		
		list1.retainAll(list2);
		System.out.println("list1: "+ list1);
		
		list1.addAll(list2);
		System.out.println("list1: "+ list1);
		
		System.out.println("list1.set(2, 2): "+ list1.set(2, 2));
		
		// Search
		System.out.println("list1.contains(1): "+ list1.contains(1));
		System.out.println("list1.indexOf(1): "+ list1.indexOf(1));
		System.out.println("list1.lastIndexOf(1): "+ list1.lastIndexOf(1));
		System.out.println("list1: "+ list1);
		
		// Range-view: subList (new list is backed by original)
		List<Integer> list3 = list1.subList(2, 3);
		list3.set(0, 6);
		System.out.println("list1: "+ list1);
		list3.set(0, 7);
		System.out.println("list1: "+ list1);
		list1.set(2, 8);
		System.out.println("list3: "+ list3);
		// structural change will Generate ConcurrentModificationException
		//list1.add(0, 8);
		//System.out.println("list3: "+ list3);
		System.out.println("list1: "+ list1);
		
		/*for (int element : list1) {
			System.out.println("element: " + element);
			
			// Generates ConcurrentModificationException
			// fail-fast iterator gives the above exception
			if (element == 9) {
				list1.remove(Integer.valueOf(element));
			}
		}*/
		return list1;
	}
	
	private static void iteratorDemo(List<Integer> list1) {
		System.out.println("\nInside iteratorDemo ...");
		
		Iterator<Integer> iterator = list1.iterator();
		while (iterator.hasNext()) {
			int element = iterator.next();
			System.out.println("element: " + element);
			
			if (element == 9) {
				iterator.remove();
				// Following code generate IllegalStateException,
				// Since remove should be preceded by a next
				//iterator.remove();
				iterator.forEachRemaining(Filter::add);
			}
		}
		
		System.out.println("list1: " + list1);
		
		// forEach
		//list1.forEach(System.out::println);
		//list1.forEach(Filter::filter);
		//list1.forEach(new Filter()); // requires implementing Consumer
	}
	
	private static void listIteratorDemo() {
		System.out.println("\nInside listIteratorDemo ...");
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		
		for (ListIterator<String> iterator = list.listIterator(); iterator.hasNext();) {
			System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());
		}
		
		System.out.println("\nDemonstrating add, remove and set operations ...");
		for (ListIterator<String> iterator = list.listIterator(); iterator.hasNext();) {
			System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());
			if (iterator.nextIndex() == 1) {
				System.out.println("*** Adding test at index 1");
				iterator.add("test");
				System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());
				
				System.out.println("Removing test that was added at index 1");
				iterator.previous(); // "b"
				iterator.previous(); // "test"
				iterator.remove(); // remove "test"
				
				// Uncommenting below line gives an IllegalStateException as
				// set should be preceded by next/previous/set
				//iterator.set("test");
				System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());
				System.out.println("Setting element at index 1 as test");
				iterator.set("test");
				System.out.println("Setting element at index 1 as test1 to show that two set operations can be invoked sequentially");
				iterator.set("test1");
			}
		}
		
		System.out.println("\nDisplaying current elements ...");
		for (ListIterator<String> iterator = list.listIterator(); iterator.hasNext();) {
			System.out.println("iterator.nextIndex: " + iterator.nextIndex() + ", iterator.next: " + iterator.next());
		}
	}
	
	private static List<Integer> linkedListDemo() {
		System.out.println("\nInside linkedListDemo ...");
		List<Integer> list1 = new LinkedList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(3);
		list1.add(null);
		System.out.println("list1: "+ list1);
		
		list1.remove(3);
		System.out.println("list1: "+ list1);
		list1.remove(3);
		System.out.println("list1: "+ list1);
		
		list1.add(0, 10);
		System.out.println("list1: "+ list1);
		list1.set(0, 9);
		System.out.println("list1: "+ list1);
		
		// Bulk Operations
		Collection<Integer> list2 = new LinkedList<>();
		list2.add(9);
		list2.add(3);
		
		list1.removeAll(list2);
		System.out.println("list1: "+ list1);
		
		list2.add(1);
		
		list1.retainAll(list2);
		System.out.println("list1: "+ list1);
		
		list1.addAll(list2);
		System.out.println("list1: "+ list1);
		
		System.out.println("list1.set(2, 2): "+ list1.set(2, 2));
		
		// Search
		System.out.println("list1.contains(1): "+ list1.contains(1));
		System.out.println("list1.indexOf(1): "+ list1.indexOf(1));
		System.out.println("list1.lastIndexOf(1): "+ list1.lastIndexOf(1));
		System.out.println("list1: "+ list1);
		
		// Range-view: subList (new list is backed by original)
		List<Integer> list3 = list1.subList(2, 3);
		list3.set(0, 6);
		System.out.println("list1: "+ list1);
		list3.set(0, 7);
		System.out.println("list1: "+ list1);
		list1.set(2, 8);
		System.out.println("list3: "+ list3);
		// structural change will Generate ConcurrentModificationException
		//list1.add(0, 8);
		//System.out.println("list3: "+ list3);
		System.out.println("list1: "+ list1);
		
		/*for (int element : list1) {
			System.out.println("element: " + element);
			
			// Generates ConcurrentModificationException
			// fail-fast iterator gives the above exception
			if (element == 9) {
				list1.remove(Integer.valueOf(element));
			}
		}*/
		return list1;
	}
	
	public static void main(String[] args) {
		//List<Integer> list1 = arrayListDemo();
		//iteratorDemo(list1);
		//listIteratorDemo();
		linkedListDemo();
	}
}

class Filter implements Consumer {
	static void filter(Integer i) {
		if (i == 1)
			System.out.println(i);
	}

	@Override
	public void accept(Object i) {
		if ((int) i == 1)
			System.out.println(i);
	}
	
	static void add(Integer i) {
		System.out.println(i + 7);
	}
}
