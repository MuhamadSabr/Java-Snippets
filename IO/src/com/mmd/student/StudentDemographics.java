package com.mmd.student;

public record StudentDemographics(String countryCode, int enrolledMonth,
                                  int enrolledYear, int ageAtEnrollment, String gender,
                                  boolean previousProgrammingExperience ) {

    @Override
    public String toString() {
        return "%s,%d,%d,%d,%s,%b".formatted(countryCode,
                enrolledMonth,enrolledYear, ageAtEnrollment,gender,
                previousProgrammingExperience);
    }


    public String toJSON() {
        return '{' +
                "\"countryCode\": " + '"' + countryCode + '"' + "\n"+
                ", \"enrolledMonth\": " + enrolledMonth + "\n"+
                ", \"enrolledYear\": " + enrolledYear + "\n"+
                ", \"ageAtEnrollment\": " + ageAtEnrollment + "\n"+
                ", \"gender\": " + '"' + gender + '"' + "\n"+
                ", \"previousProgrammingExperience\": " + previousProgrammingExperience + "\n"+
                '}' + '\n';
    }
}
