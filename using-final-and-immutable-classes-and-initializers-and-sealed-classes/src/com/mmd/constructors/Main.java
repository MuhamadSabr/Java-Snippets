package com.mmd.constructors;

import external.Child;

public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent("Mohammed", "10-09-1998", 4);
        System.out.println(parent);
        System.out.println("-".repeat(50));
        Child child = new Child();
        System.out.println(child);
        System.out.println("-".repeat(50));

        Person person = new Person("Mohammed Saber Othman", "10-09-1998");
        System.out.println(person);
        Person personCopy = new Person(person);
        System.out.println(personCopy);
        System.out.println("-".repeat(50));

        Generations g;// declaring a variable of the enum does not call the constructor.
        g = Generations.GEN_Z; // But when you assign one of the constants of the enum it calls the constructor for all the enum's constants
                                        // and not just the constant you assigned to the variable.
//        Generations gg = new Generations();// Enum types cannot be instantiated.
//        Generations gg = Generations.GEN_Z(2003, 2021); This is also not allowed unless you have a static method with the same name.

        System.out.println(g);

    }
}
