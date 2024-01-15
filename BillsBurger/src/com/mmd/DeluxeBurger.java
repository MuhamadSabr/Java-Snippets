package com.mmd;

public class DeluxeBurger extends Hamburger{

    public DeluxeBurger(){
        super("Deluxe", "Beef", 19.10, "Regular");
        super.addHamburgerAddition1("Chips",0);
        super.addHamburgerAddition2("Drinks",0);
    }

    public void addHamburgerAddition1(String name, double price){
        System.out.println("No additional items can be added to a deluxe burger");
    }

    public void addHamburgerAddition2(String name, double price){
        System.out.println("No additional items can be added to a deluxe burger");
    }

    public void addHamburgerAddition3(String name, double price){
        System.out.println("No additional items can be added to a deluxe burger");
    }

    public void addHamburgerAddition4(String name, double price){
        System.out.println("No additional items can be added to a deluxe burger");
    }

}