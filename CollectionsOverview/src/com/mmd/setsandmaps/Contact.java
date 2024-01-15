package com.mmd.setsandmaps;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Contact {
    private String name;
    private Set<String> emails = new HashSet<>();// These are hashsets because we don't want duplicate emails n phones.
    private Set<String> phones = new HashSet<>();

    public Contact(String name){
        this(name, null);
    }

    public Contact(String name, String emails){
        this(name, emails, 0);
    }

    public Contact(String name, long phone){
        this(name, null, phone);
    }
    public Contact(String name, String email, long phone){
        this.name = name;
        if(email!=null){
            emails.add(email);
        }
        if(phone>0){
            String p = String.valueOf(phone);
            phones.add(
                    "(%s) %s-%s".formatted(p.substring(0,3), p.substring(3,6), p.substring(6))
            );
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%s: %s %s".formatted(name, emails, phones);
    }

    public Contact mergeContactData(Contact contact){
        Contact tempContact = new Contact(this.name);// Creates a temp Contact based on the instance that calls this method.
        tempContact.phones = this.phones;
        tempContact.emails = this.emails;// Until here we are just copying the instance's Contact data after this we merge it
        tempContact.emails.addAll(contact.emails);  // with the Contact that is passed to this method.
        tempContact.phones.addAll(contact.phones);

        return tempContact;
    }

    public String lastFirstName(){
        return name.substring(name.indexOf(" ")+1) + ", " +
                name.substring(0, name.indexOf(" "));
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true; //If they both have the same reference(pointing to the same object) then they are equal.
//        if (o == null || getClass() != o.getClass()) return false;
//        Contact contact = (Contact) o; //At this point we know by using the getClass() that it is the same as this instance type. so it is safe to cast the object to this type
//        return Objects.equals(getName(), contact.getName()) && Objects.equals(emails, contact.emails) && Objects.equals(phones, contact.phones);
//        //Objects.equals is null safe equality check.
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //Checking if both reference are the same.
        if (o == null || getClass() != o.getClass()) return false; // if the element is null or its class does not match this then return false.

        Contact contact = (Contact) o;  //At this point the class is the same so u can cast it to this type.

        return getName().equals(contact.getName()); // getName() is called directly instead of Objects.equals because it is certain not to be null.
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    public boolean addEmail(String companyName){
        String[] names = name.split(" ");
        String email = "%c%s@%s.com".formatted( name.charAt(0), names[names.length-1], companyName.replaceAll(" ", "").toLowerCase() );
        return emails.add(email);
    }


    public boolean replaceEmail(String oldEmail, String newEmail){
        if(!emails.contains(oldEmail)){
            return false;
        }
        emails.remove(oldEmail);
        return emails.add(newEmail);
    }

}
