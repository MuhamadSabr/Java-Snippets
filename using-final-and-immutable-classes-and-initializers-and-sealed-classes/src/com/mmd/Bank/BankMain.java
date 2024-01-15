package com.mmd.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankMain {
    public static void main(String[] args) {

        BankAccount mohammedSavingAcc = new BankAccount(BankAccount.AccountType.SAVING, 50.0);
        BankAccount mohammedCurrentAcc= new BankAccount(BankAccount.AccountType.CURRENT, 50.0);
        List<BankAccount> mohammedAccounts = new ArrayList<>(List.of(mohammedCurrentAcc, mohammedSavingAcc));
        BankCustomer mohammed = new BankCustomer("Mohammed Saber Othman", mohammedAccounts);

        Bank mmd = new Bank(123);
//        System.out.println(mohammed);
        mmd.addCustomer(mohammed);
        mohammedAccounts.add(new BankAccount(BankAccount.AccountType.CHECKING, 1_000_000));

        BankCustomer mohamad =  mmd.getCustomer("000001000000000");
        mmd.doTransaction("000001000000000", BankAccount.AccountType.SAVING, 50.0);
        mmd.doTransaction("000001000000000", BankAccount.AccountType.SAVING, -100.0);
//        mohamad.getAccount(BankAccount.AccountType.SAVING).getTransactions().forEach((k, v)-> v.setTransactionAmount(0));

        mmd.getCustomer("000001000000000").getAccount(BankAccount.AccountType.SAVING).getTransactions().forEach((k, v)-> System.out.println(k+ " : " + v ));
        System.out.println(mohammed.getAccounts());




        BankAccount ahmedSavingAcc = new BankAccount(BankAccount.AccountType.SAVING, 0.0);
        BankAccount ahmedCurrentAcc= new BankAccount(BankAccount.AccountType.CURRENT, 50.0);
        BankAccount ahmedCheckingAcc=new BankAccount(BankAccount.AccountType.CHECKING, 0.0);
        List<BankAccount> ahmedAccounts = new ArrayList<>(List.of(ahmedCheckingAcc, ahmedCurrentAcc, ahmedSavingAcc));
        BankCustomer ahmed = new BankCustomer("Ahmed Babakr Ja3far", ahmedAccounts);

        mohammedCurrentAcc = new BankAccount(BankAccount.AccountType.CURRENT, 1_000_000.0);

        List<BankCustomer> bankCustomers = new ArrayList<>(List.of(mohammed, ahmed));

//        System.out.println(mohammed);
//        System.out.println(mohammed);

//        bankCustomers.forEach(System.out::println);
    }
}
