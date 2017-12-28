package com.neotechlabs.singleton;

public class SingletonTest {

    public static void main(String[] args) {
        System.out.println("SINGLETON DESIGN PATTERN EXAMPLE");

        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance1);
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance2);
    }

}
