package com.mmd.constructors;

public record Person(String name, String dob) {//This is all you need to have an operational class. These fields are final.
                                        //A constructor is inserted by the compiler when it generates the byte code or class file.
                                        //It takes the same arguments in the same order defined in the record header declaration.
                                        //This constructor is called a Canonical constructor.


//    public Person(String name, String dob) { //For explicitly declaring a canonical u might wan to do some transform before initialization.
//        this.name = name;
//        this.dob = dob.replaceAll("-", "/");
////        this.dob = dob.trim(); //Again fields are final so u could not reassign teh components of the record.
//    }When u declare an explicit canonical constructor u can't create another constructor.
//    U can't also create a Compact constructor since it is intertwined with the implicit canonical constructor


    public Person {//This is the compact. This code gets inserted into the implicit canonical constructor before any final fields r assigned
        if(dob==null) throw new IllegalArgumentException();
        dob = dob.replaceAll("-", "/");//U can only work with the method arguments n not the this.final fields at all
//        this.dob = dob.replaceAll("-", "/"); //Can not assign a value to final variable dob.
    }//The advantage of using this is u can just focus on the custom part n leave the boilerplate code to the compiler

    public Person(Person person) {//Non-canonical constructor must delegate to another constructor.
        this(person.name, person.dob);
    }

    //    {
//        this.dob = dob;
//    }//Instance initializers aren't allowed in records. The only place to make assignments is in the canonical constructor.

    //The toString is another implicitly generated method on records.
}
