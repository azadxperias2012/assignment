package com.neotechlabs.basics;

import java.util.ArrayList;

public class StudentUtil {

    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        double[] studentsGPA = new double[studentIdList.length];
        int index = 0;
        for (char[] grades : studentsGrades) {
            double totalGradePoints = 0.0;
            for (char grade : grades) {
                switch (grade) {
                    case 'A':
                        totalGradePoints += 4.0;
                        break;
                    case 'B':
                        totalGradePoints += 3.0;
                        break;
                    case 'C':
                        totalGradePoints += 2.0;
                        break;
                    default:
                        totalGradePoints += 0.0;
                        break;
                }
            }
            studentsGPA[index++] = totalGradePoints / grades.length;
        }
        return studentsGPA;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if(lower > higher)
            return null;
        double[] studentsGPA = calculateGPA(studentIdList, studentsGrades);
        ArrayList<Integer> studentsList = new ArrayList<>();
        int i = 0;
        for (double studentGPA : studentsGPA) {
            if((lower <= studentGPA) && (studentGPA <= higher)) {
                studentsList.add(studentIdList[i]);
            }
            i++;
        }
        if(studentsList.size() > 0) {
            int[] studentsByGPA = new int[studentsList.size()];
            for (int j = 0; j < studentsList.size(); j++) {
                studentsByGPA[j] = studentsList.get(j);
            }
            return studentsByGPA;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] studentsByGPA = getStudentsByGPA(3.2, 3.5, new int[]{1001, 1002}, new char[][]{{'A', 'A', 'A', 'B'}, {'A', 'B', 'B'}});
        System.out.println(studentsByGPA[0]);
    }
}
