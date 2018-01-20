package com.neotechlabs.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

	public static void main(String[] args) {
		//hashMapDemo();
		immutableKeysDemo();
	}
}
