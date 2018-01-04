package com.neotechlabs.objectoriented.abstractclass;

abstract class AbstractSubClass extends AbstractSuperClass {
    @Override
    void test1() {
        System.out.println("test1");
    }

    abstract void test3();
}
