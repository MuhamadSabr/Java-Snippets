package com.mmd.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankCustomer {
    private final String customerName;

    private final int customerID;

    private static int lastId = 1_000_000_000;

    private List<BankAccount> accounts;

    BankCustomer(String customerName, List<BankAccount> accounts){
        this.customerName = customerName;
        this.customerID   = lastId++;
        this.accounts = new ArrayList<>();
        this.accounts = accounts!=null  ? List.copyOf(accounts) : null;
    }

    public BankCustomer(BankCustomer customer){
        this(customer.customerName, customer.accounts);
    }


    public BankAccount getAccount(BankAccount.AccountType accountType){
        for(BankAccount account : accounts){
            if(account.getType() == accountType){
                return account;
            }
        }
        return null;
    }


    public String getCustomerName() {
        return customerName;
    }

    public List<BankAccount> getAccounts() {
        return List.copyOf(accounts);
    }

    public String getCustomerID() {
        return "%015d".formatted(customerID);
    }

    @Override
    public String toString() {
        return "%-15s,  %-25s : %s".formatted("00000"+customerID, customerName, accounts);
    }
}
