package com.mmd;

import java.util.ArrayList;
import java.util.Arrays;

record GroceryItem(String name, String type, int count){

    public GroceryItem(String name) {
        this(name, "Diary", 1); // U have to call the default constructor with all fields(here 3).
    }
    @Override
    public String toString() {
        return "%d of %s in class %s ".formatted(count, name.toUpperCase(), type);
    }
}

public class Main {
    public static void main(String[] args) {

        GroceryItem[] groceryArray = new GroceryItem[3]; //Do not use Object, since it bypasses compile-time check.
        groceryArray[0] = new GroceryItem("Milk"); //When u only want a specific type in ur array or arrayList
        groceryArray[1] = new GroceryItem("Beef", "Meat", 2);
        groceryArray[2] = new GroceryItem("Baking powder");

        System.out.println(Arrays.deepToString(groceryArray));
        System.out.println(Arrays.toString(groceryArray));

        //ArrayList groceryArrayList = new ArrayList(); // Called ROW VERSION If u do not specify the elements type, defaults to Object. Avoid using this
        //      This is the type                  (unnecessary)     U can also use it in the instantiation part.
        ArrayList<GroceryItem> groceryArrayList = new ArrayList<GroceryItem>();
        groceryArrayList.add(new GroceryItem("Milk"));
        groceryArrayList.add(new GroceryItem("Beef", "Meat", 2));
        //System.out.println(Arrays.toString(groceryArrayList)); //This will throw an error. A list is not an array
        //groceryArrayList.add(0,new GroceryItem("Apple", "Fruit", 6));
        groceryArrayList.set(0, new GroceryItem("Apple", "Fruit", 6)); //This .set(index, element) replaces the element at index.
        groceryArrayList.remove(1);
        System.out.println(groceryArrayList.toString());
    }
}