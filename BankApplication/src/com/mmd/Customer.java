package com.mmd;


import java.util.*;

public class Customer {

    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name, double initialTransaction){
        this.name = name;
        transactions = new ArrayList<>();
        transactions.add(initialTransaction); // Remember calling methods in the constructor is not a good practice.
    }

    public String getName(){
        return name;
    }

    public ArrayList<Double> getTransactions(){
        return transactions;
    }

    public void addTransaction(double transaction){
        transactions.add(transaction);
    }

}