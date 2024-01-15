package com.mmd;

import java.util.*;

public class Bank {

    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name){
        this.name = name;
        branches = new ArrayList<>();
    }

    public boolean addBranch(String name){
        Branch branch = findBranch(name);
        if ( branch != null ){
            return false;
        }
        return branches.add(new Branch(name));
    }

    public boolean addCustomer(String branchName, String customerName, double initialTransaction){  //U have to make sure the branch exists then
        Branch branch = findBranch(branchName);     //U v to check if u have that customer in any of your branches.
        if(branch==null){
            return false;
        }
        // Customer customer = branch.findCustomer(customerName);
        //if(customer!=null){
        //return false;
        //}
        return branch.newCustomer(customerName, initialTransaction);
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double transaction){
        Branch branch = findBranch(branchName);
        if(branch==null){
            return false;
        }
        //Customer customer = branch.findCustomer(customerName);
        //if(customer!=null){
        // return false;
        //}
        return branch.addCustomerTransaction(customerName, transaction);
    }

    private Branch findBranch(String name){
        for( var element : branches){
            if ( element.getName().equalsIgnoreCase(name) ){
                return element;
            }
        }
        return null;
    }

    public boolean listCustomers(String branchName, boolean printTransactions){
        Branch branch = findBranch(branchName);
        if(branch==null){
            return false;
        }
        int counter2 = 1;
        System.out.println("Customer details for branch " + branchName);
        if(printTransactions){
            for( Customer customer : branch.getCustomers() ){
                int counter = 1;
                System.out.println("Customer: " + customer.getName() +"["+ counter2++ +"]");
                System.out.println("Transactions");
                for( double trx : customer.getTransactions()){
                    System.out.println("[" + counter++ + "] Amount " + trx);
                }
            }
        }
        if(!printTransactions){
            for( var customer : branch.getCustomers() ){
                System.out.println("Customer: " + customer.getName()+ "[" + counter2++ + "]");
            }
        }
        return true;
    }

}