package com.neotechlabs.objectoriented.interfaces;

public class TestClient {
    public static int getVal() {
        return 42;
    }

    public static void main(String[] args) {
        C c = new X();
        //A a = new X();
        //a.foo();
        //a.bar();

        C clone = ((X) c).clone();
        if (clone != c) {
            System.out.println("Clone created!!");
        }
    }
}
