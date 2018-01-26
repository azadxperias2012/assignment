package com.neotechlabs.nestedclasses;

import java.util.Arrays;
import java.util.Comparator;

interface CacheIterator {
	boolean hasNext();
	Bookmark next();
}

class FooBar {
	protected int y = 0;

	FooBar(int x) {
		y = x;
	}

}

public class Cache {

	private Bookmark[] items;
	private int next = 0;
	
	private static final Comparator<Bookmark> RATING_COMPARATOR = new Comparator<Bookmark>() {
		
		{
			System.out.println("Instance Initializer");
		}
		
		@Override
		public int compare(Bookmark o1, Bookmark o2) {
			return o1.getRating() < o2.getRating() ? 1 : -1;
		}
		
	};
	
	public Cache(int size) {
		items = new Bookmark[size];
	}
	
	public void add(Bookmark item) {
		if (next < items.length)
			items[next++] = item;
	}
	
	public CacheIterator iterator() {
		int count = 0; // from java 8, effectively final, can't manipulate inside the anonuymous class
		//final int count = 0; // prior to java		
		CacheIterator iterator = new CacheIterator() {

			private int i = 0;

			@Override
			public boolean hasNext() {
				//count++; // Not possible
				//System.out.println(count); // this ok
				return i < items.length;
			}

			@Override
			public Bookmark next() {
				return items[i++];
			}

		};
		return iterator;
	}
	
//	private class MyCacheIterator implements CacheIterator {
//		private int i = 0;
//
//		@Override
//		public boolean hasNext() {
//			return i < items.length;
//		}
//
//		@Override
//		public Bookmark next() {
//			return items[i++];
//		}
//	}
	
	public static void main(String[] args) {
		Cache recommendedItems = new Cache(5);

		Bookmark item1 = new Bookmark();
		item1.setId(2000);
		item1.setTitle("Use Finally Liberally");
		item1.setRating(1.0);

		Bookmark item2 = new Bookmark();
		item2.setId(2001);
		item2.setTitle("How do I import a pre-existing Java project into Eclipse");
		item2.setRating(2.0);

		Bookmark item3 = new Bookmark();
		item3.setId(2002);
		item3.setTitle("Interface vs Abstract Class");
		item3.setRating(3.0);

		Bookmark item4 = new Bookmark();
		item4.setId(2003);
		item4.setTitle("NIO tutorial by Greg Travis");
		item4.setRating(4.0);

		Bookmark item5 = new Bookmark();
		item5.setId(2004);
		item5.setTitle("Virtual Hosting and Tomcat");
		item5.setRating(5.0);
		
		recommendedItems.add(item1);
		recommendedItems.add(item2);
		recommendedItems.add(item3);
		recommendedItems.add(item4);
		recommendedItems.add(item5);
		
		//CacheIterator iterator = recommendedItems.new MyCacheIterator(); // another way
		CacheIterator iterator = recommendedItems.iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
		
//		Arrays.sort(recommendedItems.items, new Comparator<Bookmark>() {
//			
//			@Override
//			public int compare(Bookmark o1, Bookmark o2) {
//				return o1.getRating() < o2.getRating() ? 1 : -1;
//			}
//			
//		});
		
		Arrays.sort(recommendedItems.items, RATING_COMPARATOR);
		System.out.println("\nSorted by rating (Anonymous Class) ...");
		iterator = recommendedItems.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
		
		new FooBar(5) {
			void go() {
				System.out.println("\nPrinting y in FooBar.go: " + y);
			}
		}.go();
		
		Arrays.sort(recommendedItems.items, Bookmark.RATING_COMPARATOR);
		System.out.println("\nSorted by rating (Static Member Class 1) ...");
		iterator = recommendedItems.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
		
		Arrays.sort(recommendedItems.items, new Bookmark.ComparatorList.StringLengthComparator());
		System.out.println("\nSorted by String length (Static Member Class 2) ...");
		iterator = recommendedItems.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}
	}

}
