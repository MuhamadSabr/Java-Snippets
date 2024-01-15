package com.mmd;

import java.util.Iterator;

public class Iron extends ProductForSale implements Iterator {
    public Iron(String type, double price, int quantity, String description) {
        super(type, price, quantity, description);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}
