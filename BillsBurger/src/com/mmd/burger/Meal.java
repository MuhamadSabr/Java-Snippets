package com.mmd.burger;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Meal {
    private double price = 5.0;
    private Item burger;
    private Item drink;
    private Item side;
    private double conversionRate;
    private burger topping;
    public Meal() {
        this(1);
    }

    public Meal(double conversionRate) {
        this.conversionRate = conversionRate;
        burger = new Item("regular", "burger");
        drink = new Item("coke", "drink", 1.5);
        side = new Item("fries", "side", 2.0);
        topping = new burger();
    }

    public void addToppings(String... toppings){
        for(var top : toppings){
            topping.addTopping(top);
        }

    }
    public double getTotal(){
        double total = burger.price + drink.price + side.price;
        for(var topping :  topping.toppings){
            total += topping.price;
        }
        return Item.getPrice(total, conversionRate); //U needed to call it through its class's name because u r not directly
        //in its class. If u were in its class directly(inner class) u could, as u v already done.
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%s%nTotal due: %.2f".formatted(burger, drink, side, topping, getTotal());
    }

    private class Item{
        private String name;
        private String type;
        private double price;

        public Item(String name, String type) {
            this(name, type, type.equalsIgnoreCase("burger") ? Meal.this.price : 0);
            //this(name, type, type.equalsIgnoreCase("burger") ? price : 0);
            //There are a common variable name between the class n its inner class.
            //U have to prefix this with the className.this.VarName; The error is cannot reference local price
            //before the supertype constructor has been called.
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }
        @Override
        public String toString() {
            return "%-10s%-15s$%.2f".formatted(name, type, getPrice(price, conversionRate));
        }

        private static double getPrice(double price, double rate){
            return price*rate;
        }

    }


    private class burger{

        List<Item> toppings;
        public burger(){
            toppings = new ArrayList<>();
        }
        private void addTopping(String topping){
            double price =
            switch (toppingsEnum.valueOf(topping)){
                case MUSHROOM -> toppingsEnum.MUSHROOM.getPrice();
                case SALAD -> toppingsEnum.SALAD.getPrice();
                case FRUIT -> toppingsEnum.FRUIT.getPrice();
                default -> 0;
            };
            toppings.add(new Item(topping, "topping", price));
        }

        @Override
        public String toString() {
            if(toppings==null){
                return "";
            }
            StringBuilder toppingItems= new StringBuilder();
            for (Item item : toppings) {
                toppingItems.append(item).append("\n");
            }
            return toppingItems.toString();
        }
    }

}


 enum toppingsEnum {
    MUSHROOM, SALAD, FRUIT;
    public double getPrice(){
        return switch (this){
            case MUSHROOM -> 1.5;
            case SALAD , FRUIT -> 2.0;
            default -> 0;
        };
    }
}