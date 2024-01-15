package com.mmd.Bank;

import com.mmd.Bank.BankAccount;
import com.mmd.Bank.BankCustomer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Bank {
    final public int routingId;
    private static long lastTransactionId = 1_000_000_000;
    private Map<String, BankCustomer> customers;

    public Bank(int routingId) {
        this.routingId = routingId;
        customers = new HashMap<>();
    }


    public BankCustomer getCustomer(String customerId){
        return customers.get(customerId);
    }

    public void addCustomer(BankCustomer customer){
        customers.putIfAbsent(customer.getCustomerID(), customer);
    }

    public boolean doTransaction(String id, BankAccount.AccountType accountType, double amount){
        BankCustomer customer = getCustomer(id);
        if(customer==null){
            return false;
        }
        customer.getAccount(accountType).commitTransaction(routingId, lastTransactionId++, id, amount);
        return true;
    }



}
