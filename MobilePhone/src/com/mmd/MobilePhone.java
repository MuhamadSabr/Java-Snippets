package com.mmd;

import java.util.ArrayList;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber){
        this.myNumber = myNumber;
        myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact contact){
        if( findContact(contact) == -1 ){
            myContacts.add(contact);
            return true;
        }
        return false;

    }

    public boolean updateContact(Contact oldContact, Contact newContact){
        if( findContact(oldContact) != -1 ){
            myContacts.set( myContacts.indexOf(oldContact), newContact );
            return true;
        }
        return false;
    }

    public boolean removeContact(Contact contact){
        return (myContacts.remove(contact) );
    }

    private int findContact(Contact contact){
        return findContact(contact.getName() );
    }

    private int findContact(String name ){
        for(var element : myContacts){
            if(element.getName() == name ){
                int index = myContacts.indexOf(element);
                return index;
            }
        }
        return -1;
    }

    public Contact queryContact(String name){
        if( findContact(name) ==-1 ){
            return null;
        }
        return ( myContacts.get(findContact(name) ) );
    }

    public void printContacts(){
        System.out.println("Contact List:");
        for(int i=0; i< myContacts.size(); i++){
            System.out.printf("%d. %s -> %s%n", i+1, myContacts.get(i).getName(), myContacts.get(i).getPhoneNumber());
        }
    }

}
