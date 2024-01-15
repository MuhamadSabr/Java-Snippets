package com.mmd.Bank;

import com.mmd.dev.dto.Transaction;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class BankAccount {
    private final AccountType type;
    private double balance;
    private final String accountNumber;
    private static int lastNumber = 1;
    private final Map<Long, Transaction> transactions;


    BankAccount(AccountType type, double balance){
        transactions = new LinkedHashMap<>();
        this.type = type;
        this.balance = balance <0 ? 0 : balance;
        accountNumber = ("00" + lastNumber) + (int)balance;
        lastNumber++;
    }

    public boolean commitTransaction(int routingNumber, long transactionId, String customerId, double amount){
        if(amount<0 && (balance+amount) <0.0) return false;
//        Transaction trx = new Transaction(routingNumber, Integer.parseInt(customerId), transactionId, amount);
        balance+= amount;
        transactions.put(transactionId, new Transaction(routingNumber, Integer.parseInt(customerId), transactionId, amount));
        return true;
    }


    public Map<Long, String> getTransactions() {//Do not return reference to your class fields at all.
        Map<Long, String> tempMap = new HashMap<>();
        transactions.forEach((k, v) -> tempMap.put(k, v.toString()));
        return tempMap;
    }

    public AccountType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "%-10s : %-16s %.2f$".formatted( type, accountNumber, balance);
    }


    public enum AccountType{
        CHECKING, SAVING, CURRENT
    }



}
