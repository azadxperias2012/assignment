package com.neotechlabs.objectoriented;

public class Student {

    private static int studentCount;

    private static int idInitializer = 1000;
    private final int id;
    private String name;
    private String gender = "male";

    public static int getStudentCount() {
        return studentCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name, String gender) {
        this.name = name;
        this.gender = gender;

        id = ++idInitializer;
        studentCount++;
        System.out.println("ID of " + name + ": " + id);
    }

    public static void main(String[] args) {
        Student student1 = new Student("joan", "male");
        Student student2 = new Student("raj", "male");
        Student student3 = new Student("anita", "female");

        System.out.println("Name of student1: " + student1.getName());
        System.out.println("Name of student2: " + student2.getName());
        System.out.println("Name of student3: " + student3.getName());

        student1.setName("john");
        System.out.println("Updated name of student1: " + student1.getName());

        System.out.println("# students created so far: " + Student.getStudentCount());
    }
}
