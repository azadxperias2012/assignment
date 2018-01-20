package com.neotechlabs;

public class Student {

    int id;
    String name;
    String gender = "male";

    public Student(String name) {
        this.name = name;
    }

    public Student(int id, String name) {
        this(name);
        this.id = id;
    }

    boolean updateProfile(String name) {
        this.name = name;
        return true;
    }

	public void setName(String string) {
		this.name = name;
	}

}
