package com.mmd;

public class Main {
    public static void main(String[] args) {
        Bird bird = new Bird();
        Animal animal = new Bird();   //This is upcasting n u can explicitly specify Animal animal = (Animal) new Bird();
        //Bird downCasting = (Bird) new Animal(); //Downcasting
        FlightEnabled flier = new Bird();
        Trackable tracked = new Bird();
        bird.eat();
        bird.move();
        bird.takeOff();
        flier.fly();
        tracked.track();
        bird.land();
        System.out.println("-".repeat(40));
        animal.eat();      // Even a type of the abstract class that has the abstract method on it can call it.
        animal.move();
        System.out.println("-".repeat(40));
        inFlight(flier);
        System.out.println("-".repeat(40));
        /*
        animal.land();    // Again none of the interface methods exist on Animal so u can not use Animal variable to call these methods.
        flier.move();    //Method move is not on the type of  the variable
        tracked.move();  //Method move is not on the type of this object.
        */
        inFlight(flier);
        OrbitEarth.log("Starting " + new Satellite().toString());
        //new Satellite().b();  //static methods may be invoked on containing interface class only
        //Satellite.b();          //The classes implementing the interface cannot invoke the static methods on the interface.

    }

    public static void inFlight(FlightEnabled flier){
        flier.transition(FlightStages.GROUNDED);
        flier.takeOff();
        flier.transition(FlightStages.LAUNCH);
        flier.fly();
        if(flier instanceof Trackable){     //Note right operand is an interface not a class. If flier is an object of a class that implements Trackable it returns true.
            ( (Trackable)flier ) .track();  //Only methods on the interface can be called because the type of the reference again is that.
        }
        flier.land();
    }

}