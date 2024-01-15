package com.mmd.unmodifiablecollections;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder mohammedNotes = new StringBuilder("The best student");
        StringBuilder ahmedNotes    = new StringBuilder("The second best student");
        Student mohammed = new Student("Mohammed", mohammedNotes);
        Student ahmed    = new Student("Ahmed", ahmedNotes);

        List<Student> studentList = new ArrayList<>(List.of(mohammed, ahmed));
        studentList.forEach(System.out::println);

        List<Student> studentList1 = new ArrayList<>(studentList);// Passing a list to another list's constructor is a "Shallow Coppy"

        System.out.println("-".repeat(50));
        studentList1.forEach(System.out::println);


        System.out.println("-".repeat(50));
        studentList.forEach(System.out::println);


        List<Student> studentList2 = List.copyOf(studentList);
        List<Student> studentList3 = Collections.unmodifiableList(studentList);
        
        studentList.add(new Student("Karim", new StringBuilder("Karim notes")));

        mohammedNotes.replace(0, mohammedNotes.length(), "Mohammed note was changed");
        studentList.get(0).setStudentNotes(mohammedNotes);

        studentList.sort(Comparator.comparing(Student::getName));

        System.out.println("-".repeat(50));
        studentList.forEach(System.out::println);

        System.out.println("-".repeat(50));
        //studentList2.add(new Student("Karim", new StringBuilder("Karim note")));
        //studentList2.set(0, new Student("Karim", new StringBuilder("Karim note")));
        studentList2.forEach(System.out::println);

        System.out.println("-".repeat(50));
        studentList3.forEach(System.out::println);

    }
}
