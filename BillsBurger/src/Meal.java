import java.security.PublicKey;
import java.util.HashMap;

public class Meal {

    private Hamburger hamburger;
    private Drink drink;
    private SideItem sideItem;
    private double price;

    public Meal(){
        hamburger = new Hamburger();
        drink = new Drink();
        sideItem = new SideItem();
    }

    public Meal(Hamburger hamburger, Drink drink, SideItem sideItem){
        this.hamburger = hamburger;
        this.drink = drink;
        this.sideItem = sideItem;
    }

    public void mealOrder(){
        if(hamburger.getType().toUpperCase().equals("DELUXE")){
            drink.setPrice(0);
        }
        price += (hamburger.getPrice() + drink.getPrice() + sideItem.getPrice());
        System.out.printf("""
                --------------------------------------------------------------------------
                %s hamburger size %s at price, this includes extra toppings : %.2f
                %s drink size %s at price : %.2f
                %s side item at price : %.2f
                --------------------------------------------------------------------------
                """, hamburger.getType(), hamburger.getSize(), hamburger.getPrice(),
                                                    drink.getType(), drink.getSize(), drink.getPrice(),
                                                        sideItem.getType(), sideItem.getPrice());
    }

    public double getPrice(){
        return price;
    }

    public void getExtraToppings(String type, double price){
        hamburger.extraToppings(type, price);
    }

}



class Hamburger{
    private String type;
    private String size;
    private double price;
    private String topping1;
    private String topping2;
    private String topping3;
    private boolean withCheese;
    private int countAddedExtraToppings;

    public Hamburger(){
        this("Regular", "Medium",5.5);
        topping1 = "Tomato";
        topping2 = "Pickles";
        topping3 = "Lettuce";
        withCheese = false;
        countAddedExtraToppings = 0;
    }

    public Hamburger(String type, String size, double price){
        this(type, size, price, false);
        countAddedExtraToppings = 0;
    }

    public Hamburger(String type, String size, double price, boolean withCheese){
        this.type = type;
        this.size = size;
        this.price = withCheese ? price+1.5 : price;
        countAddedExtraToppings = 0;
    }

    public void extraToppings(String extraTopping, double price){
        countAddedExtraToppings++;
        if(type.toUpperCase().equals("DELUXE") && countAddedExtraToppings==1){
            System.out.printf("With Deluxe bonus you can add up to 5 extra toppings you want for free%n");
        }
        if(countAddedExtraToppings<=5) {
            price = 0;
            System.out.printf("%s is added to your order at a price of %.2f%n", extraTopping, price);
            this.price += price;
        }
        else {
            System.out.printf("%s is added to your order at a price of %.2f%n", extraTopping, price);
            this.price += price;
        }
    }

    public double getPrice(){
        return price;
    }

    public String getType() {
        return type;
    }

    public String getSize(){
        return size;
    }

}

class Drink{

    private String type;
    private String size;
    private double price;

    public Drink(){
        type = "Coke";
        price = 1.0;
    }
    public Drink(String type,String size, double price){
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class SideItem{
    private String type;
    private double price;
    public SideItem(){
        type = "Fries";
        price = 2.5;
    }
    public SideItem(String type, double price){
        this.type = type;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public String getType(){
        return type;
    }

}