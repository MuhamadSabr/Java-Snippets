package com.mmd.domain;

import java.util.Comparator;

public class StoreEmployee extends Employee{
    private String store;

    public StoreEmployee() {
    }

    public StoreEmployee(int employee_id, String name, int yearStarted, String store) {
        super(employee_id, name, yearStarted);
        this.store = store;
    }

    @Override
    public String toString() {
        return "%-8s%s".formatted(store,super.toString());
    }

    public class StoreComparator <T extends StoreEmployee> implements Comparator<StoreEmployee>{

        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            int result = o1.store.compareTo(o2.store);//Zero means the employees work at the same store
            if(result==0){
                return new Employee.EmployeeComparator("yearStarted").
                        compare(o1,o2);// new Has to be used to be able to call the constructor.
            }
            return result;
        }
    }

}
