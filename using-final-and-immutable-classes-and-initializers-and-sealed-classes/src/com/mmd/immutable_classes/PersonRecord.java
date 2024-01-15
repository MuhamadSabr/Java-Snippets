package com.mmd.immutable_classes;

import java.util.Arrays;

public record PersonRecord(String name, String dob, PersonRecord[] kids) {

    public PersonRecord(String name, String dob) {
        this(name, dob, new PersonRecord[20]);
    }

    @Override
    public PersonRecord[] kids() {
        return Arrays.copyOf(kids, kids.length);
    }

    @Override
    public String toString() {
        String kidsString = "n/a";
        for(int i = 0; i<kids.length; i++){
            if(kids[i]!=null)
            kidsString = kidsString + kids[i];
        }
        return "%s %s , kids : %s".formatted(name, dob, kidsString);
    }
}
