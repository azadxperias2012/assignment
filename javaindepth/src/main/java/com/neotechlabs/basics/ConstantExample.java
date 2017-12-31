package com.neotechlabs.basics;

public class ConstantExample {
    public static void main(String[] args) {
        switchExample();
    }

    static void switchExample() {
        System.out.println("\nInside switchExample...");
        final byte monthFeb = 2;
        byte month = 3;
        switch (month) {
            case 1:
                System.out.println("January");
                break;
            case monthFeb:
                System.out.println("February");
                break;
            case 3:
                System.out.println("March");
                break;
            default:
                System.out.println("April");
                break;
        }
    }
}
