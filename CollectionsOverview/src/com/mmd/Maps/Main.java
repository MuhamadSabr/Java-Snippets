package com.mmd.Maps;

import com.mmd.setsandmaps.Contact;
import com.mmd.setsandmaps.ContactData;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] a){
        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");

        List<Contact> fullList = new ArrayList<>(emails);
        fullList.addAll(phones);

        fullList.forEach(System.out::println);
        System.out.println("-".repeat(50));

        Map<String, Contact> contactMap = new HashMap<>();// You cannot pass a Collection to the constructor, only other Map types.
        for(Contact contact : fullList){
            contactMap.put(contact.getName(), contact);//If the key already exists then it replaces its value, n does not complain.
        }                                               //If u don't want to replace existing values use putIfAbsent.
        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("-".repeat(50));

        System.out.println(contactMap.get("Linus Van Pelt"));//.get(ObjectKey). returns the Object if not found returns null
        System.out.println(contactMap.getOrDefault("Snoopy", new Contact("Linus Van Pelt")));//If 1st arg not found returns the 2nd
        System.out.println(contactMap.containsValue(new Contact("Linus Van Pelt")));
        System.out.println("-".repeat(50));

        contactMap.clear();
        for(Contact contact : fullList){
            Contact duplicate = contactMap.put(contact.getName(), contact); // If the element is already in the Map then it returns it
            if(duplicate!=null){                                        //after that it replaces it. If not in the map Null is returned.
                //System.out.println("Duplicate : "+ duplicate);
                //System.out.println("Current   : " + contact);
                contactMap.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }

        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("-".repeat(50));

        contactMap.clear();
        for(Contact contact : fullList) {
            contactMap.putIfAbsent(contact.getName(), contact);//If the key already exists then it ignores the put operation
        }                                                      // , n returns a copy of it. Returns null if the key is not in the Map.
        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("-".repeat(50));


        contactMap.clear();
        for(Contact contact : fullList){
            Contact duplicate = contactMap.putIfAbsent(contact.getName(), contact); // If the element is already in the Map then it returns it
            if(duplicate!=null){                                        //and ignores it. If not in the map Null is returned.
//                System.out.println("Duplicate : "+ duplicate);
//                System.out.println("Current   : " + contact);
                contactMap.put(contact.getName(), contact.mergeContactData(duplicate));
            }
//            System.out.println("Current   : " + contact);
        }

        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("-".repeat(50));

        contactMap.clear();
//        for(Contact contact : fullList){
//            //contactMap.merge(contact.getName(), contact, (previous, current)-> previous.mergeContactData(current));
//            contactMap.merge(contact.getName(), contact, Contact::mergeContactData);
//        }
//        fullList.forEach(contact -> contactMap.merge(contact.getName(), contact,
//                (previous, current) -> current.mergeContactData(previous) ));
        fullList.forEach( contact -> contactMap.merge(contact.getName(), contact, Contact::mergeContactData) );
        //The 1st arg is the ObjectKey, the 2nd is the value, the 3rd arg is a BiFunction argument.

        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("-".repeat(50));


        contactMap.compute("Lucy Van Pelt", (k, v)-> new Contact(k));
        contactMap.computeIfAbsent("Lioness Van Pelt", Contact::new);
        contactMap.computeIfPresent("Minnie Mouse", (k, v) -> new Contact(k));
        //contactMap.replaceAll((k, v) -> new Contact(k)); //Takes a Function returns void, n return type of the F must be type of V.
        contactMap.replaceAll( (k, v) -> {
            v.replaceEmail("daffy@google.com", "daffy@mmd.com");
            return v;
        });
        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("-".repeat(50));

        Contact replaced=contactMap.replace("Linus Van Pelt", new Contact("Snoopy"));//The two args version returns the replaced element
        System.out.println(replaced);             //If no element was replaced returns null, meaning the KeyObject was not in the Map
        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));// Remember this version only cares about the KeyObject
        System.out.println("-".repeat(50));                             //and does not check for values equality of both


        replaced.mergeContactData(contactMap.get("Mickey Mouse"));
        boolean successReplacement = contactMap.replace("Linus Van Pelt", new Contact("Linus Van Pelt"), replaced);
        if(successReplacement){
            System.out.println("successfully replaced");
        }else System.out.println("Key n value both must match");

        successReplacement = contactMap.replace("Linus Van Pelt", new Contact("Snoopy"), replaced);
        if(successReplacement){
            System.out.println("successfully replaced");
        }else System.out.println("Key n value both must match");

        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("-".repeat(50));

        boolean successRemove = contactMap.remove("Linus Van Pelt", new Contact("Snoopy"));
        if(successRemove){              //If both key n match an element in the map it is removed n true is returned.
            System.out.println("successfully removed"); //If exact element with key n value is not found in the map false is returned
        }else System.out.println("Key n value both must match");

        Contact removed = contactMap.remove(replaced.getName()); //Takes only KeyObject as arg n returns the removed element
        if(removed!=null){                                          //If the Key is not in the map null is returned.
            System.out.println("successfully removed");
        }else System.out.println("Key was not found in the map");

        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("-".repeat(50));


        Map<String, Integer> fruits = new HashMap<>();
        fruits.computeIfAbsent("apple", k-> (int)k.charAt(0)); //The 1st arg is the KeyObject the 2nd arg is a Function with the Key parameter
        fruits.computeIfPresent("apple", (k, v) ->(int)k.charAt(0) - v);//Only computeIfAbsent takes a Function as the 2nd arg
        fruits.compute("apple", (k,v) -> (int)k.charAt(0)+v);   //computer and computeIfPresent take BiFunction as the 2nd arg.
        //Be careful working with the v of computer might result in null       //BiFunction means k, v . Function is only the K.
        fruits.merge("apple", 3, (oldV, newV) -> oldV+newV);
        fruits.merge("orange", 3, (oldV, newV) -> oldV+newV);//If the key does not exist it adds it with a Value of 0.
        Map<String, String> fruString = new HashMap<>();
        fruString.merge("orange", "orange", (oldV, newV) -> oldV+newV);//For string it adds it with a value of "" blank
        fruString.forEach((k, v) -> System.out.println(k+" : " + v));//You don't face null exceptions like u do with compute
        System.out.println("-".repeat(50));


    }
}
