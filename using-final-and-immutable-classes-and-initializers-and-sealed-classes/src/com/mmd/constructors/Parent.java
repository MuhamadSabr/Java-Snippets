package com.mmd.constructors;

public class Parent {

    {//This is an instance initializer.
//        name = "Mohammed";
//        dob = "10-9-1998";
        System.out.println("In parent initializer");
    }

    public Parent(){this(null,null, 5);}

    public Parent(String name, String dob, int siblings) {
        this.name = name;
        this.dob = dob;
        this.siblings = siblings;
        System.out.println("In parent constructor");
    }

    final private String name;

    static {
        System.out.println("Static initializer is called only once(not for class's instances, class construction");
    }
    final private String dob;
    final protected int siblings;

    public String getName() {
        return name;
    }


    public String getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return name + ": " + dob;
    }
}
