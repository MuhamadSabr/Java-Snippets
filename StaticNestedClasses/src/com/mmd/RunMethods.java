package com.mmd;

import com.mmd.domain.Employee;
import com.mmd.domain.EmployeeComparator;
import com.mmd.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RunMethods {
    public static void main(String[] args){
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(1001, "Mohammed", 2023,"Erbil"),
                new StoreEmployee(1005, "ahmed", 2018,"Erbil"),
                new StoreEmployee(1003, "Hasan", 2017,"Duhok"),
                new StoreEmployee(1020, "karim", 2020,"Duhok"),
                new StoreEmployee(1015, "Dyar", 2021,"Suli")
        ));

        var c0 = new EmployeeComparator();
        var c1 = new Employee.EmployeeComparator();
        var c2 = storeEmployees.get(0).new StoreComparator<StoreEmployee>();
        var c3 = new StoreEmployee().new StoreComparator<StoreEmployee>();
        sortIt(storeEmployees, c0);
        sortIt(storeEmployees, c1);
        sortIt(storeEmployees, c2);
        sortIt(storeEmployees, c3);

        var c4 = new Comparator<StoreEmployee>() {
            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        sortIt(storeEmployees, (o1, o2) -> o1.getName().compareTo(o2.getName()));
//        sortIt(storeEmployees, Comparator.comparing(Employee::getName));
    }
    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator){
        System.out.println("Sorting with comparator : " + comparator.toString());
        list.sort(comparator);
        for(var emp : list){
            System.out.println(emp);
        }
    }
}
