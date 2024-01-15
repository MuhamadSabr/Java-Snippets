package com.mmd.expression.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"
        ));
        for(String item : list){
            System.out.println(item);
        }
        System.out.println("----------"); //forEach is a default  method on Iterable that List  inherits
        list.forEach(  item -> System.out.println(item) ); // If u only have one parameter u don't need () around it.
        //U can also specify the type of the parameter on the left side(U need () in that case). But it is unnecessary.
        String prefix= "nato";
        System.out.println("-------------");
        list.forEach((var item) ->{
            char character = item.charAt(0);
            System.out.printf("%s %s means %s%n", prefix, item, character);
        });
        System.out.println("---------");
        var a = calculator( (o1,o2) -> {System.out.print("blablabla "); return 0;} ,5, 5 );
        var b = calculator( (n1, n2) -> n1/n2, 5.0,3.0 );
        var c = calculator( (first, second) -> first.toUpperCase() + " " + second.toUpperCase(), "Mohammed", "Othman");
        //Here you pass two arguments(based on the single abstract method
        //Then define an signle statement or code for it. After that u pass the calculator's other two arguments.
        BinaryOperator<String> func = (first, second) -> first.toUpperCase() + " " + second.toUpperCase();
        System.out.println(func.apply("Mohammed", "Othman"));
        //Above u created a reference to the functional interface assigning it the lambda expressions that fits it.
        //After that u called the abstract method on it passing the right number of arguments with the right type.
        System.out.println("-".repeat(50));
        List<Double[]> coords = Arrays.asList(// This is an error List<[]Double> so always after the type must be the []
                new Double[] {2.454334, 3.454545},
                new Double[]{2.34454, 3.3454354},
                new Double[]{2.345454, 3.35454}
        );
        coords.forEach(s-> System.out.println(Arrays.toString(s)));
        BiConsumer<Double, Double> biConsumer = (u,t)->
                System.out.printf("Lat %f and lon %f%n", u, t); // This is declaring a variable with a lambda expression
        biConsumer.accept(coords.get(0)[0], coords.get(0)[0]);  //After declaring the variable u have to call the functional method manually
        // on that variable which is an instance of the Interface that the function method resides on.
        coords.forEach(s -> processPoint(s[0], s[1], (ss, sss) -> System.out.println(ss + " " + sss)));
        //Above u call the method processPoint based on the number of elements in coords(which is 3 here)
        //U pass its two arguments and the third argument which is a lambda expression then the method calls the functional method on the lambda expression.
        System.out.println("-".repeat(50));
        BiPredicate<String, String> biPredicate = (s, ss)-> {System.out.println(s.equalsIgnoreCase(ss)); return s.equalsIgnoreCase(ss);};
        biPredicate.test("false", "fAlse");
        List<String> stringList = new ArrayList<>(List.of("shafiq", "Ahmed", "Jawhar", "Shafiq", "Rafiq", "shafiq"));
        System.out.println(stringList);
        //Predicate<String> stringPredicate = s -> s.equalsIgnoreCase("mohammed"); //No need of creating this as a variable
        stringList.removeIf(s -> s.equalsIgnoreCase("shafiQ"));// This cut 5 lines of code to only one. Pretty slick.
        System.out.println(stringList);
        stringList.addAll(List.of("echo", "enum", "epas"));
        System.out.println(stringList);
        stringList.removeIf(s-> s.toUpperCase().startsWith("E"));
        stringList.forEach( s-> System.out.println(s) );

        String stringConcat = "Mohammed Saber Othman Sahreef";

        System.out.println("-".repeat(50));
        Function<String, String[]> stringFunction = s -> s.split(" ");
        Arrays.asList(stringFunction.apply(stringConcat)).forEach(s-> System.out.println(s));
        System.out.println("-----------------");
        list.replaceAll(s-> s.charAt(0) + " - " + s.toUpperCase()//replaceAll uses apply function on UnaryOperator interface
        ); // Replace every element with firstCharacter + - plus the
        list.forEach(s -> System.out.println(s));                   //Complete word in upper case.
        String[] stringsArray = new String[10];
        System.out.println(Arrays.toString(stringsArray));
        //Arrays.fill(stringsArray, "");
        Arrays.setAll(stringsArray, i -> i+1 +". ");// It is empty but not null after this.
        //U can see that the difference is that u have to pass the Array, after that the argument is index of the current element
        //and not the element itself.
        System.out.println(Arrays.toString(stringsArray));
        Arrays.setAll(stringsArray, (i) -> switch (i){
            case 0 -> "zero";
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            default -> String.valueOf(i);
        });
        System.out.println(Arrays.toString(stringsArray));
        //List.of(stringsArray).forEach(s-> System.out.println(s));


        var size = list.size();
        String[] strings = generateRandomStringArray(list.toArray(new String[0]), 7, () -> new Random().nextInt(0,size));
        System.out.println(Arrays.toString(strings));
        //size = list.size();
    }

    public static String[] generateRandomStringArray(String[] values, int count, Supplier<Integer> s){
        String[] strings = new String[count];
        for(int i = 0 ; i<count ; i++){
            strings[i] = values[s.get()];
        }
        return strings;
    }

    public static <T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer){// I want to be able to pass more than just Double so T
        consumer.accept(t1, t2);
    }
    public static <T> T calculator(Operation<T> function, T value1, T value2){
        T result = function.operate(value1, value2);
        System.out.println("Operation result : " + result);
        return result;
    }

}
