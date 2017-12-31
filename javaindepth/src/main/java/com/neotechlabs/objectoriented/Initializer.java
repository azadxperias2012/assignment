package com.neotechlabs.objectoriented;

public class Initializer {

    private static int value;

    static {
        System.out.println("Inside Initializer: Static Initializer value: " + value);
        value = 100;
        System.out.println("Inside Initializer: Static Initializer value: " + value);
    }

    public Initializer() {
        System.out.println("Inside Initializer: No args constructor");
    }

    public Initializer(int id) {
        System.out.println("Inside Initializer: Constructor with a parameter");
    }

    {
        System.out.println("Inside Initializer: Instance Initializer");
    }

    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        initializer = new Initializer(1);
    }
}
