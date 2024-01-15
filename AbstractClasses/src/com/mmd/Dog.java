package com.mmd;

public class Dog extends Animal{


    public Dog(String type, Size size, double weight) {
        super(type, size, weight);
    }
    public void move(String speed) {  //U will get abstract method in non abstract class error. if u specify abstract modifier.
        if(speed.equalsIgnoreCase("slow")){
            System.out.println(getExplicitType() + " is walking");
        }else{
            System.out.println(getExplicitType() + " is running");
        }
    }
        public void makeNoise(){
        if(type.equalsIgnoreCase("wolf")) {
            System.out.println(getSize().name() +" " + getExplicitType() + " goes howling");
        }else{
            System.out.println(getSize().name() +" " + getExplicitType() + " goes bark bark");
        }
    }
//    public Size getSize(){   This method is defined with the modifier final, so cannot be overridden by subclasses. Can only be used.
//        return Size.SMALL;
//    }
}
