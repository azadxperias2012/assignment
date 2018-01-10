package com.neotechlabs.jvm;

import java.lang.reflect.Method;

public class SimpleUnitTester {
	public static int execute(Class clazz) throws Exception {
		int failedCount = 0;

		// your code
		Reflection reflectionInstance = (Reflection) clazz.newInstance();
		for(Method m : clazz.getDeclaredMethods()) {
			String methodName = m.getName();
			if(methodName.toLowerCase().startsWith("test")) {
				Object result = m.invoke(reflectionInstance);
				
				if(result instanceof Boolean && (!(boolean) result)) {
					failedCount++;
				}
			}
		}

		return failedCount;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, Exception {
		System.out.println(execute(Class.forName("com.neotechlabs.jvm.Reflection")));
	}
}
