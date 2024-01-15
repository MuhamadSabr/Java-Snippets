package com.mmd.streamingstudents;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class MainMapping {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python masterclass", 50);
        Course jmc =  new Course("JMC",  "Java masterclass", 100);
        Course gjv =  new Course("GJV", "Creating games in Java");

        var students = IntStream.rangeClosed(1, 1000)
                .mapToObj(f-> Student.getRandomStudent(pymc, jmc))
                .toList();

        var mappedStudents = students.stream()// groupingBy collector is used to group elements from a stream
                .collect(Collectors.groupingBy(Student::getCountryCode));//into a map where keys represent groups, n values represent
        // lists of elements within those groups. Collectors.groupingBy(classifier) this is this version. groupingBy(classifier, toList() )
        mappedStudents.forEach((k, v)-> System.out.println(k + " " + v.size()));
        System.out.println("-".repeat(20));

        students.stream()
                .collect(Collectors.groupingBy(Student::getAge))
                        .forEach( (age, studentsList) ->
                                System.out.println("Age " + age + " " + studentsList.stream()
                                        .map(Student::getCountryCode)
                                        .distinct()
                                        .collect(Collectors.joining())//If u don't specify delimiter then there will be no delimiter
                                )
                                );
        System.out.println("-".repeat(20));

        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getAge,//We group students by their age. - The downstream collector Collectors.counting() is applied
                        Collectors.counting()))//to each group to count n of people of each age. The result is Map<Integer, Long>.
                .forEach((age, count)-> System.out.print(age + " " + count + ", "));
        System.out.println();
        System.out.println("-".repeat(20));

        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getAge,
                        TreeMap::new,//Use a TreeMap as a result container
                        Collectors.counting()))
                .forEach((age, count)-> System.out.print(age + " " + count + ", "));
        System.out.println();
        System.out.println("-".repeat(20));


        students.stream()
                .collect(Collectors.partitioningBy(ps-> ps.getAge()<=30))// Result is a Map<Boolean, List<Student>>
                .get(false)
                .forEach(student -> System.out.print(student.getStudentId()+ " "));
        System.out.println();
        System.out.println("-".repeat(20));


        students.stream()
                .collect(Collectors.partitioningBy(
                        ps-> ps.getAge()<=30,
                        Collectors.counting()
                ))
          .forEach((result, occurrence)-> System.out.print((result? "30 or younger than 30: " : "older than 30: ")+occurrence + ", "));
        System.out.println();
        System.out.println("-".repeat(20));

        Map<Boolean, Long> countByPartition = students.stream()
                .collect(Collectors.partitioningBy(
                        ps-> ps.getAge()<=30,
                        Collectors.counting()
                ));
        System.out.println("Number of students younger than or 30 is: " + countByPartition.get(true));
        System.out.println("Number of students older   than    30 is: " + countByPartition.get(false));
        System.out.println();
        System.out.println("-".repeat(20));


        var experienced= students.stream()
                .collect(Collectors.partitioningBy(Student::hasProgrammingExperience));
                System.out.print("Number of students with programming experience: ");
        System.out.println();
        System.out.println("-".repeat(20));





        var multi = students.stream()
                .collect(groupingBy(
                        Student::getCountryCode,
                        groupingBy(Student::getGender)
                ));
        multi.forEach((keyCountryCode, countryCodeValue)-> {
            System.out.println(keyCountryCode + ":");
            countryCodeValue.forEach((genderKey, stdList)->
                    System.out.println("Gender: " + genderKey+ " " + stdList.stream().map(std-> std.getStudentId()+"")
                            .collect(joining(", "))));
        });

        System.out.println();
        System.out.println("-".repeat(20));


        var multi2 = students.stream()
                .collect(groupingBy(
                        Student::getCountryCode,
                        groupingBy(Student::getAge, Collectors.counting())
                ));

        multi2.forEach((countryCode, countryValue)-> {
            System.out.println("Country: " + countryCode);
            countryValue.forEach((age, ageOccur)-> System.out.println("Age: " + age +": " + ageOccur));
        });

//
//        System.out.println( experienced.entrySet().stream()
//                .mapToInt(fs-> fs.getValue().size())
//                .sum()
//        );
//
//        System.out.println( experienced.values().stream()
////                .mapToInt(List::size)
//                .mapToInt(fs-> fs.size())
//                .sum()
//        );
        System.out.println("-".repeat(20));

        List<List<Integer>> listOfLists = new ArrayList<>(List.of(
                List.of(1,2,3),
                List.of(4,5,6),
                List.of(7,8,9)
        ));

        List<Integer> list = listOfLists.stream()
                .flatMap(fl-> fl.stream())
                .collect(toList());
        System.out.println(list);

        Stream<Stream<Integer>> nestedStream = Stream.of(// U don't instantiate Stream
                Stream.of(1,2,3),
                Stream.of(4,5,6),
                Stream.of(7,8,9)
        );

        Stream<Integer> stream = listOfLists.stream()
                .flatMap(fl-> fl.stream());
        System.out.println("Stream : " + stream.collect(toList()));



        var exp = experienced.values().stream()
                        .flatMap(fl-> fl.stream())
                                .count();
        System.out.println(exp);

        var exp2 = multi.values().stream()
                .flatMap(fl-> fl.values().stream())
                .flatMap(fl-> fl.stream())
                .count();
        System.out.println(exp2);


        var ma = students.stream()//If there are duplicate keys n u don't provide the Merge Function(arg 3)
                .collect(toMap(                     //U will face java.lang.IllegalStateException
                        Student::getCountryCode,//Lastly u can specify a supplier as the 4th arg indicating the result container
                        Student::getAge
                        ,(existingValue, newValue) -> existingValue+newValue//If duplicate key was found then add the new value
                ));             //of the duplicate key to the already there value.
        ma.forEach((k, v) -> System.out.println(k+ ": " + v));




    }
}
