package com.neotechlabs.objectoriented;

public class Student {

    private static int studentCount;

    private int id;
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

    public Student(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;

        studentCount++;
    }

    public static void main(String[] args) {
        int[] studentIds = {1001, 1002, 1003};

        Student student1 = new Student(studentIds[0], "joan", "male");
        Student student2 = new Student(studentIds[1], "raj", "male");
        Student student3 = new Student(studentIds[2], "anita", "female");

        System.out.println("Name of student1: " + student1.getName());
        System.out.println("Name of student2: " + student2.getName());
        System.out.println("Name of student3: " + student3.getName());

        student1.setName("john");
        System.out.println("Updated name of student1: " + student1.getName());

        System.out.println("# students created so far: " + Student.getStudentCount());
    }
}
