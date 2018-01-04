package com.neotechlabs.objectoriented.abstractclass;

public class ConcreteSubClass extends AbstractSubClass {
    @Override
    void test2() {
        System.out.println("test2");
    }

    @Override
    void test3() {
        System.out.println("test3");
    }

    public static void main(String[] args) {
        AbstractSuperClass superCls = new ConcreteSubClass();
        superCls.test1();
        superCls.test2();

        ((AbstractSubClass) superCls).test3();
    }
}
