package com.mmd;


//Both records n enums can implement interfaces, though a recod usually don't have a body. But they can't extend classes whether abstract or not.
enum FlightStages implements Trackable{
    GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;
    @Override
    public void track() {
        if(this != GROUNDED){
            System.out.println("Monitoring " + this);
        }
    }
    public FlightStages getNextStage(){
        FlightStages[] allStages = values();// U can use values() without referring to enum type name because u r on the enum itself.
        return allStages[ordinal()+1 % allStages.length]; //In case the next position is equal to the length it will return the first element position 0.
    }

}
record DragonFly(String name, String type) implements FlightEnabled {
    @Override
    public void takeOff() {}
    @Override
    public void land() {}
    @Override
    public void fly() {}
}
class Satellite implements OrbitEarth{
    public void achieveOrbit(){    //Remember even though the public modifier is not needed on the interface, on the class it is required.
        //Interfaces have default public modifier. Classes have default package-private. and U know when overriding a method with weaker access modifier is not allowed.
        System.out.println("Achieving Orbit");
    }
    @Override
    public void takeOff() {

    }
    @Override
    public void land() {

    }
    @Override
    public void fly() {

    }
}

interface OrbitEarth extends FlightEnabled{
    void achieveOrbit();
    static void log(String description){    //static methods allow u to include helper methods on the interface type itself
        var today = new java.util.Date();
        System.out.println(today + " : " + description);
    }

    private static void a(){
        System.out.println("A"); //non-static methods cannot be referenced on a static method.
    }
    static void b(){
        a();
    }
    default void c(){
        b();
    }
}

interface FlightEnabled{
    double MILE_TO_KM = 1.60934; //Any field declared on an interface is static, public n final;
    double KM_TO_MILE =0.621371;
    public abstract void takeOff();   // U can specify abstract keyword but it is not necessary. In interfaces abstract is implicitly deduced. When u don't include a method body.
    public void land();             //Unlike classes, interfaces do not have access modifiers for methods. By default, all methods in an interface are public, and you don't need to explicitly specify the public keyword.
    public void fly();
    //protected void sweep();       Modifier protected is not allowed here.
    //private  void sweep();        private methods in an interface must have method body.
    //static void transition(FlightEnabled stage);    //static methods in interfaces must have a body. The same thing as private methods.
    default FlightStages transition(FlightStages stage){
        //System.out.println("transition not implemented on " //This is a common use, where u throw an error or a message letting people know it is not been implemented
                //+ getClass().getName());                    // on the class whose object calls this method. Note u did not need to call getClass using this.
        //return null;                                        //This is because it is implicit in non-static methods, including this default method on an interface.
        FlightStages nextStage = stage.getNextStage();
        System.out.println("Transitioning from stage " + stage + " to " + nextStage);
        return nextStage;
    }
}
interface Trackable{
    void track();
}

public abstract class Animal {
    public abstract void move();   // The abstract keyword for abstract classes r required. U cannot just end the parenthesis with a semi-colon. Though u can do this with interface.
    public void eat(){
        System.out.println("Animal eats");
    }
}
class Bird extends Animal implements FlightEnabled, Trackable{
    @Override
    public void move() {
        System.out.println("Splashes wings");
    }
    public void eat(){
        System.out.println("Bird eats");
    }
    @Override
    public void takeOff(){
        System.out.println(getClass().getSimpleName() + " is taking off" );
    }
    @Override
    public void fly(){
        System.out.println(getClass().getSimpleName() + " is flying" );
    }
    @Override
    public void land(){
        System.out.println(getClass().getSimpleName() + " is landing" );
    }
    @Override
    public void track(){
        System.out.println(getClass().getSimpleName() + "'s coordinates are being tracked" );
    }
    @Override
    public FlightStages transition(FlightStages stage) {
        System.out.println(getClass().getName() + " is transitioning");
        return FlightEnabled.super.transition(stage);  // U cannot just use super without prefixing it with interface.super since it is not a superclass
                                                        //Every time u call super u have to qualify it with the interfaceType.
    }
}