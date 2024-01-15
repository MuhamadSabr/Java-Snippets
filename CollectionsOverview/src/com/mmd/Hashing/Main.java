package com.mmd.Hashing;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l", "He", "lo");
        String dText = "Hel".concat("lo");
        String eText = "hello";

        List<String> hellos = new ArrayList<>(List.of(aText, bText, cText, dText, eText));
        hellos.forEach(s-> System.out.println(s + " " + s.hashCode()));
        System.out.println("-".repeat(15));
        Set<String> mySet = new HashSet<>(hellos);//Set is abstract it cannot be instantiated remember.
        mySet.forEach(s-> System.out.println(s + " " + s.hashCode()));// Most collections allow creation of a collection type
                                    //by passing a different collection to the constructor.

        for(String seE : mySet){
            System.out.print(seE+" ");
            for(int i = 0 ; i<hellos.size(); i++){
                if(seE==hellos.get(i)){ // == checks to verify that the two variables point to the same object in memory(same reference)
                    System.out.print(i + ", ");// It does not check one by one its character like the String.equals does.
                }
            }
            System.out.println();
        }

        PlayingCard aceOfHearts = new PlayingCard("Hearts", "Ace");
        PlayingCard kingOfClubs = new PlayingCard("Clubs", "King");
        PlayingCard queenOfSpades = new PlayingCard("Spades", "Queen");

        List<PlayingCard> cards =  List.of(aceOfHearts, kingOfClubs, queenOfSpades) ;
        cards.forEach(s-> System.out.println(s + " " + s.hashCode()));

        Set<PlayingCard> deck = new HashSet<>();
        for(PlayingCard card : cards){
            if(!deck.add(card)){
                System.out.println("Duplicate element found");
            }
        }
        System.out.println(deck);

    }
}
