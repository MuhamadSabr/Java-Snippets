package com.mmd.streamingstudents;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python masterclass");
        Course jmc =  new Course("JMC",  "Java masterclass");
//        Student mohammed = new Student("KUR", 2020, 24, "Male", true,
//                pymc, jmc);
//        System.out.println(mohammed);
//
//        mohammed.watchLecture("JMC", 1, 1, 2020);
//        mohammed.watchLecture("PYMC", 1, 1, 2020);
//        System.out.println(mohammed);

        Student[] studentsArray = new Student[1000];
        Arrays.setAll(studentsArray, i-> Student.getRandomStudent(jmc, pymc));

        for(String gender : List.of("U", "M", "F")) {
            int tempGenderCounter = (int) Arrays.stream(studentsArray).filter(ps -> ps.getGender().equals(gender)).count();
            System.out.println((gender.equals("U") ? "Unknown" : gender.equals("M") ? "Male" : "Female")
            + " gender number is : " + tempGenderCounter);
        }

     List<Predicate<Student>> ageRangeList = new ArrayList<>(List.of(
             (sp) -> sp.getAge() <30,
             (sp) -> sp.getAge()>=30 && sp.getAge()<=60)
     );

     int total = 0;
     for(int i =0 ;i< ageRangeList.size(); i++){
         long range = Arrays.stream(studentsArray).filter(ageRangeList.get(i)).count();
         total+= range;
         System.out.println(ageRangeList.get(i).toString() +" : " + range);
     }
     System.out.println("Above 60 student number : " + (studentsArray.length-total));

     IntSummaryStatistics studentAgeIntSummaryStatistics = Arrays.stream(studentsArray)
                                        .mapToInt(Student::getAge)
                                        .summaryStatistics();
     System.out.println(studentAgeIntSummaryStatistics);

     var countryList = Arrays.stream(studentsArray)
                .filter(sp-> sp.getYearsSinceInvolved()>=7)
                .limit(5)
//                .toList();// Provides an unmodifiable list.
//                .toArray(); //Returns an array of Object[]. U need to cas to Student if u want to use some attribute of it.
//                .toArray(size-> new Student[size]);//Overloaded function to return the type of array you want.
                .toArray(Student[]::new);// takes 1 arg which is the size of the new array type you need.
        System.out.println(Arrays.toString(countryList));
//     System.out.println( ((Student[]) countryList)[0].getAge() );//Got error cannot cast to class streamingStudents.Student.
        System.out.println();
        var longTimeLearners = Arrays.stream(studentsArray)
                .filter(sp-> sp.getYearsSinceInvolved()>=7)
                .limit(5)
                        .collect(Collectors.toList());//Returns a mutable list. Not like toList that returns an unmodifiable list.
        Collections.shuffle(longTimeLearners);
        System.out.println(longTimeLearners);

     boolean hasStudentsEnrolledOver7Years = Arrays.stream(studentsArray)
             .peek(System.out::println)
             .anyMatch(ps-> ps.getYearsSinceInvolved()>7);
     System.out.println("Is there any student that has enrolled for over 7 years? " + hasStudentsEnrolledOver7Years);



    }

    public static boolean range(Student s){
        return s.getAge() < 30 || (s.getAge() >= 30 && s.getAge() <= 60) || s.getAge() > 60;
    }
}
