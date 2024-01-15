package com.mmd;

public abstract class ProductForSale {
    private String type;
    private double price;
    protected String description;
    private final int quantity;

    public ProductForSale(String type, double price, int quantity, String description){
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }


    public void pricedLineItem(){
        System.out.printf("Item %s, Quantity %d, price %.2f%n", type, quantity, price);
    }
    public double salesPrice(){
        return quantity * price;
    }

    public void showDetails(){
        System.out.printf("Product %s, Quantity %d, Price %.2f, description%s%n", type, quantity, price, description);
    }

}
