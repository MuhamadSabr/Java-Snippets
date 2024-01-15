package com.mmd.streamingstudents;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainTerminalOptional {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python masterclass", 50);
        Course jmc =  new Course("JMC",  "Java masterclass", 100);
        Course gjv =  new Course("GJV", "Creating games in Java");

        List<Student> students = IntStream.rangeClosed(1, 1000)
                .mapToObj(f-> Student.getRandomStudent(pymc, jmc))
//                .peek(System.out::println)
                .collect(Collectors.toList());

        OptionalDouble average =  students.stream()
                .mapToInt(Student::getAge)
                .average();//Again only exits on the primitive streams.
//        System.out.println(average.orElseGet(()->System.out.println("No average was calculated")));//Return type has to be OptionalDouble
        System.out.println(average.orElseGet(()-> 0.0));

        int minAge = 21;
        students.stream()
                .filter(ps-> ps.getAge()<=21)
                .findAny()
                .ifPresentOrElse( cs-> System.out.printf("student %s, from %s is %d%n",cs.getStudentId(), cs.getCountryCode(), cs.getAge()),
                        ()-> System.out.printf("No one under %d was found", minAge));

//        students.add(0, null);//There should be no null in your stream elements, otherwise even Optional will complain
        students.stream()
                .findFirst()
                .ifPresentOrElse(cs-> System.out.printf("student %s, from %s is %d%n",cs.getStudentId(), cs.getCountryCode(), cs.getAge()),
                        ()-> System.out.println("No student's found"));

        students.stream()
                .sorted(Comparator.comparing(Student::getStudentId))
                .findFirst()
                .ifPresentOrElse(cs-> System.out.printf("student %s, from %s is %d%n",cs.getStudentId(), cs.getCountryCode(), cs.getAge()),
                        ()-> System.out.println("No student's found"));//U can replace the above code with below code.

        students.stream()
                .filter(ps-> ps.getAge()<=minAge)
                .min(Comparator.comparing(Student::getStudentId))
                .ifPresentOrElse(cs-> System.out.printf("student %s, from %s is %d%n",cs.getStudentId(), cs.getCountryCode(), cs.getAge()),
                        ()-> System.out.println("No student's found less than : "+ minAge));

        students.stream()
                .filter(ps-> ps.getAge()<=minAge)
                .max(Comparator.comparing(Student::getStudentId))
                .ifPresentOrElse(cs-> System.out.printf("student %s, from %s is %d%n",cs.getStudentId(), cs.getCountryCode(), cs.getAge()),
                        ()-> System.out.println("No student's found less than or equal to : "+ minAge));

        students.stream()
                .filter(ps-> ps.getAge()<=17)
                .map(Student::getCountryCode)
                .distinct()
                .reduce((r, v)-> String.join(", ", r, v))
                .ifPresentOrElse(cs-> System.out.printf("Studetns who are less than %d, are from %s countries ", minAge, cs),
                        ()-> System.out.println("No student's found less than or equal to : "+ 17));


        students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .map(f-> String.join(", ", f))
                .filter(pc-> pc.contains("AU"))
                .findAny()
                .ifPresentOrElse(System.out::println,
                        ()-> System.out.println("Missing AU"));

    }
}
