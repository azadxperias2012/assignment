package com.neotechlabs.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.neotechlabs.Student;

public class MapDemo {	
	private static void hashMapDemo() {
		System.out.println("\nInside hashMapDemo ...");
		Map<String, Integer> map1 = new HashMap<>();
		map1.put("John", 25);
		map1.put("Raj", 29);
		map1.put("Anita", null);
		
		System.out.println(map1);
		
		map1.put("Anita", 23);
		System.out.println(map1);
		
		System.out.println("Contains John? " + map1.containsKey("John"));
		System.out.println("John's age: " + map1.get("John"));
		
		System.out.println("Iterating using keySet ...");
		Set<String> names = map1.keySet();
		for (String name : names) {
			System.out.println("Name: " + name + ", Age: " + map1.get(name));
		}
		
		System.out.println("Iterating using entrySet ...");
		Set<Entry<String, Integer>> mappings = map1.entrySet();
		for (Entry<String, Integer> mapping : mappings) {
			System.out.println("Name: " + mapping.getKey() + ", Age: " + mapping.getValue());
		}
		
		names.remove("Anita");
		System.out.println(map1);
		
		Map<String, Map<String, Object>> userProfile = new HashMap<>();
		
		Map<String, Object> profile = new HashMap<>();
		profile.put("age", 25);
		profile.put("dept", "CS");
		profile.put("city", "New York");
		userProfile.put("John", profile);
		
		profile = new HashMap<>();
		profile.put("age", 29);
		profile.put("dept", "ECE");
		profile.put("city", "New Jersey");
		userProfile.put("Raj", profile);
		
		System.out.println(userProfile);
		
		Map<String, Object> profile1 = userProfile.get("John");
		int age = (Integer) profile1.get("age");
		System.out.println("Age: " + age);
		
		profile1 = userProfile.get("Raj");
		String city = (String) profile1.get("city");
		System.out.println("City: " + city);
		
		HashMap<String, Integer> map2 = new HashMap<>(map1);
		System.out.println(map2);
		
		profile1.putAll(map2);
		System.out.println(profile1);
		
		profile1.clear();
		System.out.println(profile1);
		System.out.println(userProfile);
		
		Collection<Integer> values = map2.values();
		for (Integer value : values) {
			System.out.println(value);
		}
	}
	
	private static void immutableKeysDemo() {
		System.out.println("\nInside immutableKeysDemo ...");
		List<Integer> list = new ArrayList<>();
		list.add(1);
		
		Map<List<Integer>, Integer> map = new HashMap<>();
		map.put(list, 1);
		
		list.add(2);
		System.out.println(map.get(list));
		
		Student s = new Student(1, null);
		Map<Student, Integer> map2 = new HashMap<>();
		map2.put(s, 2);
		
		s.setName("John");
		System.out.println(map2.get(s));
	}

	private static void lruCacheTest() {
		System.out.println("\nInside lruCacheTest ...");
		// constructor for LRU cache
		//Map<String, String> lruCache = new LinkedHashMap<>(16, 0.75f, true);
		Map<String, String> lruCache = new LRUCache<>(16, 0.75f, true);
		lruCache.put("a", "A");
		lruCache.put("b", "B");
		lruCache.put("c", "C");
		System.out.println(lruCache);
		
		lruCache.get("a"); // multiple gets to "a" will not make a difference
		lruCache.get("a");
		lruCache.get("a");
		System.out.println(lruCache);
		lruCache.get("b");
		System.out.println(lruCache);
		
		lruCache.put("d", "D");
		System.out.println(lruCache);
		lruCache.put("e", "E");
		System.out.println(lruCache);
	}
	
	public static void main(String[] args) {
		//hashMapDemo();
		//immutableKeysDemo();
		lruCacheTest();
	}
}

class LRUCache<K, V> extends LinkedHashMap<K, V> {
	//private static final long serialVersionUID = 3059513327180672467L;
	private static final int MAX_ENTRIES = 3;
	
	public LRUCache(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}
	
	// Invoked by put and putAll after inserting a new entry into the map
	public boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size() > MAX_ENTRIES;
		// return false; // same as normal linked hash map
	}
}
