package com.mmd;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> bingoPool = new ArrayList<>(75);
        int start=1;
        for(char c : "BINGO".toCharArray()){
            for(int i=start; i<(start+15); i++){
                bingoPool.add(c+""+i);
            }
            start+= 15;
        }

        Collections.shuffle(bingoPool);
        for(int i=0; i<15; i++){
            System.out.println(bingoPool.get(i));
        }
        System.out.println("-.-".repeat(20));

//        List<String> firstOnes = bingoPool.subList(0,15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0,15));
        firstOnes.replaceAll(s-> s.transform( ss-> ss.charAt(0)=='G'||ss.charAt(0)=='O' ? ss.charAt(0)+"-"+ss.charAt(1) : ss));

        //The pipeline starts with a stream source(where the elements r coming from).
        var str = bingoPool.stream();// this chain, this list of 5 operations is called a stream pipeline.
        var str2 =str.limit(15) //Everything else in between the source n the terminal is an "intermediate operation"
                .filter(s-> s.charAt(0)=='G' || s.charAt(0)=='O')//An intermediate operation is not required.
                .map(s-> s.charAt(0) + "-" + s.charAt(1))//U can v a pipeline with just the source n terminal operation.
                .sorted();               //Every "intermediate operation process elements on the stream n returns a stream as a result.
//                .map(String::toLowerCase); //map behaves like replaceAll
//                .forEach(System.out::println);//Stream pipelines end in a terminal operation. Which produces a result or side effect.
        //A terminal operation is required. Only forEach n forEachOrdered has void return types. All others return some type of data

        str2.forEach(System.out::println);

        Character[] strings = new Character[]{'s', 't', 'r', 'i', 'n', 'g'};
        var strArray1 =Arrays.stream(strings). sorted(Comparator.reverseOrder());//only does not support primitive char.
        Arrays.stream(strings, 2, 4).forEach(System.out::println);//startInclusive, endExclusive.
//        Stream.of(strings).forEach(System.out::println);// It takes T... values or T t(collection)
        var strArray2 = Stream.of('h', 'e', 'l', 'l', 'o').map(c-> (char)(c-32));// creating list of any type you want.
        System.out.println();
        Stream.concat(strArray1, strArray2)
                .map(c-> "-" + c)
                .forEach(System.out::println);
        System.out.println("-".repeat(50));

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for(char c : "BINGO".toCharArray()){
            int[] ints = new int[15];
            final int myLabelNumber = bingoIndex;
            Arrays.setAll(ints, i-> ints[i]=i+myLabelNumber);
            myMap.put(c, ints);
            bingoIndex+= 15;
        }

        myMap.entrySet().stream()//Stream<<Entry<Character,int[]>>
                .map(kv -> kv.getKey() + " hase range: " + kv.getValue()[0] + " - "
                                            + kv.getValue()[kv.getValue().length-1])//Stream<String>
                .forEach(System.out::println);
        System.out.println("-".repeat(40));
        Stream.generate(() -> new Random().nextInt(2))//Takes a supplier. n having only this sentence generates 0 or 1 infinitely
                .limit(10) //use this to limit generating.
                .forEach(i-> System.out.print(i+" "));
        System.out.println();

                        //The 1st arg is used for the first value. After that the 2nd parameter is used to determine the next values in the sequence
        IntStream.iterate(1, n-> n+1)//Generates a sequence of values based on the initial seed value(1st arg),
                .filter(Main::isPrime) //a UnaryOperator(2nd arg) that generates the next value in the sequence. Used to generate an
                .limit(20)              //infinite stream of values. Then u must use .limit to make it finite.
                .forEach(n-> System.out.print(n + " "));
        System.out.println();

        IntStream.iterate(1, n-> n+1)//Above we say generate a number if it is not prime don't go to the next step but generate again.
                .limit(100)// Here we say we only want to generate 100 numbers
                .filter(Main::isPrime)//Then these 100 numbers must be prime, don't include those that are not.
                .forEach(n-> System.out.print(n + " "));
        System.out.println();

        IntStream.iterate(1, n-> n<=100, n-> n+1)//1st is seed, but second is a IntPredicate n 3rd here is the IntUnaryOperator.
                .filter(Main::isPrime)// This version the 2nd arg acts as the limit. It stop generating once that test fails.
                .forEach(n-> System.out.print(n + " "));
        System.out.println();

        IntStream.range(1, 101)// (intStartInclusive, intEndExclusive)
                .filter(Main::isPrime)
                .forEach(n-> System.out.print(n + " "));
        System.out.println();

        IntStream.rangeClosed(1,101)// (intStartInclusive, intEndValueInclusive)
                .filter(Main::isPrime)
                .forEach(n-> System.out.print(n + " "));
        System.out.println();
    }


    public static boolean isPrime(int number){
        if(number<=2){
            return false;
        }
        for(int i=2; i<number; i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
}