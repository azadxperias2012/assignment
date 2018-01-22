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
	}
	
	public void go(T list) {
		list.add(0, new Object());
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
