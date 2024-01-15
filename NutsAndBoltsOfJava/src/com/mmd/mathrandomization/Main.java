package com.mmd.mathrandomization;

import java.lang.Math;
import java.util.Optional;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        int maxMinusFive = Integer.MAX_VALUE - 5;
        for (int j = 0, id = maxMinusFive; j < 10; j++, id++) {//Here silent overflow happens. Which could prove troublesome.
            System.out.printf("Assigning id: %,d%n", id);
        }

//        for(int j=0, id=maxMinusFive; j<10; j++, id=Math.incrementExact(id)){//Math.incrementExact(int or long) throws
//            System.out.printf("Assigning id: %,d%n", id);                   //ArithmeticException error if the value overflow
//        }//the condition in the method is (value ==Integer.MAX_VALUE()) throw new ArithmeticException("integer overflow); return a+=1;

        System.out.println();
//        int minPlus5 = Integer.MIN_VALUE + 5;
//        for (int j = 0, id = minPlus5; j < 10; j++, id=Math.decrementExact(id)) {//The same thing only if Integer.MIN_VALUE==value
//            System.out.printf("Assigning id: %,d%n", id);                       //throws the ArithmeticException with related message
//        }

        System.out.println(Math.abs(Integer.MIN_VALUE));//abs Integer.MIN_VALUE doesn't exist. so overflows into the MIN_VALUE again.
//        System.out.println(Math.absExact(Integer.MIN_VALUE));//This version throws the ArithmeticException n does allow the overflow.

        System.out.println( Math.max(10.00121f, 10.0012) );//Float n double together the larger type will be used to do the calculation.

        System.out.println(Math.round(10.49f));//takes either a float or double. Returns long for double n int for a float argument.
        System.out.println(Math.floor(10.99f)); //takes double n returns a whole double .0 always
        System.out.println(Math.floorDiv(15, 6));
        System.out.println(Math.ceil(10.0001)); //takes double n returns a whole double .0 always
        System.out.println(Math.pow(2,5)); //takes doubles n returns double
        System.out.println(Math.sqrt(36));
        System.out.println(Math.floorMod(-15,4));//This returns non negative result, n useful when u require non-negateive results
        System.out.println(-15%4);      //This might or might nor return negative dependent on the values.
//        Math.addExact();
//        Math.subtractExact();
//        Math.multiplyExact();
//        Math.toIntExact();
//        Math.negateExact();

        for(int i=0; i<26; i++)
        System.out.printf("%1$d is %1$c ", (int)(Math.random() *(91-65))+65 );

        System.out.println();

        for(int i=0; i<26; i++)
            System.out.printf("%1$d is %1$c ", (int)(Math.random() *(1+'Z'-'A'))+'A' );

        System.out.println();

        Random r = new Random();
        for(int i=0; i<26; i++)
            System.out.printf("%1$d is %1$c ", r.nextInt('A', 'Z'+1));
        System.out.println();

        r.ints()
                .limit(10)
                .forEach(n-> System.out.print(n + ", "));
        System.out.println();

        r.ints(10)//one argument specifies the streamSize which is long
                .forEach(n-> System.out.print(n + ", "));
        System.out.println();

        r.ints('A', 'Z'+1)// 1st arg is lowerBound(origin), 2nd is upperBound(bound).
                .limit(10)
                .forEach(n-> System.out.print(n + ", "));
        System.out.println();

        r.ints(10, 'A', 'Z'+1)//1st is streamSize, 2ns is randomNumberOrigin, 3rd is Bound
                .forEach(n-> System.out.print(n + ", "));
        System.out.println();


        r.doubles(10, -10.0, 0)//doubles n longs' parameters are exactly the same as ints
                .forEach(n-> System.out.print(n + ", "));
        System.out.println();
    }
}