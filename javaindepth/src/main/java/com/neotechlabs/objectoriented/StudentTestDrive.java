package com.neotechlabs.objectoriented;

public class StudentTestDrive {
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
