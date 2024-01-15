package com.mmd;

import java.util.*;

public class Branch {

    private String name;
    private ArrayList<Customer> customers;

    public Branch(String name){
        this.name = name;
        customers = new ArrayList<>();
    }

    public String getName(){
        return name;
    }
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    public boolean newCustomer(String name, double initialTransaction){
        if( findCustomer(name) !=null ){
            return false;
        }
        return customers.add(new Customer(name, initialTransaction) );
    }
    private Customer findCustomer(String name){
        for(var element : customers){
            if(element.getName().equalsIgnoreCase(name) ){
                return element;
            }
        }
        return null;
    }

    public boolean addCustomerTransaction(String name, double transaction){
        Customer customer = findCustomer(name) ;
        if ( customer == null ){
            return false;
        }
        customer.addTransaction(transaction);
        return true;
    }


}