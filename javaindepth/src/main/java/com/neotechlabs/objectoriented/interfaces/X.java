package com.neotechlabs.objectoriented.interfaces;

public class X extends AbstractA implements A, B {
    @Override
    public void foo() {
        System.out.println("X: foo");
        System.out.println("A.VAL: " + A.VAL);
        System.out.println("B.VAL: " + B.VAL);
    }
}
