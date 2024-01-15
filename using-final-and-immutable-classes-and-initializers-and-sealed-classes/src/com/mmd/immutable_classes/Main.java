package com.mmd.immutable_classes;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Person[] johnKids = {new Person("Didar", "1999-4-8"), new Person("Karim", "2001-03-04"),
        new Person("Shafiq", "2004-4-7")};
        Person john = new Person("John", "1980-4-1", johnKids);
        johnKids[0] = null;
        johnKids[1] = null;
        System.out.println(Arrays.toString(johnKids));
        System.out.println(john);
        System.out.println("-".repeat(50));

        johnKids= john.getKids();
        johnKids[0] = null;
        johnKids[1] = null;
        System.out.println(john);
    }
}
