package com.mmd;

import com.mmd.model.LPAStudent;
import com.mmd.model.Student;
import com.mmd.model.YearStartedStudentCompare;
import com.mmd.util.QueryList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        //List<Student> students = new ArrayList<LPAStudent>(); This is an error.
        for(int i = 0 ; i < studentCount ; i++){
            students.add(new Student());
        }
        students.add(new LPAStudent());
        //printList(students);
        printMoreList(students);
        List<LPAStudent> LPAstudents = new ArrayList<>();
        //List<Student> students = new ArrayList<LPAStudent>(); This is an error.
        for(int i = 0 ; i < studentCount ; i++){
            LPAstudents.add(new LPAStudent());
        }
        //printList(LPAstudents);
        printMoreList(LPAstudents);

        List<String> list = new ArrayList<>(List.of("Mohammed", "Ahmed", "Karim"));
        List<Integer> iList = new ArrayList<>(List.of(1,2,4,6));
        printList(list);
        printList(iList);

//        var queryList = new QueryList<>(LPAstudents); // We don't specify type argument on either sides, we pass the list of lpaStudents and that is enough to infer the type and the type is going to be LPAStudent.
//        var matches = queryList.getMatches("Course", "java");
//        System.out.println("The first match using the instance method:");
//        printMoreList(matches);
//        System.out.println("The second match using the static generic method:");
        var matches1 = QueryList.getMatches(LPAstudents, "percentComplete", "50");
        if(!matches1.isEmpty()) {
            printMoreList(matches1);
        }
        System.out.println("-".repeat(50));
        QueryList<LPAStudent> queryList = new QueryList<>();
        for(int i = 0; i<25; i++){
            queryList.add(new LPAStudent());
        }
        printMoreList(queryList);
        queryList.sort(Comparator.reverseOrder());
        var comparator = new YearStartedStudentCompare();
        queryList.sort(comparator);
        System.out.println("-".repeat(30));
        printMoreList(queryList);
        System.out.println("Matches:");
        var queryListMatches = queryList.getMatches("percentComplete", "50")
                .getMatches("course", "java"); //getMatches return a QueryList so u can again call another on the result of another
        queryListMatches.sort(new YearStartedStudentCompare().reversed());
        //queryListMatches.sort(null); // This sorts it in natural order.
        printMoreList(queryListMatches);

    }


    public  static <T extends Student> void printMoreList(List<? extends Student> studentList){// This means this method will take a list of any type.
        Student last = studentList.get(studentList.size()-1); // With wildcard u can get value but cannot set value.
        for(var student : studentList){
            System.out.println(student ) ;// I have the toString overridden.
            if(student instanceof LPAStudent lpaStudent){
                //System.out.println(lpaStudent.getPercentComplete());
            }
        }
        //System.out.println(last+" Hffdfdfdf");
    }


    public static void printList(List<?> list){
        for(var i : list){
            if(i instanceof String sList) {
                System.out.println(sList.toUpperCase());
            }
            else if(i instanceof Integer iList){
                System.out.println(iList.floatValue());
            }
        }
    }


//    public static void printList(List<String> sList){
//        for(var i : sList){
//            System.out.println(i.toUpperCase());
//        }
//    }
//    public static void printList(List<Integer> sList){
//        for(var i : sList){
//            System.out.println(i.floatValue());
//        }
//    }


//      public  static <T extends Student> void printList(List<T> studentList){// This means this method will take a list of any type.
//        for(var student : studentList){
//            System.out.println(student.getYearStarted() ) ;// I have the toString overridden.
//            if(student instanceof LPAStudent lpaStudent){
//                System.out.println(lpaStudent.getPercentComplete());
//            }
//        }
//        System.out.println();
//    }

}