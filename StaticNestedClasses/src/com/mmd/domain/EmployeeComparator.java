package com.mmd.domain;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {

//    @Override
//    public int compare(T o1, T o2) {
//        return o1.getName().compareTo(o2.getName());
//    }

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
