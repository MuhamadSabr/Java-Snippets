package com.mmd.cards;

import java.util.ArrayList;
import java.util.List;

public record Card(Suit suit, String face, int rank)
{// We make it a record because after creating a card there is no sense in changing it.
    public Card(Suit suit, int rank) {
        this(suit," ", rank);
    }

    public Card(Suit suit, String face) {
        this(suit, face, 0);
    }

    @Override
    public String toString() {
        return "%s%c(%d)".formatted(face, suit.getImage(), rank);
    }

    public static Card getNumericCard(Suit suit, int cardNumber){
        if(cardNumber<2 || cardNumber >10)
            return null;
        return new Card(suit, String.valueOf(cardNumber), cardNumber-2);
    }

    public static Card getFaceCard(Suit suit, char abbr){
        int abbrIndex = "JQKA".indexOf(abbr);
        if(abbrIndex<0)
            return null;
        return new Card(suit, String.valueOf(abbr), 9+abbrIndex);
    }

    public static List<Card> getStandardDeck(){
        List<Card> deck = new ArrayList<>(52);
        for (Suit suit : Suit.values()){
            for(int i=2 ; i<=10 ; i++){
                deck.add(getNumericCard(suit, i));
            }
            for(char chr : new char[]{'J', 'Q', 'K', 'A'}){
                deck.add(getFaceCard(suit, chr));
            }
        }
        return deck;
    }

    public static void printDeck(List<Card> deck, String description, int rows){
        System.out.println("-".repeat(30));
        if(description!= null){
            System.out.println(description);
        }
        int cardsInRow = deck.size() / rows;
        for(int i = 0; i<rows; i++){
            int startIndex = i * cardsInRow;
            int endingIndex = startIndex+cardsInRow;
            deck.subList(startIndex, endingIndex).forEach(s-> System.out.print(s+" "));
            System.out.println();
        }
    }

    //@Override static method cannot be annotated with @Override
    public static void printDeck(List<Card> deck){
        printDeck(deck,"Current Deck", 4);
    }


    public enum Suit{
        CLUB, DIAMOND, HEART, SPADE;
        public char getImage() {
            return new char[]{9827, 9830, 9829, 9828}[this.ordinal()];
        }

    }


}


