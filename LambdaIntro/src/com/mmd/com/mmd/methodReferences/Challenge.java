package com.mmd.com.mmd.methodReferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.UnaryOperator;

record Person(String first){
    public String last(String lastName){
        return first + " " + lastName;
    }
}

public class Challenge {
    public static void main(String[] args) {
        Person Mohammed = new Person("Mohammed");
        String[] names = {"Mohammed", "Bob", "ahmed", "Karim", "anna", "Jawhar", "Rafiq","shafiq"};
        List<Function<String, String>> functionList = new ArrayList<>();
        functionList.add(String::toUpperCase);
        functionList.add(s-> s.transform(Challenge::generateMiddleName));
        functionList.add(s-> s.transform(Challenge::generateLastName));
        functionList.add(Mohammed::last);
        functionList.add(new Person("Jawhar")::last);
        transform(names, functionList);
//        Arrays.setAll(names, s -> functionList.get(0).apply(names[s]));
//        System.out.println( Arrays.toString(names) );
//        functionList.add(String::new); // valueOf does the same thing.
//        Arrays.setAll(names, i-> functionList.get(1).apply(generateMiddleName(names[i])));
//        System.out.println(Arrays.toString(names));
//        Arrays.setAll(names, i-> functionList.get(1).apply(generateLastName(names[i])));
//        System.out.println(Arrays.toString(names));
//        System.out.println("-".repeat(50));
        //System.out.println( functionList.get(2).apply("Karim") );
        //When using an instance u can use an expression instead, see bellow:
        //System.out.println(  functionList.get(3).apply("Rahim"));
    }

    public static void transform(String[] nameArray, List< Function<String, String> > functionLis){
        List<String> backedByArray = Arrays.asList(nameArray);
        for(Function<String, String> func : functionLis){
            backedByArray.replaceAll( s-> s.transform(func));
            System.out.println( Arrays.toString(nameArray) );
        }
    }

    public static String generateMiddleName(String s){
        return String.join(" ", s, (char) new Random().nextInt(65,91)+"." );
    }
    public static String generateLastName(String s){
        String ss = s.substring(0, s.indexOf(" "));
        return String.join(" ", s, new StringBuilder(ss).reverse() );
    }
}
