package com.mmd.copyingclasses;

import java.util.Arrays;

record Person(String name, String dob, Person[] kids){

    public Person(Person p){ //Remember the default record constructor does not use defensive copy.
        this(p.name, p.dob, p.kids()==null ? null : Arrays.copyOf(p.kids(),p.kids.length));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", kids=" + Arrays.toString(kids) +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {

        Person mohammed = new Person("Mohammed", "10-09-1998", null);
        Person ahmed = new Person("Ahmed", "19-11-1995", null);
        Person karim = new Person("Karim", "15-03-1994", new Person[]{mohammed, ahmed});
        Person jawhar = new Person("Jawhar", "11-04-1993", null);
        Person shafiq = new Person("Shafiq", "03-09-1994", new Person[]{jawhar});

        Person[] persons = {mohammed, ahmed, karim, jawhar, shafiq};
        Person[] personsCopy = Arrays.copyOf(persons, persons.length);// This is a shallow copy.
        //If your array elements are mutable this is a big problem


        Person[] personsDeepCopy = new Person[persons.length];
//        for(int i=0; i<persons.length; i++){
////            String name = persons[i].name();
////            String dob  = persons[i].dob();
////            Person[] kids = persons[i].kids()==null ? null : Arrays.copyOf(persons[i].kids(), persons[i].kids().length);
//            personsDeepCopy[i] = new Person(persons[i]); // If the constructor does not use defensive copy then the same copy is returned.
//        }

        Arrays.setAll(personsDeepCopy, i-> new Person(persons[i]));

        for(int i=0; i<persons.length; i++){
            if(persons[i]==personsDeepCopy[i]){ //== checks for equality of reference and not value.
                System.out.println(persons[i]);
            }
        }

        System.out.println(persons[4]);
        System.out.println(personsDeepCopy[4]);
    }
}
