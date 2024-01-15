package com.mmd.model;

public class LPAStudent extends Student{
    private double percentComplete;

    public LPAStudent(){//This is going to implicitly call the base no-args constructor, generating data for the
        percentComplete = random.nextDouble(0, 100.001);                               // other fields
    }

    @Override
    public String toString(){
        return "%s %8.1f%%".formatted(super.toString(), percentComplete);
        //%% is the way to print % symbol.
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String fieldValue) {
        if(fieldName.equalsIgnoreCase("percentComplete")){
            return Integer.parseInt(fieldValue)>=percentComplete;
        }
        return super.matchFieldValue(fieldName, fieldValue);
    }
}
