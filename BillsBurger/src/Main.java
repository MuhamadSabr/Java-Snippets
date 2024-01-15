public class Main {
    public static void main(String[] args)
    {

        Meal meal1 = new Meal();
        meal1.mealOrder();
        System.out.printf("Regular order Meal1's price is %.2f%n", meal1.getPrice());
        System.out.println("--------------------------------------------------------------------------");

        Meal meal2 = new Meal(new Hamburger("Deluxe","Large", 10, true), new Drink(), new SideItem());
        meal2.getExtraToppings("Mashroum",1);
        meal2.getExtraToppings("Chicken pieces", 1.5);
        meal2.getExtraToppings("Mashroum",1);
        meal2.getExtraToppings("Turkey pieces", 1.5);
        meal2.getExtraToppings("Souce",1);
        meal2.getExtraToppings("Duck pieces", 1.5);
        meal2.mealOrder();
        System.out.printf("Meal2's price is %.2f%n", meal2.getPrice());
        System.out.println("--------------------------------------------------------------------------");
        Hamburger hamburger = new Hamburger();
        //hamburger.extraToppings("Souce", 1.0);
        //hamburger.extraToppings("Mashroum",1.0);
        System.out.printf("Price of your hamburger is %.2f%n", hamburger.getPrice());

        SideItem sideItem = new SideItem();
        System.out.printf("Price of your side item is : %.2f%n", sideItem.getPrice());

        Drink drink = new Drink();
        System.out.printf("Price of your drink is : %.2f%n", drink.getPrice());

    }
}