package com.mmd.EnumSetNEnumMap;

import jdk.dynalink.linker.LinkerServices;

import java.util.*;

enum DaysOfWeek {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}


public class Main {

    public static void main(String[] args) {
        List<DaysOfWeek> mohammedWorkDays = new ArrayList<>(List.of(
                DaysOfWeek.SUNDAY, DaysOfWeek.MONDAY, DaysOfWeek.TUESDAY, DaysOfWeek.WEDNESDAY, DaysOfWeek.THURSDAY
        ));
        EnumSet<DaysOfWeek> mohammedDaysSet = EnumSet.copyOf(mohammedWorkDays);//Takes a collection n returns a EnumSet.
        mohammedDaysSet.forEach(System.out::println);//The set is naturally ordered by the enum's ordinals
        System.out.println(mohammedDaysSet.getClass().getSimpleName());//RegularEnumSet. if passed 64 becomes JumboEnumSet.
        System.out.println("-".repeat(50));

        EnumSet<DaysOfWeek> allDaysSet = EnumSet.allOf(DaysOfWeek.class);
        allDaysSet.forEach(System.out::println);
        System.out.println("-".repeat(50));

        EnumSet.complementOf(mohammedDaysSet).forEach(System.out::println);//Returns the difference between the enumSet you pass
                                                                            //and the Enum type of the EnumSet.
        Set<DaysOfWeek> complementOf = EnumSet.copyOf(allDaysSet);
        complementOf.removeAll(mohammedDaysSet);
        complementOf.forEach(System.out::println);
        System.out.println("-".repeat(50));

        Set<DaysOfWeek> businessDays = EnumSet.range(DaysOfWeek.SUNDAY, DaysOfWeek.THURSDAY);//Both E from n E to are inclusive.
        businessDays.forEach(System.out::println);


        //This does not have a no args constructor.
        Map<DaysOfWeek,String[]> daysMap = new EnumMap<>(DaysOfWeek.class);//This class is not abstract like EnumSet. but u do need to pass
                                                // the type of the keys.class at construction. then u can use all the Map methods on it.
        daysMap.put(DaysOfWeek.FRIDAY, new String[]{"Mohammed", "Karim", "Jawhar"});
        daysMap.put(DaysOfWeek.SUNDAY, new String[]{"Karim", "Jawhar", "Rafiq"});
        daysMap.forEach((k, v) -> System.out.println(k + " : " + v)); // Again the map is ordered by the ordinal of the enum Type naturally
    }

}
