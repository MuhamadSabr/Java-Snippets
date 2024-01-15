package com.mmd;

import java.lang.module.Configuration;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class minichallenge {
    public static void main(String[] args) {

        Consumer<String> printTheParts = new Consumer<String>(){
            @Override
            public void accept(String sentence){
                String[] parts = sentence.split(",");
                for (String part : parts){
                    System.out.println(part);
                }
            }
        };
        printTheParts.accept("Mohammed, Saber, Othman, Karim, Shareef");
        Function<String, String[]> printParts = s -> s.split(",");
        System.out.println ( Arrays.toString(printParts.apply("Mohammed, Saber, Othman, Karim, Shareef") ) );
        Arrays.asList( printParts.apply("Mohammed, Saber, Othman, Karim, Shareef") ).forEach(s-> System.out.println(s));
        Consumer<String> consumer = sentence -> Arrays.asList( sentence.split(",") ).forEach(s->System.out.println(s));
            //String[] parts = sentence.split(",");
            //Arrays.asList(parts).forEach(s->System.out.println(s));

        consumer.accept("Mohammed, Saber, Othman, Karim, Shareef");
        System.out.println("------------------");

        UnaryOperator<String> everySecondChar = s-> {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i<s.length(); i++){
                if(i%2 ==1){
                    stringBuilder.append(s.charAt(i));
                }
            }
            return stringBuilder.toString();
        };
        System.out.println (everySecondChar.apply("1234567890") );
        System.out.println(everySecondCharacter(s-> {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i<s.length(); i++){
                if(i%2 ==1){
                    stringBuilder.append(s.charAt(i));
                }
            }
            return stringBuilder.toString();
        }, "1234567890"));
        System.out.println("-".repeat(30));

        //Lambdas represent deferred execution of snippets of code.
        Supplier<String> iLoveJava = () -> "I Love Java";
        System.out.println(iLoveJava.get());

    }

    public static String everySecondCharacter(UnaryOperator<String> operator, String s){ //This is how u create a method that is a target for lambda expressions.
        return operator.apply(s);
    }

}
