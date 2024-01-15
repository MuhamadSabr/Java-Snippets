package com.mmd.student;

public record Course(String courseCode, String title) {

    public int getLectureCount() {
        return 15;
    }

    @Override
    public String toString() {
        return "%s %s".formatted(courseCode, title);
    }
    public String toJSON() {
        return "{" +
                "\"courseCode\": " + '"' + courseCode + '"' + '\n' +
                ", \"title\": " + '"' + title + '"' + '\n' +
                '}' + '\n';
    }
}
