package com.mmd.writing;

import com.mmd.student.Course;
import com.mmd.student.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Challenge {
    public static void main(String[] args) {

        Course jmc = new Course("JMC", "Java Masterclass");
        Course pymc= new Course("PYMC", "Python Masterclass");

        var x = Stream.iterate(0, ps-> ps<9, u-> u+= 1)
                .map(fs-> Student.getRandomStudent(jmc, pymc))
                .map(Student::toJSON)
                .collect(Collectors.joining(", ", "[", "]"));

        Path path = Path.of("student.json");
        try {
            Files.writeString(path, x);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
