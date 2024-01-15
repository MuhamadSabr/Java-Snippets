package com.mmd.immutable_classes;

import java.util.Arrays;

public class LivingPerson extends PersonImmutable{

    public LivingPerson(String name, PersonImmutable[] kids) {
        super(name, null, kids==null ? new PersonImmutable[10] : Arrays.copyOf(kids, 10));
    }

    public LivingPerson(PersonImmutable person){
        this(person.getName(), person.getKids());
    }

    @Override
    public String getDob() {
        return null;
    }

    public void addKids(PersonImmutable kid){
        for(int i =0; i<kids.length; i++){
            if(kids[i]==null){
                kids[i] = new LivingPerson(kid);
                break;
            }
        }
    }
}
