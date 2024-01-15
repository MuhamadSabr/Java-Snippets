package com.mmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Challenge {
    public static void main(String[] args) {
        String[] stringArray = {"Mohammed", "Bob", "ahmed", "Karim", "anna", "Jawhar", "Rafiq","shafiq"};
        Supplier<String> getMiddleName = () -> stringArray[new Random().nextInt(0, stringArray.length)];
        //Arrays.setAll(stringArray, i-> ( stringArray[i] + " ." + getMiddleName.get() + " " + reverseString(stringArray[i])).toUpperCase() );
        List<String> backedByArray = Arrays.asList(stringArray);// A list backed by an array will throw an exception if u use the removeIf method on it.
        backedByArray.replaceAll(s-> (s + " ." + getMiddleName.get().split(" ")[0] + " " + reverseString(s)).toUpperCase());
        backedByArray.forEach(s-> System.out.println(s));

        List<String> stringList = new ArrayList<>(List.of(stringArray));
        System.out.println("-".repeat(50));
        //stringList.removeIf(s-> s.split(" ")[0].equals(s.split(" ")[2]) );
        stringList.removeIf(s-> s.substring(0,s.indexOf(" ")).equals( s.substring(s.lastIndexOf(" ")+1) )  );

        stringList.forEach(s-> System.out.println(s));

    }
    public static String reverseString(String string){
        return new StringBuilder(string).reverse().toString();
    }

}
