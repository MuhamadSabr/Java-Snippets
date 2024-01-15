package com.mmd.LinkedHashSetnTreeSet;

import com.mmd.cards.Card;
import com.mmd.setsandmaps.Contact;
import com.mmd.setsandmaps.ContactData;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");
        Comparator<Contact> contactComparator = Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sorted = new TreeSet<>(contactComparator);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);
        System.out.println("-".repeat(30));

        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(s-> justNames.add(s.getName()));
        justNames.forEach(System.out::println);

        System.out.println("-".repeat(30));

        NavigableSet<Contact> fullSet = new TreeSet<>(contactComparator);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);

        System.out.println("-".repeat(30));

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.sort(sorted.comparator()); // treeSet.comparator() returns the comparator used by the treeSet
        fullList.forEach(System.out::println);

        System.out.println("-".repeat(30));

        Contact min = Collections.min(fullSet, sorted.comparator());  // Remember Collections.min n .max need a Comparator
        Contact max = Collections.max(fullSet, fullSet.comparator());// as the 2nd arg if the type does not implement Comparable

        System.out.println("-".repeat(30));

        Contact first = fullSet.first();//These .first n .last on treeSet work the same as Collections. min n .max
        Contact last  = fullSet.last(); //Also know that this is the preferred way to get min n max values over Collections

        System.out.printf("min : %s, first : %s%n",min, first);
        System.out.printf("max : %s, last : %s%n",max, last);
        System.out.println("-".repeat(30));

        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);// Since fullSet was created first with a Comparator u don't need
        Contact pollFirst = copiedSet.pollFirst();                                      //to pass the Comparator again.
        Contact pooLast   = copiedSet.pollLast(); //These two remove the first or last elements in the treeSet n return it.

        System.out.printf("Removed from the start of the treeSet : %s%n",pollFirst);
        System.out.printf("Removed from the end of the treeSet : %s%n", pooLast);
        System.out.println("-".repeat(30));
        copiedSet.forEach(System.out::println);
        System.out.println("-".repeat(30));

        Contact daffy = new Contact("Daffy Duck");
        Contact daisy = new Contact("Daisy Duck");
        Contact snoopy= new Contact("Snoopy");
        Contact archie= new Contact("Archie");
        fullSet.forEach(System.out::println);
        System.out.println("-".repeat(30));
        for(Contact contact : List.of(daffy, daisy, last, snoopy)) {
            System.out.println("ceiling " + contact.getName()+ ",,, " + fullSet.ceiling(contact));
            System.out.println("higher " + contact.getName()+ ",,, "+ fullSet.higher(contact));
        }
        System.out.println("-".repeat(30));
        fullSet.forEach(System.out::println);
        System.out.println("-".repeat(30));
        for(Contact contact : List.of(daffy, archie, last, snoopy)) {
            System.out.println("floor " + contact.getName()+ ",,, " + fullSet.floor(contact));
            System.out.println("lower " + contact.getName()+ ",,, "+ fullSet.lower(contact));
        }
        System.out.println("-".repeat(30));

        NavigableSet<Contact> descendingSet = fullSet.descendingSet(); //The returned set is backed by the original set.
        descendingSet.forEach(System.out::println);            //meaning any changes to these changes the original set n vise versa.
        System.out.println("-".repeat(30));
        descendingSet.pollFirst();
        fullSet.pollFirst();
        fullSet.forEach(System.out::println);
        System.out.println("-".repeat(30));
        sorted.forEach(System.out::println);
        System.out.println("-".repeat(30));

        Contact marion = new Contact("Maid Marion");
        //var headSet = sorted.headSet(marion); // headSet returns elements less than the E passed. one argument returns SortedSet type
        var headSet = sorted.headSet(marion, true); //U can pass a 2nd arg, it specifies whether
        headSet.forEach(System.out::println);//the E passed itself is included in the return or not. By default it is not.

        System.out.println("-".repeat(30));

        //var tailSet = sorted.tailSet(marion); // tailSet returns elements greater than the E passed.
        var tailSet = sorted.tailSet(marion, false);// two argument returns NavigableSet type
        tailSet.forEach(System.out::println);// tailSet by default has the 2 argument set to true, so E is included by default.

        System.out.println("-".repeat(30));


        var subSet = sorted.subSet(marion, last);// Again there are two versions, the one that u don't specify inclusive args it returns SortedSet
        //var subSet = sorted.subSet(marion, false, last, true);
        subSet.forEach(System.out::println);// By default, the fromE is included n the toE is not included


    }
}
