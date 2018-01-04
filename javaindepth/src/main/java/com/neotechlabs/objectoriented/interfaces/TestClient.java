package com.neotechlabs.objectoriented.interfaces;

public class TestClient {
    public static int getVal() {
        return 42;
    }

    public static void main(String[] args) {
        A a = new X();
        a.foo();
        a.bar();
    }
}
