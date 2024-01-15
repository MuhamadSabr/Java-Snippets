package com.mmd;

public enum Topping {
    TOMOTO,
    MASHROUM,
    PICKLES,
    PEPER,
    ONION;    //The ; is necessary if u r going to include code in the enum.
    private double price;
    public double getPrice(){    //As u can see an enum is much more than just a set of constants. It is a special collection class n can include constructors n fields, n methods.
        return switch (this){   //We pass this instance of the enum.
            case PICKLES -> {yield 0.5;}
            case MASHROUM -> {yield 1.5;}
            default -> {yield 0.0;}
        };
    }
}
