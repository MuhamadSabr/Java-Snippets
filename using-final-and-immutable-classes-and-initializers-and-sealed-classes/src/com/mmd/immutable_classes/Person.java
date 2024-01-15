package com.mmd.immutable_classes;

import java.util.Arrays;

public class Person {
    private String name;
    private String dob;
    private Person[] kids;

    public Person(String name, String dob, Person[] kids) {//String is immutable, but Arrays r not, so use defensive copy
        this.name = name;
        this.dob = dob;
//        if(kids!=null) {
//            this.kids = new Person[kids.length];
//            Arrays.setAll(this.kids, i -> kids[i]);
//        }else this.kids = null;

        this.kids = kids==null ? null : kids.clone();

    }
    public Person(String name, String dob) {
        this(name, dob, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Person[] getKids() { //String is immutable but Arrays are not, so use defensive copy
        return kids.clone();
    }

    public void setKids(Person[] kids) {
        this.kids = kids;
    }

    @Override
    public String toString() {
        return "%s %s , kids : %s".formatted(name, dob, Arrays.toString(kids));
    }
}
