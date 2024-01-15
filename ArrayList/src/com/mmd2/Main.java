package com.mmd2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String... args){
        String[] items= {"Apple", "Banana", "Cucumber", "Pineapple",};
        List<String> list = List.of(items); //Transforming an array of String to an immutable list of String
        System.out.println(list);           //This has one advantage of making creating ArrayLists easier.
        ArrayList<String> arrayList = new ArrayList<String>(list);
        arrayList.add("Yogurt");
        arrayList.add("Pineapple");
        System.out.println(arrayList);

        String[] arrayStr = new String[] {"Mohammed", "Ahmed", "Karim", "Jawhar", "Sadq"}; // U cannot specify a length-dimension when using an array initializer.
        Arrays.sort(arrayStr,Comparator.reverseOrder());

        ArrayList<Integer> arrayList1 = new ArrayList<>( List.of(1,2,3,4,5) );
        arrayList1.add(0,0);
        System.out.println(arrayList1);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.addAll(arrayList1);  //U should replace this with parameterized constructor call while creating the ArrayList
        arrayList2.addAll( 0, List.of(-1,-2,-1,-2) ); //addAll takes a collection(any valid list)
        System.out.println(arrayList2);                     //An array is not a collection

        System.out.println(arrayList.contains("Pineapple"));  // Returns true if found, if not returns false
        System.out.println(arrayList.lastIndexOf("Pineapple")); // just like .indexOf(object) returns the index of the object(but the last one found not the first), if not found returns -1.
        System.out.println(arrayList.remove("Banana") );  //Returns true if the removal was successful(The element being wanted to be deleted was in the arrayList) otherwise returns false.
        //arrayList2.removeAll( List.of(-2,5,-1) );       // .removeAll() removes duplicates of elements in the collection. but .remove() only  removes first copy of the element
        arrayList2.remove(0);   //Removes by index only, when the elements r of numeric types.
        arrayList2.retainAll(List.of(-1,-2,-3));  //Retains only the elements specified in the collection.
        arrayList2.clear();   //Removes all the elements in the arrayList.
        System.out.println( arrayList2.isEmpty());
        arrayList2.addAll(Arrays.asList(-1,-2,-1,-2,0,1,2,3,4,5)); //Takes a variable list of arguments n produces a list
        arrayList2.sort(Comparator.naturalOrder());   // or Comparator.reversOrder();
        System.out.println(arrayList2);


        var groceryArray = arrayList.toArray(new String[arrayList.size()]); // U can use .toArray(new String[0]);
        System.out.println(Arrays.toString(groceryArray));  //This creates an array of the arrayList

        ArrayList<String> lastArrayList = new ArrayList<>(List.of(arrayStr));   //( Arrays.asList(arrayStr) ) ;
        lastArrayList.set(0, "Hello");
        var arrayListStr = Arrays.asList(arrayStr);   //arrayListStr is set to be a reference of Arrays.asList(arrayStr) this makes it backed by array. So any change will change the original array also.
        //arrayListStr.remove(0); Backed by array arrayLists cannot remove elements.
        //arrayListStr.add(0,"Hi"); Also backed by array arrayLists cannot add elements. It is not resizable.
        arrayListStr.set(0,"var variable referencing Arrays.asList(arrayStr");
        System.out.printf(Arrays.toString(arrayStr)  + "%n");
        System.out.println(arrayListStr.toString());
        System.out.println(lastArrayList);

        System.out.println("-".repeat(30));
        System.out.println(lastArrayList.set(lastArrayList.indexOf("Mohammed"), "Hama"));
        System.out.println(lastArrayList.indexOf("Ki"));
    }
}
