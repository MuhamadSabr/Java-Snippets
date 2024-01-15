package com.mmd;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        DayOfTheWeek today = DayOfTheWeek.FRIDAY;  //U can create a variable of the enum type n assign one of the values of the type
        System.out.println(today);
        System.out.println(today.getClass().getName());  //The FQCN is pacakge.enumName in this case com.mmd.DayOfTheWeek
        System.out.println(today.name());  //This is the same as only typing the variableName with more functionality.
        System.out.println(today.toString());  //Again u get the value of the variable.
        System.out.println(today.ordinal());  //This gets the position of the element in the order declared in the enum body
        System.out.println(DayOfTheWeek.SUNDAY.ordinal());  //U can get the order of each element this way without creating a variable to each one.

        for(int i = 0 ; i<10; i++) {
            today = getRandomDayOfTheWeek();
            if (today.equals( DayOfTheWeek.FRIDAY ) ) {
                System.out.printf("It is: %s, the beginning of the weekend%n", today);
            } else {
                System.out.printf("Today is %s the %d day of the week%n", today.name(), today.ordinal()+1);
            }
        }
        switchWeekDay(today);

        for(Topping topping : Topping.values()){
            System.out.println(topping.name() + " has a price of " + topping.getPrice());
        }
        System.out.println(DayOfTheWeek.values().toString());  //toString has not be overridden so u get the Object form of toString
    }

    private static void switchWeekDay(DayOfTheWeek weekDay){
        switch (weekDay){
            case SUNDAY -> System.out.printf("%s, %d%n", weekDay.name(), weekDay.ordinal()+1);  // U can directly specify the constant values in the cases
            case MONDAY -> System.out.printf("It is %s, the %d day of the week%n", weekDay.name(), weekDay.ordinal());
            default -> System.out.printf("Today is %s%s, the %d day of the week",weekDay.name().charAt(0),weekDay.name().substring(1).toLowerCase(), weekDay.ordinal()+1);
        }
    }
    private static DayOfTheWeek getRandomDayOfTheWeek(){
        DayOfTheWeek[] day = DayOfTheWeek.values(); //EnumName.values() returns all its values in an array[]
        return day[new Random().nextInt(7)];  //Remember the bound is -1 than the number u specify.
    }

}