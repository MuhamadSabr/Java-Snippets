package com.mmd.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(1001, "Mohammed", 2023,"Erbil"),
                new StoreEmployee(1005, "ahmed", 2018,"Erbil"),
                new StoreEmployee(1003, "Hasan", 2017,"Duhok"),
                new StoreEmployee(1020, "karim", 2020,"Duhok"),
                new StoreEmployee(1015, "Dyar", 2021,"Suli")
        ));

        addPigLatinName(storeEmployees);

    }

    public static void addPigLatinName(List<? extends StoreEmployee> list){

        String lastName = "Piggy";
        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee>{
            private String pigLatinName;
            private Employee originalInstance;

            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                this.pigLatinName = pigLatinName + " " + lastName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString(){
                return originalInstance.toString() + " " + pigLatinName;
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return pigLatinName.compareTo(o.pigLatinName);
            }
        }
        List<DecoratedEmployee> decoratedEmployees = new ArrayList<>(list.size());
        for( var emp : list){
            String name = emp.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";
            decoratedEmployees.add(new DecoratedEmployee(pigLatin, emp));
        }

        decoratedEmployees.sort(Comparator.reverseOrder());
        for(var dEmp : decoratedEmployees){
            //System.out.println(dEmp);
            System.out.println(dEmp.originalInstance.getName() + "\t\t" +
            dEmp.pigLatinName);
        }

    }

}
