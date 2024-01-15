package com.mmd.Maps.LinkedHashMapNTreeMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record Course(String courseId, String name, String subject){}

record Purchase(String courseId, int studentId, double price, int year, int dayOfYear){
    public LocalDate purchaseDate(){
        return LocalDate.ofYearDay(year, dayOfYear);
    }
}
public class Student {

    private static int lastId=1;
    private int id;
    private String name;
    private List<Course> courseList;

    public Student(String name, List<Course> courseList) {
        id = lastId++;
        this.name = name;
        this.courseList=  new ArrayList<>();
        this.courseList.addAll(courseList);
    }

    public Student(String name, Course course){
        this(name, List.of(course));
        //this(name, new ArrayList<>(List.of(course)));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean addCourse(Course course){

        return !courseList.contains(course) && courseList.add(course);
    }

    @Override
    public String toString() {
        String[] courseNames = new String[courseList.size()];
        Arrays.setAll(courseNames, i->  courseList.get(i).name());
        return "[%d] : %s".formatted(id, String.join(", ", courseNames));
    }
}
