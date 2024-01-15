package com.mmd;

import java.util.ArrayList;

public class Store {
    //orders.add(1, new Helmet("Plastic", 10.99, 10, "10 construction helmets"));

    public static void main(String[] args) {
        ArrayList<OrderItem> orders = new ArrayList<>();
        orders.add(new OrderItem(10, new Helmet("Z10 construction helmet",10.99, 10, "10 construction z10 helmets ")));

        for(OrderItem order : orders){
            ProductsSold(order.getProduct());
        }
    }

    public static void ProductsSold(ProductForSale product){
        product.pricedLineItem();
        System.out.println( product.salesPrice() +" is total price");
        product.showDetails();
    }
}