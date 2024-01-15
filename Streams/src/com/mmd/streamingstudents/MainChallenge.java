package com.mmd.streamingstudents;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainChallenge {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python masterclass", 50);
        Course jmc =  new Course("JMC",  "Java masterclass", 100);
        Course gjv =  new Course("GJV", "Creating games in Java");

//        List<Student> students = Stream.iterate(0, n-> n<5000, n-> ++n).
//        map(f-> Student.getRandomStudent(pymc, jmc))
//                .peek(System.out::println)
//                .toList();
        List<Student> students = IntStream.rangeClosed(1, 5000)
                .mapToObj(f-> Student.getRandomStudent(pymc, jmc))
//                .peek(System.out::println)
                .toList();

        double percentageCompleteJava = students.stream()
                .mapToDouble(fs-> fs.getPercentComplete("JMC"))
                .reduce(0.0, Double::sum) / students.size();
        System.out.printf("Average percentage complete for Java master class:  %.2f%n", percentageCompleteJava );

        int topPercent = (int)(1.25*percentageCompleteJava);
        List<Student> threeQuartersDoneStudents = students.stream()
                .filter(sp-> sp.getPercentComplete("JMC")>= (1.25*percentageCompleteJava))
                .sorted(Comparator.comparing(Student::getYearEnrolled))
                .limit(10)
                .peek(System.out::println)
                .collect(Collectors.toList());
        threeQuartersDoneStudents.forEach( cs-> cs.addCourse(gjv));
        threeQuartersDoneStudents.forEach(System.out::println);


        students.stream()
                .filter(sp-> sp.getPercentComplete("JMC")>= (1.25*percentageCompleteJava))
                .sorted(Comparator.comparing(Student::getYearEnrolled))
                .limit(10)
//                .toList()
//                .collect(Collectors.toList())
                .collect(()-> new TreeSet<Student>(Comparator.comparing(Student::getStudentId)),
                        TreeSet::add,
                        TreeSet::addAll)
                .forEach( cs-> {
                    cs.addCourse(gjv);
                    System.out.println(cs);
                });

    }
}
