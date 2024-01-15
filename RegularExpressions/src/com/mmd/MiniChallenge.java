package com.mmd;

public class MiniChallenge {
    public static void main(String[] args) {
        String string = "Hello, World!";
        if(string.matches("Hello, World!")){
            System.out.println(string);
        }else System.out.println("Does not match "+ string);

        String pattern1 = "[A-Z][a-zA-Z0-9\\s]*[.]"; //Another way [.] to specify a period.
        String pattern1c= "[A-Z].*\\.";   //the .* means any number of any character, \\. is to make sure a period is needed, not metacharacter
        string="Hello World#.";//Remember the whole word must match "[A-Z][a-z]*."
        if(string.matches(pattern1c)){
            System.out.println("Matches : "+ pattern1     +" " + string);
        }else System.out.println("Does not match "+  pattern1  +" " + string);


        String pattern2 = "[A-Z].*[.?!]";
        String pattern2c= "[A-Z][a-zA-Z0-9\\s\\p{Punct}]*[.?!]";
        String pattern2b= "^[A-Z][\\p{all}]*[.?!]$"; //The anchors here r redundant. Since String's matches method always
        for(String str : new String[]{"The bike is red, and has flat tires.",    //matches the entire string
                                        "I love being a new L.P.A student!",
                                        "Hello, friends and family: Welcome!", "How are you, Mary?"}){
            if(str.matches(pattern2b))
            System.out.println(str);
        }
    }
}
