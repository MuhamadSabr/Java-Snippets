package com.mmd.immutable_classes;

import java.util.Arrays;

public class PersonImmutable {
    private final String name; //First step towards making an object immutable is making the instance fields private n final
    private final String dob;
    protected PersonImmutable[] kids;


    public PersonImmutable(String name, String dob, PersonImmutable[] kids) {//String is immutable, but Arrays r not, so use defensive copy
        this.name = name;//Second is using constructor or factory methods to set Data.
        this.dob = dob;
        this.kids = kids==null ? null : kids.clone(); // Another thing is making copies of mutable reference data.

    }
    public PersonImmutable(String name, String dob) {
        this(name, dob, null);
    }

    //Not having any setter is one more step that has to be taking towards having an immutable object
    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public PersonImmutable[] getKids() { //String is immutable but Arrays are not, so use defensive copy
        return kids.clone();
    } //Creating defensive copies in getters(copies of mutable reference data).


    @Override
    public String toString() {
        return "%s %s , kids : %s".formatted(name, dob, Arrays.toString(kids));
    }
}
