package com.mmd;

public class Hamburger {

    private String name;
    private String meat;
    private String breadRollType;
    private double price;

    private String addition1Name;
    private double addition1Price;

    private String addition2Name;
    private double addition2Price;

    private String addition3Name;
    private double addition3Price;

    private String addition4Name;
    private double addition4Price;

    public Hamburger(String name, String meat, double price, String breadRollType){
        this.name = name;
        this.meat = meat;
        this.price = price;
        this.breadRollType = breadRollType;
    }

    public void addHamburgerAddition1(String name, double price){
        addition1Name = name;
        addition1Price = price;
    }

    public void addHamburgerAddition2(String name, double price){
        addition2Name = name;
        addition2Price = price;
    }

    public void addHamburgerAddition3(String name, double price){
        addition3Name = name;
        addition3Price = price;
    }

    public void addHamburgerAddition4(String name, double price){
        addition4Name = name;
        addition4Price = price;
    }

    public double itemizeHamburger(){
        System.out.printf("%s %s hamburger for price : %.2f%n", name, meat, price);
        if(addition1Name!=null){
            System.out.printf("%s at price : %.2f is added%n", addition1Name, addition1Price);
        }
        if(addition2Name!=null){
            System.out.printf("%s at price : %.2f is added%n", addition2Name, addition2Price);
        }
        if(addition3Name!=null){
            System.out.printf("%s at price : %.2f is added%n", addition3Name, addition3Price);
        }
        if(addition4Name!=null){
            System.out.printf("%s at price : %.2f is added%n", addition4Name, addition4Price);
        }

        return price + addition4Price + addition3Price + addition2Price + addition1Price;

    }

}






