package com.mmd.burger;

public class Store {
    public static void main(String[] args) {
        Meal meal = new Meal();
        System.out.println(meal);

        System.out.println("-".repeat(30));

        Meal usMeal= new Meal(.65);
        System.out.println(usMeal);

        System.out.println("After adding toppings");
        meal.addToppings(toppingsEnum.MUSHROOM.toString(), toppingsEnum.SALAD.toString(), toppingsEnum.FRUIT.toString());
        System.out.println(meal);
        System.out.println("-".repeat(30));
        usMeal.addToppings(toppingsEnum.SALAD.toString());
        System.out.println(usMeal);
        System.out.println();
    }
}
