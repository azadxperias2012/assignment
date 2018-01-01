package com.neotechlabs.basics;

public class AutoBoxingExample {

    public static void main(String[] args) {
//        long start = System.nanoTime();
//        veryExpensive();
//        System.out.println("Elapsed Time: " + ((System.nanoTime() - start) / 1000000.0) + "ms");

        long newStart = System.nanoTime();
        leastExpensive();
        System.out.println("Elapsed Time: " + ((System.nanoTime() - newStart) / 1000000.0) + "ms");

        compareBoxPrimitives();
//        unbelievable();
    }

    static void veryExpensive() {
        System.out.println("\nInside veryExpensive");
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }
    static void leastExpensive() {
        System.out.println("\nInside leastExpensive");
        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }

    static void compareBoxPrimitives() {
        System.out.println("\nInside compareBoxPrimitives");
        Integer num1 = new Integer(0);
        Integer num2 = new Integer(0);
        System.out.println("(num1 == num2): " + (num1 == num2));

        // Solutions
        System.out.println("(num1.intValue() == num2.intValue()): " + (num1.intValue() == num2.intValue()));
        System.out.println("(num1.equals(num2)): " + (num1.equals(num2)));

        Integer num3 = new Integer(1);
        System.out.println("(num1 < num3): " + (num1 < num3)); // "<" does un-boxing first
    }

    static Integer unbelievableInt;

    static void unbelievable() {
        if(unbelievableInt == 0)
            System.out.println("Weird");
    }
}
