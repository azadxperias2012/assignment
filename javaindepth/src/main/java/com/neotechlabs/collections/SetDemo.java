package com.neotechlabs.collections;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.neotechlabs.nestedclasses.Bookmark;

public class SetDemo {
	
	private static void hashSetDemo() {
		System.out.println("\nInside hashSetDemo ...");
		Set<String> set1 = new HashSet<>();
		set1.add("a");
		set1.add("b");
		set1.add("a");
		System.out.println("set1: " + set1);
		
		Book book1 = new Book("Walden", "Henry Thoreau", 1854);
		Book book2 = new Book("Walden", "Henry Thoreau", 1854);
		Set<Book> set2 = new HashSet<>();
		set2.add(book1);
		set2.add(book2);
		System.out.println("set2: " + set2);
	}
	
	private static void linkedHashSetDemo() {
		System.out.println("\nInside linkedHashSetDemo ...");
		Set<String> hashSet = new HashSet<>();
		hashSet.add("Raj");
		hashSet.add("John");
		hashSet.add("Anita");
		// Insertion-order not preserved
		System.out.println("hashSet: " + hashSet);
		
		Set<String> linkedHashSet = new LinkedHashSet<>();
		linkedHashSet.add("Raj");
		linkedHashSet.add("John");
		linkedHashSet.add("Anita");
		// Insertion-order preserved
		System.out.println("linkedHashSet: " + linkedHashSet);
	}
	
	private static void treeSetDemo() {
		System.out.println("\nInside treeSetDemo ...");
		Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book3 = new Book("Walden", "Henry Thoreau", 1854);
		Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);

		Set<Book> books = new TreeSet<>(new AuthorComparator());
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		
		for (Book book : books) {
			System.out.println(book);
		}
	}
	
	private static void treeSetDemo2() {
		System.out.println("\nInside treeSetDemo2 ...");
		NavigableSet<Integer> set = new TreeSet<>();
		set.add(5);
		set.add(23);
		set.add(74);
		set.add(89);
		
		System.out.println("lower: " + set.lower(74));
		System.out.println("floor: " + set.floor(74));
		System.out.println("ceiling: " + set.ceiling(74));
		System.out.println("higher: " + set.higher(74));
		
		System.out.println("first: " + set.first());
		System.out.println("last: " + set.last());
		
		System.out.println("set: " + set);
		
		NavigableSet<Integer> descendingSet = set.descendingSet();
		System.out.println("descendingSet: " + descendingSet);
		
		NavigableSet<Integer> headSet = set.headSet(74, true);
		System.out.println("headSet: " + headSet);
		
		headSet.add(6);
		System.out.println("headSet: " + headSet);
		System.out.println("set: " + set);
		
		headSet.add(4);		
		//headSet.add(75);

		System.out.println("set: " + set);
		
		SortedSet<Integer> subSet = set.subSet(5, 74);
		//subSet.add(2);
		
		set.add(25);
		System.out.println("subSet: " + subSet);
	}
	
	public static Set<Book> treeSetDemo3(Comparator comparator) {
		Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
		Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
		Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);
			  
		Set<Book> books = new TreeSet<>(comparator);
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);
	      
	    for (Book book : books) {
	    	System.out.println(book);
	    }
	    
	    System.out.println();
	    
	    return books;
	}
	
	public static void main(String[] args) {
		//hashSetDemo();
		//linkedHashSetDemo();
		//treeSetDemo();
		//treeSetDemo2();
		//treeSetDemo3(null);
		//treeSetDemo3(new PubDateAscComparator());
		//treeSetDemo3(new PubDateDescComparator());
		
		treeSetDemo3(new Book.PubDateComparators.PubDateAscComparator());
		treeSetDemo3(new Book.PubDateComparators.PubDateDescComparator());
	}
}

class Book implements Comparable {
	private String title;
	private String author;
	private int year;
	
	public static final Comparator<Book> TITLE_COMPARATOR = new TitleComparator();
	private static class TitleComparator implements Comparator<Book> {
		@Override
		public int compare(Book o1, Book o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
	}
	
	public static class PubDateComparators {

		public static class PubDateAscComparator implements Comparator<Book> {
			@Override
			public int compare(Book o1, Book o2) {
				Integer year1 = o1.getYear();
				Integer year2 = o2.getYear();
				if(year1.equals(year2)) {
					return TITLE_COMPARATOR.compare(o1, o2);					
				}
				return year1.compareTo(year2);
			}
		}

		public static class PubDateDescComparator implements Comparator<Book> {
			@Override
			public int compare(Book o1, Book o2) {
				Integer year1 = o1.getYear();
				Integer year2 = o2.getYear();
				if(year1.equals(year2)) {
					return TITLE_COMPARATOR.compare(o1, o2);
				}
				return year1.compareTo(year2) == 1 ? -1: 1;
			}
		}

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public Book(String title, String author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
//	@Override
//	public int hashCode() {
//		return title.hashCode();
//	}
//	
//	@Override
//	public boolean equals(Object o) {
//		return (year == ((Book)o).getYear()) && (author == ((Book)o).getAuthor());
//	}
//
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Object book) {
		// Utilizing String's compareTo
		return getTitle().compareTo(((Book)book).getTitle());
	}
}

class TitleComparator implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
	}
}

class AuthorComparator implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return ((Book)o1).getAuthor().compareTo(((Book)o2).getAuthor());
	}
}

class PubDateAscComparator implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
		Integer year1 = ((Book)o1).getYear();
		Integer year2 = ((Book)o2).getYear();
		if(year1.equals(year2)) {
			return ((Book)o1).getAuthor().compareTo(((Book)o2).getAuthor());
		}
		return year1.compareTo(year2);
	}
}

class PubDateDescComparator implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
		Integer year1 = ((Book)o1).getYear();
		Integer year2 = ((Book)o2).getYear();
		if(year1.equals(year2)) {
			return ((Book)o1).getAuthor().compareTo(((Book)o2).getAuthor());
		}
		
		int compareTo = year1.compareTo(year2);
		return compareTo == 1 ? -1: 1;
	}
}