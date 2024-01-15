package com.mmd.mathrandomization;

import java.util.*;
import java.math.*;
import java.util.stream.Collectors;

public class challenge {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        final int  min = 1, max=6;
        int counter=1, rollDice = 5;

        int[] toKeep = new int[5];

        while(counter<=3) {
//            ArrayList<Integer> throwRole = random.ints(rollDice, min, max + 1)
//                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            List<Integer> throwRole = random.ints(rollDice, min, max+1)
                            .boxed()
                                    .toList();
            System.out.print("Your dice are : [");

            for (int i =0; i< throwRole.size(); i++) {
                System.out.print(throwRole.get(i) + (i+1 == throwRole.size() ? "" : ", "));
            }
            System.out.println("]");
            counter++;


            System.out.println("""
                    Press Enter to score
                    Type "All" to re-roll all the dice.
                    List numbers(separated by spaces) to re-roll selected dice.""");
            String input = scanner.nextLine();
            if(input.isEmpty()){
                System.out.println("Your score is: " + throwRole.stream()
                        .mapToLong(mp-> mp)
                        .sum()
                );
                break;
            }

            if(input.equalsIgnoreCase("all")){
                continue;
            }



        }

    }
}
