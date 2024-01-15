package com.mmd;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class emailPattern {
    public static void main(String[] args) {
        String emails = """
                john.boy@valid.com
                jane.doe-smith@valid.co.uk
                jane_Doe1976@valid.co.uk
                bob-1964@valid.net
                elaine@valid-test.com.au
                david@valid.io
                john.boy@invalid
                bob!@invalid.com
                elaineinvalid1983@.com
                david@invalid..com
                mahamadsaber18@gmail.com.
                """;

        Pattern emailPattern = Pattern.compile("((\\w+[.-]?)+)@(\\p{Alpha}+([.-]\\p{Alpha}{2,})*[.]\\p{Alpha}{2,})");
        Matcher emailPatternMatcher = emailPattern.matcher(emails);
        emailPatternMatcher.results().forEach(matchResult -> System.out.println(matchResult.group() +"\t" +
                matchResult.group(1) + "\t"+
                matchResult.group(3)));

        String[] arrStr = emails.lines().toArray(String[]::new);//Changing an Stream<String> to array of String.
        System.out.println("-".repeat(30));
        for(String email : arrStr){
            Matcher eMatcher = emailPattern.matcher(email);
            boolean matched = eMatcher.matches();
            System.out.println(email + " is " + (matched ? "valid" : "invalid"));
            if(matched){
                System.out.println(eMatcher.group(1) + "\t" + eMatcher.group(3));
            }
        }
    }
}
