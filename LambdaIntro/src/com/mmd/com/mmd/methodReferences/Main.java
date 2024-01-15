package com.mmd.com.mmd.methodReferences;

import java.io.PrintStream;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class PlainOld{
    private static int last_id = 1;
    private int id;
    public PlainOld() {
        id = last_id++;
        System.out.println("Creating a plain old object: id " + id);
    }
}

public class Main {
    public static void main(String... arg) {
        List<String> names = new ArrayList<>(List.of(
                "Anna", "Bob", "Mohammed", "Ahmed", "Karim"
        ));
        names.forEach(s -> System.out.printf("%s%n",s));

        calculator(Integer::sum, 5, 7);
        calculator(Double::max,5.0,9.0);
        //Supplier<PlainOld> plainOldSupplier = () -> new PlainOld();
        Supplier<PlainOld> plainOldSupplier = PlainOld::new;
        PlainOld plainOld= plainOldSupplier.get();

        seedArray(PlainOld::new, 6);
        calculator(String::concat, "Mohammed ", "Saber");

        Function<String, Boolean> u = String::isBlank;//Remember both R type a T type must match the method reference.
        UnaryOperator<String> u1 = String::toUpperCase;
        System.out.println(u.apply(" "));
        System.out.println( "Ahmed".transform(u1) ) ;//Takes a function, u can pass a unaryOperator since it is derived from it.
        //.transfrom does not require to return a string.
        System.out.println("Karim".transform(String::isBlank));

    }

    public static PlainOld[] seedArray(Supplier<PlainOld> reference, final int count){
        PlainOld[] plainOlds = new PlainOld[count];
        Arrays.setAll(plainOlds, i-> reference.get());
        return plainOlds;
    }

    public static <T> void calculator(BinaryOperator<T> operator, T t1, T t2){
        System.out.println( operator.apply(t1, t2) );
    }
}
