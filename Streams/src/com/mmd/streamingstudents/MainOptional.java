package com.mmd.streamingstudents;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainOptional {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python masterclass", 50);
        Course jmc =  new Course("JMC",  "Java masterclass", 100);
        Course gjv =  new Course("GJV", "Creating games in Java");

        List<Student> students = IntStream.rangeClosed(1, 1000)
                .mapToObj(f-> Student.getRandomStudent(pymc, jmc))
                .collect(Collectors.toList());

        Optional<Student> o1 = getStudent(null, "first");
        System.out.printf("is empty? %s, is present %s: %n" ,o1.isEmpty(), o1.isPresent());
        System.out.println(o1);

        o1.ifPresentOrElse(System.out::println, ()-> System.out.println("value is not present"));
        // U can only use .get() if a value is present. So u must always check before using it.
        //Takes a consumer as 1st arg, n a Runnable as the 2nd arg. Lambda expression for the 2nd arg ()-> voidStatement
        //The function on Runnable functional interface is run(), takes no arg n has void as return type. Runnable emptyAction.

//        students.add(0, null);
        Optional<Student> o2 = getStudent(students, "first");
        System.out.printf("is empty? %s, is present %s: %n" ,o2.isEmpty(), o2.isPresent());
        System.out.println(o2);
        o2.ifPresent(System.out::println);// u can use .oreElse if exits returns it , if not returns the arg u pass to it.
        //ifPresent takes a consumer
//        students.add(students.size(), null);
        Optional<Student> o3 = getStudent(students, "la");
        System.out.printf("is empty? %s, is present %s: %n" ,o3.isEmpty(), o3.isPresent());
        System.out.println(o3);
//        System.out.println(o3.isPresent() ? o3.get() : null ); // U can replace this with the below expression
        System.out.println(o3.orElse(getDummyStudent(jmc)));// orElse() is a special kind of get. If o has a value returns it,
                                                    //else returns what u pass to is parameter.

        System.out.println(o3.orElseGet(()-> getDummyStudent(jmc)));//This is a better option than orElse especially if u call a method on orElse
        //Has a supplier as its argument.                          //Because it is evaluated whether or not it is need.
        //Here the lambda expression doesn't get invoked unless the o3 is empty. Again that is not the case of orElse which always is invoked.

        List<String> countryList = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .toList();

//        String countryString =String.valueOf( Optional.of(countryList)
//                .map(f-> String.join(", ", f))//Optional<java.lang.String> not String.
//        );
        System.out.println(countryList);
        Optional.of(countryList)
                .map(f-> String.join(", ", f))
                .filter(pc-> pc.contains("AU"))
                .ifPresentOrElse(System.out::println, ()-> System.out.println("Missing FR"));

    }

    public static Optional<Student> getStudent(List<Student> list, String type){
        if(list==null || list.size()==0){
//            return null;// Never return null that causes NullPointerException return Optional.empty();
            return Optional.empty();
        }
        else if(type.equals("first")){
//            return Optional.of(list.get(0)); // If it is uncertain whether null will be returned or not use the ofNullable(T value)
            return Optional.ofNullable(list.get(0)); //to avoid facing NullPointerException
        }
        else if(type.equals("last")){
//            return Optional.of(list.get(list.size()-1));// You cannot pass null to Optional.of(T value).
            return Optional.ofNullable(list.get(list.size()-1));
        }
        else {
//            return Optional.of(list.get(new Random().nextInt(list.size()-1)));
            return Optional.ofNullable(list.get(new Random().nextInt(list.size()-1)));
        }
    }

    public static Student getDummyStudent(Course course){
        System.out.println("Dummy student invoked");
        return Student.getRandomStudent(course);
    }
}
