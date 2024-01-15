package com.mmd.Maps.LinkedHashMapNTreeMap;

import com.mmd.Maps.HashMapChallenge.Location;
import com.mmd.setsandmaps.Contact;

import java.time.LocalDate;
import java.util.*;

public class Main {

    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {

        Course jmc = new Course("jmc101", "Java Master Class", "Java");
        Course python = new Course("py_201", "Python machine learning", "Python");
        Course networking = new Course("net308", "Networking masterclass", "Cisco");
        Course security   = new Course("sec010", "Cyber security", "Cyber security");

        addPurchase("Mohammed", jmc, 12.99);
        addPurchase("Mohammed", python, 13.00);
        addPurchase("Karim", networking, 10.99);
        addPurchase("Jawhar", networking, 10.99);
        addPurchase("Ahmed", jmc, 13.00);
        addPurchase("Shafiq", jmc, 10.99);
        addPurchase("Rafiq", security, 13.00);

        students.forEach((k, v) -> System.out.println(k+ ": " + v));
        System.out.println("-".repeat(50));
        purchases.forEach((k, v) -> System.out.println(k+ ": " + v));

        NavigableMap<LocalDate, List<Purchase>> purchasesPerDate = new TreeMap<>();
        for(Purchase p : purchases.values()){
            purchasesPerDate.compute(p.purchaseDate(), (pkey, pList) -> {
                List<Purchase> list = pList!=null ? pList : new ArrayList<>();
                list.add(p);
                return list;
            });
        }
        System.out.println("-".repeat(70));
        purchasesPerDate.forEach((k, v) -> System.out.println(k+ ": " + v));

        LocalDate firstDay = LocalDate.ofYearDay(2023, 1);
        LocalDate week1    = firstDay.plusDays(7);

        Map<LocalDate, List<Purchase>> week1Purchases = purchasesPerDate.tailMap(week1); //In TreeSet the headMap is inclusive.
        Map<LocalDate, List<Purchase>> week2Purchases = purchasesPerDate.headMap(week1); //But with MapTree tailMap is inclusive.

        System.out.println("-".repeat(70));
        week1Purchases.forEach((k, v) -> System.out.println(k+ ": " + v));

        System.out.println("-".repeat(70));
        week2Purchases.forEach((k, v) -> System.out.println(k+ ": " + v));

        Map<String, Integer> weeklyCounts = new TreeMap<>();
        for(Purchase purchase : purchases.values()){
            weeklyCounts.merge(purchase.courseId(), 1, (oldValue, newValue) -> oldValue+newValue);
        }

        System.out.println("-".repeat(70));
        weeklyCounts.forEach((k, v) -> System.out.println(k+ ": " + v));
        System.out.println("-".repeat(70));

//        System.out.println( purchases.last); //Only Navigable type includes lastKey, lastEntry, firstKey, firstEntry.
//        System.out.println(purchases.lower); //Also Only Navigable has floorKey, floorEntry, ceilingKey, lowerKey, higherEntry. not linkedHashMap nor Map
        System.out.println(purchasesPerDate.firstKey());
        Map.Entry<LocalDate, List<Purchase>> lastEntry= purchasesPerDate.firstEntry();
        System.out.println(lastEntry);/*.getValue()*/ //U can use .getKey or .getValue to separate the entry.
        System.out.println(purchasesPerDate.lastKey());//Works like Set but there is an addition version which is
        Map.Entry<LocalDate,List<Purchase>> firstEntry= purchasesPerDate.firstEntry();
        System.out.println(firstEntry);//first|lastEntry. U can use .getValue to only get the value of the entry.

        System.out.println(purchasesPerDate.floorEntry(lastEntry.getKey()));
        System.out.println(purchasesPerDate.ceilingEntry(lastEntry.getKey()));
        System.out.println(purchasesPerDate.lowerEntry(lastEntry.getKey()));
        System.out.println(purchasesPerDate.higherEntry(firstEntry.getKey()));
        System.out.println("-".repeat(70));

        purchasesPerDate.descendingKeySet().forEach(System.out::println);
        purchasesPerDate.descendingMap().forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("-".repeat(70));
        purchasesPerDate.subMap(firstEntry.getKey(), false, purchasesPerDate.higherKey(firstEntry.getKey()), false)
                .forEach((k, v) -> System.out.println(k + " : " + v));

    }

    public static void addPurchase(String name, Course course, double price){
        students.putIfAbsent(name, new Student(name, course));
        students.get(name).addCourse(course);

        int day = new Random().nextInt(1, 15);
        String key = course.courseId() + "_" + students.get(name).getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(), students.get(name).getId(), price, year, day);
        purchases.put(key, purchase);
    }



}
