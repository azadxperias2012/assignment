package com.neotechlabs.singleton;

public class Singleton {

    private static volatile Singleton instance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        Singleton result = instance;
        if (result == null) {
            synchronized (Singleton.class) {
                if (result == null) {
                    result = instance = new Singleton();
                }
            }
        }
        return result;
    }
}
