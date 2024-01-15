package com.mmd.resourcebundle;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        Locale arIq= Locale.forLanguageTag("ar-IQ");
        for(Locale locale : List.of(Locale.US, Locale.forLanguageTag("ar-IQ"),
                                    Locale.CANADA_FRENCH, Locale.CANADA)) {
            ResourceBundle rb = ResourceBundle.getBundle("BasicText", locale);//Takes base name(bundle name)
//            System.out.println(rb.getClass().getName());//printing class name of the instance that comes back.
//            System.out.println(rb.getBaseBundleName());                 //which is PropertyResourceBundle
//            System.out.println(rb.keySet());//Set<String>

            Locale.setDefault(Locale.forLanguageTag("ar-IQ"));

            System.out.printf("%s %s!%n",//keys are case-sensitive. If the key is not found
                    rb.getString("hello"), rb.getString("world"));  //MissingResourceException key is thrown.
        }//locale in printf uses the Locale.getDefault locale.
    }
}
