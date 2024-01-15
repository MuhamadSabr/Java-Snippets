package com.mmd.domain;

import java.util.Comparator;

public class Employee {

    public static class  EmployeeComparator implements Comparator<Employee> {

        private String sortType;

        public EmployeeComparator(String sortType) {
            this.sortType = sortType;
        }
        public EmployeeComparator() {
            this("name");
        }

        @Override
        public int compare(Employee o1, Employee o2) {
            if(sortType.equalsIgnoreCase("yearstarted")){
                return Integer.valueOf(o1.yearStarted).compareTo(o2.yearStarted);
            }
            return o1.name.compareTo(o2.name);// Remember without an instance this static inner class
        }// will not be able to access any non-static member of the outer class.
    }
    private int employee_id;
    private String name;
    private int yearStarted;

    public Employee() {
    }

    public Employee(int employee_id, String name, int yearStarted) {
        this.employee_id = employee_id;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%d %-8s %d".formatted(employee_id, name, yearStarted);
    }
}
