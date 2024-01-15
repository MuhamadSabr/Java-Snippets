package com.mmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EmployeeStore {
    public static void main(String[] args) {
        List<Employee> employeeList;
        Employee e1= new Employee("Mohammed", "Othman", 2022);
        Employee e2= new Employee("Ahmed", "Abas", 2021);
        Employee e3= new Employee("Karim", "Jawhar", 2019);
        Employee e4= new Employee("Fariq", "Rafiq", 2015);
        employeeList = new ArrayList<>(Arrays.asList(e1,e2,e3,e4));

        empMethod(employeeList);

    }
    public static void empMethod(List<Employee> list){
        class LocalClass {
            private Employee employee;
            private String fullName;
            private int yearsWorked;
            private LocalClass(Employee employee){
                this.employee = employee;
                fullName = employee.firstName()+ " " + employee.lastName();
                yearsWorked = 2023 - employee.hireDate();
            }

//            @Override
//            public int compare(Employee o1, Employee o2) {
//                return Integer.compare(o1.hireDate(),o2.hireDate());
//            }
        }
        for(var emp : list) {
            LocalClass localClass = new LocalClass(emp);
            System.out.printf("full name %s, years worked %d%n", localClass.fullName, localClass.yearsWorked);
        }

        for( var emp : list){
            System.out.println(emp);
        }
        list.sort(new Comparator<Employee>(){

            @Override
            public int compare(Employee o1, Employee o2) {
                return Integer.compare(o1.hireDate(),o2.hireDate());
            }
        }.reversed());
        System.out.println("-".repeat(30));
        for( var emp : list){
            System.out.println(emp);
        }
    }
}
