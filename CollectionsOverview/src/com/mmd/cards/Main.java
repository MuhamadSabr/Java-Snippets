package com.mmd.cards;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Aces Of Hearts", 1);

        List<Card> cards = new ArrayList<>(13);// Capacity does not change size. size remains 0.
        Collections.fill(cards, aceOfHearts);//ArrayList does not work like array it does not have null objects at creation time
        System.out.println(cards);
        System.out.println("size of cards : " + cards.size());

        List<Card> acesOfHearts = (Collections.nCopies(13, aceOfHearts));
        //The Collections.nCopies works similar to Arrays.fill. First arg is number of objects, seconds is the object.
        //It creates a collection of the second object of first arg number.
        Card.printDeck(acesOfHearts, null, 01);

        Card kingOfClub = Card.getFaceCard(Card.Suit.CLUB, 'K');
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClub);
        Card.printDeck(kingsOfClubs, null, 1);

        Collections.addAll(cards, cardArray);//The second argument is a variables of arguments to be added to the first Collection type.
                                            // n U remember whenever a variable argument is passed an Array can also be passed
        //cards.addAll();// List's add all is not static also it takes a collection and not a variable argument.
        Card.printDeck(cards, null, 1);

        List<Card> newCards = new ArrayList<>(13);
        newCards.addAll(Collections.nCopies(13, kingOfClub)); //U have to use addAll. Assigning it directly would not work
        newCards.addAll(Collections.nCopies(13, kingOfClub));// Collections.nCopies does return a collection
        Collections.copy(newCards, acesOfHearts);//If source does not fit in des(src size > dst size) then u r gonna get an error
        Card.printDeck(newCards, "Dest > Src" ,2);// But if src size < dst size then u will face no error, but
                                //Only the size of the dest elements will be copied to the src and the rest of the dest will be unchanged.
        List<Card> newnewCards= List.copyOf(newCards);//This returns a not modifiable copy be careful
        //If u need a modifiable use Arrays.asList or List.Of at time of creation. Also Collections.copy does not return anything



        List<Card> deck = Card.getStandardDeck();
        Collections.shuffle(deck);
        Card.printDeck(deck, "Shuffled deck", 4);// Mixing up a list, its task is.
        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed deck", 4);
        //Collections.sort(deck); The list has to implement Comparable
        //Collections.sort(deck, (o1, o2) -> o1.suit().compareTo(o2.suit()));// U can replace it with deck.sort(Comparator)
        //Collections.sort(deck, (o1,o2) -> Integer.compare(o1.rank(), o2.rank()));
        Collections.sort(deck, Comparator.comparing(Card::rank)
                .thenComparing(Card::suit).reversed());
        Card.printDeck(deck, "Sorted deck by suit then rank", 4);

        List<Card> kings = deck.subList(4, 8);//The second argument is exclusive(Is not included).
        List<Card> tens  = deck.subList(16, 20);
        int firstIndexOfSublist = Collections.indexOfSubList(deck, kings);
        int lastIndexOfSublist = Collections.lastIndexOfSubList(deck , kings);
        System.out.println("First index of kings sublist " + firstIndexOfSublist);
        System.out.println("Last index of kings sublist  " + lastIndexOfSublist);// Returns the second occurrence of the subList
        Card.printDeck(tens, "Tens", 1);                        //  If only one occurrence then the same result it is.

        boolean t1=Collections.disjoint(acesOfHearts, kings); //Returns true if there is no element in common between the two collections
        System.out.println(t1);                     //One common element is enough to make the condition false.


        Comparator<Card> sortingAlgorithm =  Comparator.comparing(Card::suit).thenComparing(Card::rank);

        Collections.sort(deck, sortingAlgorithm); // Has to be the same as what u pass to the binarySearch
        int indexOfFound = Collections.binarySearch(deck, aceOfHearts, sortingAlgorithm);
        System.out.println(indexOfFound);// the list above has to be practically sorted with the algorithm u pass here, otherwise it does not work corectly.
        System.out.println(deck.indexOf(aceOfHearts));// list.indexOf() returns its index without the list having to be sorted.
        //Remember if the list is small indexOf or lastIndex of provide better performance, but if the list is large u might consider binarySearch

        boolean success = Collections.replaceAll(deck, aceOfHearts, kingOfClub);//accepts a list, oldValue, newValue to replace oldValue
        //new value(arg3) will replace every element with the value of the oldValue(arg 2) in the list(arg1)
        //Bellow u can see that kingOfClub frequency changed from one to two.
        int frequency = Collections.frequency(deck, kingOfClub);// Accepts a collection n an object of that collection's type
        System.out.printf("Frequency is : %s%n", frequency);

        System.out.println( Collections.min(deck, sortingAlgorithm) );// Again u have two overloading methods one that takes a collection
        System.out.println( Collections.max(deck, sortingAlgorithm) );//that implements Comparable, n one that u pass a Comparator with
                                                                     // just like sort and binarySearch

        Collections.sort(deck, sortingAlgorithm);
        Card.printDeck(deck);

        List<Card> clubSuit = deck.subList(0, 13);
        Collections.rotate(clubSuit, 3);
        Card.printDeck(clubSuit, "Rotated with a positive 3, meaning moving the last 3 e to the first three e", 1);
        Collections.rotate(clubSuit, -3);
        Card.printDeck(clubSuit, "Rotated with a -3 distance, meaning the first 3 will be moved to the back of the collection",1);

        Collections.swap(clubSuit, 0, 12);//Accepts a list(arg1), and two int(indexes) of elements to be swapped.
        Collections.swap(clubSuit, 12, 0);
        Card.printDeck(clubSuit, "Swapping index 0 with index 12 and swapping it back", 1);

        for(int i = 0 ;i<clubSuit.size()/2; i++){
            Collections.swap(clubSuit, i, clubSuit.size()-i-1);
        }
        Card.printDeck(clubSuit, "Manual reverse of Club Suit", 1);

    }
}
