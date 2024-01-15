package com.mmd;


public class HealthyBurger extends Hamburger{

    private String healthyExtra1Name;
    private double healthyExtra1Price;
    private String healthyExtra2Name;
    private double healthyExtra2Price;

    public HealthyBurger(String meat, double price){
        super("Healthy Burger", meat, price, "Regular");
    }

    public void addHealthyAddition1(String name, double price){
        super.addHamburgerAddition1(name, price);
        //healthyExtra1Name = name;
        //healthyExtra1Price = price;
    }

    public void addHealthyAddition2(String name, double price){
        super.addHamburgerAddition2(name, price);
        //healthyExtra2Name = name;
        //healthyExtra2Price = price;
    }

    public double itemizeHamburger(){
        return super.itemizeHamburger();

    }

}