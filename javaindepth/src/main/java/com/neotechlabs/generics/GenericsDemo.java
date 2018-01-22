package com.neotechlabs.generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GenericsDemo<T extends ArrayList & Serializable> {

	public static void main(String[] args) {
		Container<String> stringStore = new Store<>();
		stringStore.set("java");
		System.out.println(stringStore.get());
		
		Container<Integer> integerStore = new Store<>();
		integerStore.set(1);
		System.out.println(integerStore.get());
		
		Container<List<Integer>> listStore = new Store<>();
		listStore.set(Arrays.asList(1, 2, 3));
		System.out.println(listStore.get());
		
		//GenericsDemo<List> list1 = new GenericsDemo<>();
		GenericsDemo<ArrayList> list2 = new GenericsDemo<>();
		//GenericsDemo<Collection> list3 = new GenericsDemo<>(); // Not possible to have class or subclass other than List
		
		// raw type demo
		//rawTypeDemo();
		
		List<String> strList1 = Arrays.asList("a", "b", "c");
		List<String> strList2 = Arrays.asList("b", "c", "d");
		//getCommonElemetsCount(strList1, strList2);
		
		// wild-cards
		getCommonElemetsCountWithWildcard(strList1, strList2);
		
		Container<?> someStore = stringStore;
		Object object = someStore.get();
		System.out.println("Stored element: " + object);
		
		List<Integer> intList1 = Arrays.asList(1, 2);
		List<Integer> intList2 = Arrays.asList(3, 4);
		//invalidAggregate(intList1, intList2, new ArrayList());
		
		//go(new ArrayList<Integer>());
		go(new Integer[1]);
	}
	
//	private static void invalidAggregate(List<?> l1, List<?> l2, List<?> l3) {
//		// cannot access class level types
//		l3.addAll(l1); // null ok
//		l3.addAll(l2);
//	}

	private static int getCommonElemetsCount(List list1, List list2) {
		System.out.println("\nInside getCommonElemetsCount ...");
		int count = 0;
		for (Object element : list1) {
			if (list2.contains(element)) {
				count++;
			}
		}
		System.out.println("Common elements count: " + count);
		return count;
	}
	
	private static int getCommonElemetsCountWithWildcard(List<?> list1, List<?> list2) {
		System.out.println("\nInside getCommonElemetsCountWithWildcard ...");
		int count = 0;
		for (Object element : list1) {
			if (list2.contains(element)) {
				count++;
			}
		}
		System.out.println("Common elements count: " + count);
		return count;
	}

	private static void rawTypeDemo() {
		System.out.println("\nInside rawTypeDemo ...");
		int ISBN = 150529677;
		List<Double> priceList = new ArrayList<>();
		HalfIntegrator.getPrice(ISBN, priceList);
		
		Double price = priceList.get(0);
		
	}

//	public void go(T list) {
//		list.add(0, new Object());
//	}

	// Invariance
	private static void go(List<Number> list) {}
	
	// Covariance
	private static void go(Number[] list) {
		// throws ArrayStoreException (runtime) if an Integer array is passed
		list[0] = 24.4;
	}
}

class HalfIntegrator {
	public static void getPrice(int ISBN, List priceList) {
		priceList.add(45);
	}
}

interface Container<T> {
	void set(T a);
	T get();
}

class Store<T> implements Container<T> {
	private T a;

	public void set(T a) {
		this.a = a;
	}

	public T get() {
		return a;
	}
}
