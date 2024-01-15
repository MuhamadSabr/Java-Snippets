package com.mmd.streamsource;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class challenge {
    public static void main(String[] args) {

        int seed=1;
        Stream<String> bPool = Stream.iterate(seed, n-> n + 1)
                .limit(15)
                .map(u-> "B" + u);
        seed+= 15;
        Stream<String> iPool = Stream.iterate(seed, n-> n< 31, n-> n + 1)
                .map(u-> "I" + u);

        seed+=15;
        Set<Integer> nPoolSet = new LinkedHashSet<>(15);
        for( int i=seed; i< seed+15; i++){
            nPoolSet.add(i);
        }
        Stream<String> nPool = nPoolSet.stream().map(u-> "N" + u);

        int[] gPoolArray = new int[15];
        Arrays.setAll(gPoolArray ,i-> i+46);
        Stream<String> gPool = Arrays.stream(gPoolArray)
                        .mapToObj(u-> "G" + u);

        Stream<String> oPool = IntStream.rangeClosed(61, 75)
                        .mapToObj(u-> "O" + u);

        Stream.generate(() -> new Random().nextInt(61,75))
                .distinct()     //If ur supplier method can never generate 15 distinct number then i will be infinite be careful.
                .limit(15)
                .map(u-> "O" + u)
                .sorted()
                .forEach(s-> System.out.print(s + " "));
                System.out.println();


        Stream.concat(Stream.concat(bPool, Stream.concat(iPool, nPool)),
                Stream.concat(gPool, oPool)).forEach(s-> {
                    System.out.print(s + " ");
                    if("15 30 45 60 75".contains(s.charAt(1) +"" + s.charAt(s.length()-1))){
                        System.out.println();
                    }
        } );
    }
}
