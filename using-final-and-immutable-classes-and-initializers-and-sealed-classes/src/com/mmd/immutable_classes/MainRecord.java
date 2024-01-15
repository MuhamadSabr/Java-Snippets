package com.mmd.immutable_classes;

import java.util.Arrays;

public class MainRecord{
    public static void main(String[] args) {
        PersonRecord[] johnKids = {new PersonRecord("Didar", "1999-4-8"), new PersonRecord("Karim", "2001-03-04"),
                new PersonRecord("Shafiq", "2004-4-7")};
        PersonRecord john = new PersonRecord("John", "1980-4-1", johnKids);
//        johnKids[0] = null;
//        johnKids[1] = null;
//        System.out.println(Arrays.toString(johnKids));
//        System.out.println(john);
//        System.out.println("-".repeat(50));

        johnKids= john.kids();
        johnKids[0] = null;
        johnKids[2] = null;
        System.out.println(Arrays.toString(johnKids));
        System.out.println(john);
    }
}
