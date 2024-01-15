package com.mmd;

import com.mmd.cards.Card;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Collection<String> stringList = new HashSet<>();

        String[] strings = {"Mohammed", "Karim", "Jawhar", "Didar", "Pola"};
        stringList.addAll(Arrays.asList(strings));
        System.out.println(stringList);
        stringList.add("Fred");
        stringList.addAll(List.of("Azad", "Aziz", "Shafiq"));
        System.out.println(stringList);
        System.out.println("Gary is in the list? " + stringList.contains("Gary"));
        stringList.removeIf(s-> s.startsWith("A"));
        System.out.println(stringList);

        System.out.println(Card.Suit.HEART.getImage());
        System.out.println(Card.Suit.DIAMOND.getImage());
        System.out.println(Card.Suit.CLUB.getImage());
        System.out.println(Card.Suit.SPADE.getImage());



    }
}