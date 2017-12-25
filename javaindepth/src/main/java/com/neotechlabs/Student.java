package com.neotechlabs;

public class Student {

    int id;
    String name;
    String gender = "male";

    Student(String name) {
        this.name = name;
    }

    Student(int id, String name) {
        this(name);
        this.id = id;
    }

    boolean updateProfile(String name) {
        this.name = name;
        return true;
    }

}
