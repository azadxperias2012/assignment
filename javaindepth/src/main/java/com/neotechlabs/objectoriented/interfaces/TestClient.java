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

        //C clone = ((X) c).clone();
        //if (clone != c) {
            //System.out.println("Clone created!!");
        //}
        //c.go();

        new TestClient().lambdaTest(() -> System.out.println("Java In-Depth"));
    }

    public void lambdaTest(FunctionalInterface fi) {
        fi.test();
    }
}
