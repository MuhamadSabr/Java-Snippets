package com.mmd;

import com.mmd.domain.Employee;
import com.mmd.domain.EmployeeComparator;
import com.mmd.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(1001, "Mohammed", 2023),
                new Employee(1005, "ahmed", 2018),
                new Employee(1003, "Hasan", 2017),
                new Employee(1020, "karim", 2020),
                new Employee(1015, "Dyar", 2021)
        ));
//        var comparator = new EmployeeComparator();
        print(employees);
//        employees.sort(comparator);
//        print(employees);
        var comparator = new Employee.EmployeeComparator("yearstarted");
        employees.sort(comparator);
        print(employees);

        System.out.println("StoreEmployees:");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(1001, "Mohammed", 2023,"Erbil"),
                new StoreEmployee(1005, "ahmed", 2018,"Erbil"),
                new StoreEmployee(1003, "Hasan", 2017,"Duhok"),
                new StoreEmployee(1020, "karim", 2020,"Duhok"),
                new StoreEmployee(1015, "Dyar", 2021,"Suli")
        ));

        print(storeEmployees);
        System.out.println("After sorting:");
        //var comparSt = new StoreEmployee.StoreComparator<>();// Not an enclosing class error//
        // indicates that an instance of the class has not been used to call the inner class, which must be.
        var comparatorStore = new StoreEmployee().new StoreComparator<>();
        //As u see above to create an instance of an inner class has another difference which is
        //instance.new innerClassName(AnyConstructor arguments) syntax has to be used.
        storeEmployees.sort(comparatorStore);
        print(storeEmployees);

    }
    public static void print(List<?> list){
        for( var item : list){
            System.out.println(item);
        }
        System.out.println("-".repeat(30));
    }
}