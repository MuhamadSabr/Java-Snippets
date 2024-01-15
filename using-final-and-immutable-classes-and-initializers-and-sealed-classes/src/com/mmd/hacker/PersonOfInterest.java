package com.mmd.hacker;

import com.mmd.immutable_classes.PersonImmutable;

public class PersonOfInterest extends PersonImmutable {

    public PersonOfInterest(PersonImmutable person) {
        super(person.getName(), person.getDob(), person.getKids());
    }


    @Override
    public PersonImmutable[] getKids() {
//        return super.kids;
        return super.kids;
    }
}
