package com.mmd.Hashing;

public class PlayingCard {
    private String suit;
    private String face;
    private int internalHash;

    public PlayingCard(String suit, String face) {
        this.suit = suit;
        this.face = face;
        internalHash = suit == "Clubs" ? 11 : 12;
    }

    @Override
    public String toString() {
        return face + " of " + suit;
    }

    @Override
    public int hashCode() {
        return internalHash; //return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {// The only time the equals method is called is when the hashCode method returns the same
        System.out.println("Equals on Playing card is called");
        return false; //return super.equals(obj);       //bucket identifier.
    }
}
