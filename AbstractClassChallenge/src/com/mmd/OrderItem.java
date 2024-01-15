package com.mmd;
public class OrderItem {
    private final int quantity;
    private final ProductForSale product;
    public OrderItem(int quantity, ProductForSale product){
        this.quantity= quantity;
        this.product = product;
    }

    public ProductForSale getProduct(){
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
