package com.mmd;

abstract class  Mammal extends Animal{  //class is followed by className always
    public Mammal(String type, Size size, double weight) {  //Notice u r not required to implement the parent's abstract methods.
        super(type, size, weight);
    }
    public void move(String speed){
        System.out.println(getExplicitType() + " moves " + speed);
    }
    public abstract void shedsHair();
}

abstract public class Animal {
    protected String type;
    private Size size;
    private double weight;

    public Animal(String type, Size size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public abstract void move(String speed);
    public abstract void makeNoise();

    public final String getExplicitType(){
        return getClass().getSimpleName() + " (" + type + ")";
    }
    final public Size getSize(){
        return size;
    }
}
