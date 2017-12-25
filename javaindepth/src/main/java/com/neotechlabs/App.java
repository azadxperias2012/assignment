package com.neotechlabs;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        int[] studentIds = new int[] {1001, 1002, 1003};

        Student student1 = new Student(studentIds[0], "Azaad");
        student1.gender = "male";

        Student student2 = new Student(studentIds[1], "Mubeena");
        student1.gender = "female";

        Student student3 = new Student(studentIds[2], "Ruhan");
        student1.gender = "male";

        System.out.println("Name of student1: " + student1.name);
        System.out.println("Name of student2: " + student2.name);
        System.out.println("Name of student3: " + student3.name);

        student1.updateProfile("Azad");
        System.out.println("Updated name of student1: " + student1.name);
    }
}
