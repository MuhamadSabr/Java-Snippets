package com.mmd.Maps;

import com.mmd.setsandmaps.Contact;
import com.mmd.setsandmaps.ContactData;

import java.lang.module.Configuration;
import java.util.*;

public class MapViewsMain {
    public static void main(String[] args) {

        Map<String, Contact> contactMap = new HashMap<>();
        ContactData.getData("email").forEach(contact -> contactMap.put(contact.getName(), contact));
        ContactData.getData("phone").forEach(contact -> contactMap.put(contact.getName(), contact));

        //contactMap.forEach((k, v) -> System.out.println(k + " : " + v));

        Collection<Contact> valuesView = contactMap.values();// returns a collection, that is backed by the hashMap.
        Set<Map.Entry<String, Contact>> entryView = contactMap.entrySet();// returns a set of entry(key n values of the map). Each key value pair is stored as an entry
        Set<String> keysView = contactMap.keySet(); //returns a set of the keys               //n is unique because key must be different.

        System.out.println(keysView);
        NavigableSet<String> keysCopy = new TreeSet<>(keysView);
        System.out.println(keysCopy);

        keysView.remove("Linus Van Pelt");//Removing elements from the map by using the keySet or keyView.
        contactMap.forEach((k, v) -> System.out.println(k + " : " + v)); //Doing keysCopy.remove() does not do the same
        System.out.println("-".repeat(50));

        keysView.removeIf(o-> !List.of("Daffy Duck", "Charlie Brown").contains(o));
        contactMap.clear();


        ContactData.getData("phone").forEach(contact -> contactMap.put(contact.getName(), contact));
        ContactData.getData("email").forEach(contact -> contactMap.put(contact.getName(), contact));

        valuesView.removeAll(ContactData.getData("email"));
        contactMap.forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println(keysView);
        System.out.println("-".repeat(50));

        List<Contact> list = new ArrayList<>(valuesView);
        list.sort(Comparator.comparing(Contact::lastFirstName));
        list.forEach(s-> System.out.println(s.lastFirstName() + " : " + s));
        System.out.println("-".repeat(50));
//        for( var node : contactMap){ //For each is not applicable for Map
//        }
        for(var node : entryView){// Remember value here is a Contact.
            System.out.println(entryView.getClass().getName());
            if(!node.getKey().equals(node.getValue().lastFirstName())){
                System.out.println(node.getClass().getName());
                System.out.println("Key doesn't match name, k : " + node.getKey()+ ", name: "+ node.getValue().getName());
            }
        }


    }
}
