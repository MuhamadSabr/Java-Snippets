package com.mmd;

public class Fish extends Animal{


    public Fish(String type, Size size, double weight) {
        super(type, size, weight);
    }
    public void move(String speed) {  //U will get abstract method in non abstract class error. if u specify abstract modifier.
        if(speed.equalsIgnoreCase("slow")){
            System.out.println(getExplicitType() + " is swimming");
        }else{
            System.out.println(getExplicitType() + " is hiding frantically");
        }
    }
        public void makeNoise(){
        if(type.equalsIgnoreCase("gold fish")) {
            System.out.println(getExplicitType() + " is squashing");
        }else{
            System.out.println(getExplicitType() + " is splashing");
        }
    }

}
