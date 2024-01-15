package com.mmd.Changing.Lambdas;

import java.text.CompactNumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        String name = "Mohammed";
        Function<String, String> uCase = String::toUpperCase;
        System.out.println(uCase.apply(name));

        Function<String, String> lastName = s-> String.join(" ", s, "Othman");
        Function<String, String> uCaseLastName = uCase.andThen(lastName);
        System.out.println(uCaseLastName.apply(name));

        uCaseLastName = uCase.compose(lastName);
        System.out.println(uCaseLastName.apply(name));// Works similar to .andThen(). But compose reverses it order of execution
                                //compose is available only to lambda expressions that target either a Function or UnaryOperator
                                //Other Function interfaces that take primitive does not support it, ie BiFunction, BinaryOperator
        Function<String, String[]> f0 = uCase
                .andThen(s-> String.join(" ", s, "Karim"))//Not all Functions must return the same type.
                .andThen(s-> s.split(" ")); //Only the last lambda expression must match the R in the declaration
        System.out.println(Arrays.toString(f0.apply("Didar")));//.andThen does not work for interfaces that have primitive args.

        Function<String, String> f1 = uCase
                .andThen(s-> String.join(" ", s, "Karim"))
                .andThen(s-> s.split(" "))
                .andThen(s-> s[1].toUpperCase().concat(", ").concat(s[0]));
        System.out.println( f1.apply("Didar")  );

        Function<String, Integer> f2 = uCase
                .andThen(s-> String.join(" ", s, "Karim"))
                .andThen(s-> s.split(" "))
                .andThen(s-> String.join(", ", s))
                .andThen(String::length);
        System.out.println( f2.apply("Didar")  );

        String[] names = {"Ann", "Bob", "Carl"};
        //Consumer<String> s0 = s-> s.charAt(0);//Remember void Consumer . So this value is lost.
        Consumer<String> s0 = s-> System.out.print(s.charAt(0));
        Consumer<String> s1 = System.out::println;
        Arrays.asList(names).forEach(s0
                .andThen(s-> System.out.print(" - "))
                .andThen(s1));

        Predicate<String> p0 = s-> s.equals("Mohammed");
        Predicate<String> p1 = s-> s.equalsIgnoreCase("mohammed");
        Predicate<String> p2 = s-> s.startsWith("M");
        Predicate<String> p3 = s-> s.endsWith("i");

        Predicate<String> p0p1 = p0.and(p1);
        System.out.println("p0andp1 : " + p0p1.test(name));

        Predicate<String> p0p2p3 = p0.or(p2).and(p3);
        System.out.println("p0p2p3: " + p0p2p3.test(name));

        Predicate<String> p0p1p2p3 = p0.or(p2).and(p3).or(p1);
        System.out.println("p0p1p2p3: " + p0p1p2p3.test(name));

        System.out.println("negate p0p1p2p3: " + p0p1p2p3.negate().test(name));

        record Person(String firstName, String lastName){}
        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("Peter", "Pan"),
                new Person("Peter", "PumpkinEater"),
                new Person("Minnie", "Mouse"),
                new Person("Micky", "Mouse")
        ));
        //personList.sort((o1,o2)-> o1.lastName.compareTo(o2.lastName));
        personList.sort(Comparator.comparing(o -> o.lastName)); // U cannot chain methods to this statement type
        personList.sort(Comparator.comparing(Person::lastName));// comparing is a static method on Comparator
        personList.forEach(System.out::println);//lastName is the getter method for firstName variable on the record.
        System.out.println("-".repeat(30));

        personList.sort(Comparator.comparing(Person::firstName)
                .thenComparing(Person::firstName));
        personList.forEach(System.out::println);

        personList.sort(Comparator.comparing(Person::firstName)
                .thenComparing(Person::firstName)
                .reversed());
        System.out.println("-".repeat(30));

        personList.forEach(System.out::println);

    }
}
