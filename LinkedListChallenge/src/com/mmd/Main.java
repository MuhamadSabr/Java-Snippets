package com.mmd;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Town> towns = new LinkedList<>();
        //towns.add(new Town());
        addToList(towns, new Town("Adelaide", 1374));
        addToList(towns, new Town("adelaide", 13));
        addToList(towns, new Town("Alice Springs", 2771));
        addToList(towns, new Town("Brisbane", 917) );
        addToList(towns, new Town("Darwin", 3972));
        addToList(towns, new Town("Melbourne", 877));
        addToList(towns, new Town("Perth", 3923));
        addToList(towns, new Town("Sydney", 0));
        Scanner scanner = new Scanner(System.in);
        ListIterator<Town> townListIterator = towns.listIterator();
        boolean flag = true;
        menu();

        while(flag) {
            switch (scanner.nextLine().substring(0,1).toUpperCase()){
                case "F"-> {
                    if(!townListIterator.hasNext()){
                        System.out.println("You are at the end of the list");
                        townListIterator.previous();
                        System.out.println(townListIterator.next());
                    }else
                    System.out.println( townListIterator.next());
                }
                case "B"-> {
                    if(!townListIterator.hasPrevious()){
                        System.out.println("You are at the beginning of the list");
                        townListIterator.next();
                        System.out.println(townListIterator.previous());
                    }else
                        System.out.println( townListIterator.previous());
                }
                case "L"-> printItinerary(towns);
                case "M"-> menu();
                default -> flag = false;
            }
        }
    }

    private static void printItinerary(LinkedList<Town> towns){
        ListIterator<Town> townListIterator = towns.listIterator(1);
        String previousTown = towns.getFirst().town();
        while(townListIterator.hasNext()){
            String nextTown = townListIterator.next().town();
            System.out.printf("%d. From %s to-> %s%n", townListIterator.nextIndex()-1 ,previousTown, nextTown);
            previousTown = nextTown;
        }
    }
    private static void menu(){
        System.out.println("""
                    Available actions (select word or letter):
                    (F)orward
                    (B)ackwards
                    (L)ist Places
                    (M)enu
                    (Q)uit""");
    }

    private static void  addToList(LinkedList<Town> list, Town town){
        ListIterator<Town> townListIterator = list.listIterator();
        while (townListIterator.hasNext()){  // list.contains(town) Java includes implicit code for the record which checks equality on all of its elements
            if(townListIterator.next().town().equalsIgnoreCase(town.town()) ) {
                return;
            }
        }

        int matchedIndex=0;
        for(var element : list){
            if( town.distanceFromStartingPoint() < element.distanceFromStartingPoint() ){
                list.add(matchedIndex, town);
                return;
            }
            matchedIndex++;
        }
            list.add(town);
    }

}

record Town(String town, int distanceFromStartingPoint){
    public String toString(){
        return "%s of distance (%d)".formatted(town,distanceFromStartingPoint);
    }
}



/*
Ordered by distance from starting point
ListIterator has to be used.
Create an itinerary of places to visit.
Create a type that has two fields town n distanceFromStartingPoint
 */