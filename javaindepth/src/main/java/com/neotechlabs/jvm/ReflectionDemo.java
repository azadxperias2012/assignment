package com.neotechlabs.jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {
	// Class object access demonstration + meta-information access
	private static void demoClassObjectAccess() {
		System.out.println("\nIn demoClassObjectAccess ...");
		
		// Using objRef.getClass()
		String[] strArray = {"a", "b", "c"};
		System.out.println("\nstrArray.getClass().getName(): " + strArray.getClass().getName());
		
		// Using Class.forName
		Class clazz = null;
		try {
			clazz = Class.forName("com.neotechlabs.jvm.User");
		} catch(ClassNotFoundException ex) {
			System.out.println("\nCan't find class ...");
		}
		
		if(clazz != null) {
			System.out.println("\nclazz.getName(): " + clazz.getName());
			System.out.println("clazz.isInterface(): " + clazz.isInterface());
			System.out.println("clazz.getInterfaces().length: " + clazz.getInterfaces().length);
			System.out.println("clazz.getSuperclass().getName(): " + clazz.getSuperclass().getName());
		}
		
		// Exception is thrown as Class.forName cannot be used on primitives.
		try {
			System.out.println("\nClass.forName(\"boolean\").getName(): " + Class.forName("boolean").getName());
		} catch(ClassNotFoundException ex) {
			System.out.println("\nClassNotFoundException due to Class.forName(\"boolean\").getName()");
		}
		
		// Using .class
		System.out.println("\nint.class.getName(): " + int.class.getName());
	}
	
	// Demonstrates instance creation & method invocation
	private static void demoCoreReflection(Class clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("\nIn demoCoreReflection ...");
		
		Object object = null;
		try {
			object = clazz.newInstance();	// MUST HAVE DEFAULT CONSTRUCTOR
		} catch (InstantiationException e) {
			System.out.println("Can't instantiate ...");
		} catch (IllegalAccessException e ) {
			System.out.println("Can't access ...");
		}
		System.out.println("object.getClass().getName(): " + object.getClass().getName());
		
		for (Method m : clazz.getDeclaredMethods()) {
			System.out.println("Method name: " + m.getName());
			if(m.getReturnType() == void.class) {
				System.out.println("Method's return type is void!!!");
			}
		}
		
		for (Constructor c : clazz.getDeclaredConstructors()) {
			System.out.println("Constructor: " + c.getName() + ", #parameters: " + c.getParameterCount());
		}
		
		// Fetch constructor & invoke it
		@SuppressWarnings("unchecked")
		Constructor<User> userConstructor = clazz.getDeclaredConstructor(int.class, String.class, String.class, String.class, String.class);
		User user = userConstructor.newInstance(1, "Azad", "azad123@something.com", "male", "user");
		
		// Fetch method & invoke it
		@SuppressWarnings("unchecked")
		Method m = clazz.getDeclaredMethod("saveWebLink", String.class, String.class);
		Object result = m.invoke(user, "http://www.google.com", "Google");
		System.out.println("\nResult of invoking saveWebLink: " + ((Boolean) result).booleanValue());
	}
	
	public static void main(String[] args) throws Exception {
		demoClassObjectAccess();
		demoCoreReflection(Class.forName("com.neotechlabs.jvm.User"));
	}
}
