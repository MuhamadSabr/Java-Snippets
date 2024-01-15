package com.mmd;

public record Employee(String firstName, String lastName, int hireDate) {
    @Override
    public String toString() {
        return "%s %s %d".formatted(firstName, lastName, hireDate);
    }
}
