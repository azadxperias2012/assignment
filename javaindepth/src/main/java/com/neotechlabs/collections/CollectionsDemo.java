package com.neotechlabs.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {

	public static void main(String[] args) {
		// addAll
		List<String> list = new ArrayList<>();
		list.add("Raj");
		list.add("John");
		list.add("John");
		
		String[] array = {"Anita"};
		Collections.addAll(list, array);
		System.out.println("list: "+ list);
		
		// slower than above approach
		// list.addAll(Arrays.asList(array));
		
		// sort
		Collections.sort(list);
		System.out.println("Sorted list: "+ list);
		
		// binarySearch
		System.out.println("John is at index: " + Collections.binarySearch(list, "John"));
		System.out.println("Raj is at index: " + Collections.binarySearch(list, "Raj"));
		
		Collections.reverse(list);
		System.out.println("Reverse list: "+ list);
		
		Collections.swap(list, 0, 3);
		System.out.println("After swapping: "+ list);
		
		System.out.println("After swapping: "+ Collections.frequency(list, "John"));
		
		Collections.shuffle(list);
		System.out.println("Shuffled list: "+ list);
		
		System.out.println("Max: "+ Collections.max(list)); // natural ordering
		System.out.println("Min: "+ Collections.min(list));
		
		list.removeAll(Collections.singleton("John"));
		System.out.println("list: " + list);
		
		Collection<String> unmodifiable = Collections.unmodifiableCollection(list);
		System.out.println("unmodifiable: " + unmodifiable);
		System.out.println("Is Anita there?: " + unmodifiable.contains("Anita"));
		// can't modify throws UnsupportedOperationException
		//unmodifiable.add("John");
		// but can add to the original list will be reflected
		list.add("John");
		System.out.println("unmodifiable: " + unmodifiable);
	}

}
