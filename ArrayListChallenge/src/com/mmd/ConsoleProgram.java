package com.mmd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ConsoleProgram {

    ArrayList<String> groceryItem = new ArrayList<>();
    public void menu(){
        String textBlock = """
                Available actions:
                                
                0 - to shutdown
                                
                1 - to add item(s) to list (comma delimited list)
                                
                2 - to remove any items (comma delimited list)
                                
                Enter a number for which action you want to do:""";
        System.out.printf("%s ",textBlock);
    }

    public void addItems(){
        Scanner scanner = new Scanner(System.in);
        String itemsToBeAdded = scanner.nextLine();
        String [] items = itemsToBeAdded.split(",");
        for(int i = 0 ; i<items.length ; i++){
            if(groceryItem.contains(items[i].trim()))
                continue;
            groceryItem.add(items[i].trim());
        }
        groceryItem.sort(Comparator.naturalOrder());
        System.out.println(groceryItem);
    }

    public void removeItems(){
        Scanner scanner = new Scanner(System.in);
        String itemsToBeRemoved = scanner.nextLine();
        String [] items = itemsToBeRemoved.split(",");
        for(int i = 0 ; i<items.length ; i++){
            groceryItem.remove(items[i].trim());
        }
        groceryItem.sort(Comparator.naturalOrder());
        System.out.println(groceryItem);
    }

}
