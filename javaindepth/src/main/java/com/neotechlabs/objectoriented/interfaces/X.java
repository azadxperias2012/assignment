package com.neotechlabs.objectoriented.interfaces;

public class X extends AbstractA implements A, B, C, Cloneable {
    @Override
    public void foo() {
        System.out.println("X: foo");
        System.out.println("A.VAL: " + A.VAL);
        System.out.println("B.VAL: " + B.VAL);
    }

    @Override
    public void foobar() {
        System.out.println("X: foobar");
    }

    public C clone() {
        try {
            return (C) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public void go() {
        C.super.go();
        //System.out.println("X: go");
    }*/

    public void inheritanceTest() {
        go(); // can be accessed via default methods
        //staticMethod(); // can't be inherited
    }
}
