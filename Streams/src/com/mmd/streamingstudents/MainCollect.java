package com.mmd.streamingstudents;

import com.sun.source.tree.Tree;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python masterclass");
        Course jmc =  new Course("JMC",  "Java masterclass");

        List<Student> students = Stream.generate(()-> Student.getRandomStudent(jmc, pymc)).limit(1000).toList();

        Set<Student> australianStudents = students.stream()
                .filter(sp-> sp.getCountryCode().equals("AU"))
                .collect(Collectors.toSet());// Collecting to a set. using collect(Collectors.toSet()) which returns a HashSet.
        System.out.println("Number of Australian students : " + australianStudents.size());

        Set<Student> underThirtyStudents = students.stream()
                .filter(sp-> sp.getAgeEnrolled()<30)
                .collect(Collectors.toSet());
        System.out.println("Number of under thirty students : " + underThirtyStudents.size());

        Set<Student> youngAussies1 = new TreeSet<>(Comparator.comparing(Student::getStudentId));
        youngAussies1.addAll(australianStudents);
        youngAussies1.retainAll(underThirtyStudents);
        youngAussies1.forEach(s-> System.out.print(s.getStudentId() + " "));
        System.out.println();

        Set<Student> youngAussies2 = students.stream()
                .filter(sp-> sp.getAgeEnrolled()<30)
                .filter(sp-> sp.getCountryCode().equals("AU"))
                .sorted(Comparator.comparing(Student::getStudentId))//toSet does not on sorted order.
                        .collect(()-> new TreeSet<>(Comparator.comparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll);
//                .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);//But the TreeSet must implement Comparable or pass Comparator to
                        //Supplier      Accumulator     combiner                                            its constructor
        youngAussies2.forEach(s-> System.out.print(s.getStudentId() + " "));
        System.out.println();

        String countryString = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .reduce("", (r, v)-> r + " " + v);
                            //identity          BinaryOperator. r is currently accumulated v is the new value.
        System.out.println(countryString);
    }
}
