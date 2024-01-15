package com.mmd;

public class Horse extends Mammal{
    public Horse(String type, Size size, double weight) {
        super(type, size, weight);
    }
    @Override
    public void makeNoise() {
        System.out.println(getExplicitType() + " neighs");
    }
    public void shedsHair(){
        System.out.println(getExplicitType() + " sheds in the Spring");
    }

}
