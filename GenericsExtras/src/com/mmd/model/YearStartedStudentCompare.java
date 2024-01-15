package com.mmd.model;

import java.util.Comparator;

public class YearStartedStudentCompare implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return (o1.getYearStarted() + o1.getStudent_name()).compareTo(o2.getYearStarted() + o2.getStudent_name());
    }
}
