package com.mmd.immutable_classes;

import com.mmd.hacker.PersonOfInterest;

import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.Arrays;

public class MainImmutable {
    public static void main(String[] args) {
        PersonImmutable[] johnKids = {new PersonImmutable("Didar", "1999-4-8"), new PersonImmutable("Karim", "2001-03-04"),
                new PersonImmutable("Shafiq", "2004-4-7")};
        PersonImmutable john = new PersonImmutable("John", "1980-4-1", johnKids);

        LivingPerson livingJohn = new LivingPerson(john.getName(), john.getKids());
        System.out.println(john);
        System.out.println(livingJohn);

        LivingPerson anne = new LivingPerson("Ann", null);
        livingJohn.addKids(anne);
        System.out.println(livingJohn);

        PersonOfInterest johnCopy = new PersonOfInterest(john);
        System.out.println(johnCopy);

        johnKids= johnCopy.getKids();
        johnKids[0]=null;
        System.out.println(johnCopy);
        System.out.println(john);

//        johnKids[0] = null;
//        johnKids[1] = null;
//        System.out.println(Arrays.toString(johnKids));
//        System.out.println(john);
//        System.out.println("-".repeat(50));

//        johnKids= john.kids();
//        johnKids[0] = null;
//        johnKids[2] = null;
//        System.out.println(Arrays.toString(johnKids));
//        System.out.println(john);
    }
}
