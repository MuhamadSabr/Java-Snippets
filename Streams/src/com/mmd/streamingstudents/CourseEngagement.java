package com.mmd.streamingstudents;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class CourseEngagement {
    private final Course course;
    private final LocalDate engagementDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate engagementDate, String engagementType) {
        this.course = new Course(course.courseCode(), course.title(), course.lectureCount());
        this.engagementDate = this.lastActivityDate =  engagementDate;
        this.engagementType = engagementType;
    }

    public String getCourseCode() {
        return course.courseCode();
    }

    public int getEngagementYear() {
        return engagementDate.getYear();
    }

    public String getEngagementType() {
        return engagementType;
    }

    public int getLastLecture() {
        return lastLecture;
    }

    public int getLastActivityYear() {
        return lastActivityDate.getYear();
    }

    public String getLastActivityMonth(){
        return "%tb".formatted(lastActivityDate.getMonth()); //tb is the format for 3 letter abbr of month.
    }

    public double getPercentComplete(){
        return lastLecture * 100.00 / course.lectureCount();
    }

    public int getMonthsSinceActive(){
        return (int) Period.between(lastActivityDate, LocalDate.now()).toTotalMonths();
    }

    void watchLecture(int lectureNumber, LocalDate currentDate){
        lastLecture = Math.max(lectureNumber, lastLecture);
        lastActivityDate = currentDate;
        engagementType = "Lecture " + lastLecture;
    }

    @Override
    public String toString(){
        return "%s: %s %d %s [%d]".formatted(course.courseCode(), getLastActivityMonth(), getLastActivityYear(), engagementType,
                getMonthsSinceActive());
    }

}
