package com.mmd.cards;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CollectionsChallenge {
    public static void main(String[] args) {
        List<Card> deck = Card.getStandardDeck();
        Collections.shuffle(deck);
    }
}
