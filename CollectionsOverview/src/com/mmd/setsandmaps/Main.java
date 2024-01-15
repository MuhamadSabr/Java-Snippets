package com.mmd.setsandmaps;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Contact> contactEmail = ContactData.getData("email");
        printData("Emails", contactEmail);
        List<Contact> contactPhone = ContactData.getData("phone");
        printData("Phones" ,contactPhone);

        Set<Contact> contactSetEmail = new HashSet<>(contactEmail);
        Set<Contact> contactSetPhone = new HashSet<>(contactPhone);

        int index = contactEmail.indexOf(new Contact("Robin Hood"));
        Contact robinHood = contactEmail.get(index); // This is Creating a variable of type Contract and making it point to the same object in memory that
        if(robinHood.addEmail("Alqurtas.iq") ){     //contactEmail.get(index) points to .
            System.out.println("Added successfully");
        }else{
            System.out.println("Was not added");
        }
        if(robinHood.replaceEmail("RHood@alqurtas.iq.com", "rHood@Erbil.com") ){
            System.out.println("Replaced successfully");
        }else{
            System.out.println("Was not successfull");
        }
        System.out.println( contactSetEmail.contains(contactEmail.get(index)) );
        printData("Adding company email to Robin Hood" ,contactSetEmail);

        Set<Contact> unionAB = new HashSet<>();
        unionAB.addAll(contactSetEmail);
        unionAB.addAll(contactSetPhone);
        printData("\u222A of Email and Phone" , unionAB );

        Set<Contact> intersectAB = new HashSet<>(contactSetEmail);
        intersectAB.retainAll(contactSetPhone);
        printData("\u2229 of A n B", intersectAB);

        Set<Contact> aMinusB = new HashSet<>(contactSetEmail);
        aMinusB.removeAll(contactSetPhone);
        printData(" A minus B : ", aMinusB);

        Set<Contact> bMinusA = new HashSet<>(contactSetPhone);
        bMinusA.removeAll(contactSetEmail);
        printData(" B minus A : ", bMinusA);

        Set<Contact> symmetricDifferenceAB = new HashSet<>();
        symmetricDifferenceAB.addAll(aMinusB);
        symmetricDifferenceAB.addAll(bMinusA);
        printData("Symmetric difference of A n B : ", symmetricDifferenceAB);

        Set<Contact> symmetricDifferenceC = new HashSet<>(unionAB);
        symmetricDifferenceC.removeAll(intersectAB);
        printData("Symmetric differnce by taking all the intersect elements from the Union Set", symmetricDifferenceC);


    }

    public static void printData(String header, Collection<Contact> contacts){
        System.out.println("-".repeat(30));
        System.out.println(header);
        System.out.println("-".repeat(30));
        contacts.forEach(System.out::println);

    }
}
