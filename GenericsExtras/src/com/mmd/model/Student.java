package com.mmd.model;

import com.mmd.util.QueryItem;

import java.util.Comparator;
import java.util.Random;

public class Student implements QueryItem, Comparable<Student> {
    private String name;
    private String course;
    private int yearStarted;
    private int student_id;
    private static int last_id=1000;
    protected static Random random = new Random();

    private static String[] firstNames = {"Mohammed", "Ahmed", "Kamal", "Shamal", "Jabar"};
    private static String[] courses    = {"C++", "Python", "Java", "Database"};

    public Student(){
        student_id = last_id++;
        int lastNameIndex = random.nextInt(65, 91);
        name = firstNames[random.nextInt(5)] + " " + (char) lastNameIndex;
        course = courses[random.nextInt(3)];
        yearStarted = random.nextInt(2020, 2023);
    }

    @Override
    public String toString(){
        return "%-15d %-15s %-15s %d".formatted(student_id, name, course, yearStarted); // - means left justified. for right justified
                                                                        // Just type number of characters alone.
    }
    public int getYearStarted() {
        return yearStarted;
    }
    public String getStudent_name(){
        return name;
    }


    @Override
    public boolean matchFieldValue(String fieldName, String fieldValue) {
        String fName = fieldName.toUpperCase();
        if(fName.equalsIgnoreCase("yearstarted")){
            try{
                Integer.parseInt(fieldValue);
            }catch (Exception e){
                System.out.printf(e.getClass().toString());
                return false;
            }
        }
        return switch(fName){
            case "NAME" -> name.equalsIgnoreCase(fieldValue);
            case "COURSE" -> course.equalsIgnoreCase(fieldValue);
            case "YEARSTARTED" -> yearStarted == (Integer.parseInt(fieldValue));
            default -> false;
        };
    }

    @Override
    public int compareTo(Student o) {
//        return Integer.compare(student_id, o.student_id);
//        return Integer.valueOf(student_id).compareTo(o.student_id);
        return Integer.compare(student_id,o.student_id);
    }
}

