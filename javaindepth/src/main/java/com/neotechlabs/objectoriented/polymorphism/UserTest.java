package com.neotechlabs.objectoriented.polymorphism;

public class UserTest {

    public void printUserType(User u) {
        u.printUserType();
    }

    public void approveReview(Staff s) {
        //s.approveReview();
        //((Editor) s).approveReview(); // possible class cast exception
        if(s instanceof Editor) {
            ((Editor) s).approveReview();
        } else {
            System.out.println("Invalid Staff object passed.");
        }
    }

    public static void main(String[] args) {
        // Part 1
        User user = new User();
        User staff = new Staff();
        User editor = new Editor();

        staff.postAReview("");
        ((Staff)staff).printId();

        //UserTest ut = new UserTest();
        //ut.printUserType(user);
        //ut.printUserType(staff);
        //ut.printUserType(editor);

        // Part 2
        //editor.approveReview();
        //editor.postAReview();
        //editor.saveWebLink();

        // Casting & instanceof demo
        //ut.approveReview(new Staff());
        //ut.approveReview(new Editor());
    }

}
