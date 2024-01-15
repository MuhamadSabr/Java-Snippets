// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

package com.mmd;

import java.util.*;

//Start is from left to right.
public class Main {
    public static void main(String[] args) {
        // A Linked list can be used as a list, a single n double ended queue also a stack.
        LinkedList<String> placesToVisit = new LinkedList<>();
        //var placesToVisit = new LinkedList<>();    //This way the type inferred is going to be Object. So specify the type in the instantiation part.

        placesToVisit.add(0,"UK");
        System.out.println("Outside " +placesToVisit.toString());
        addMoreElements(placesToVisit);
        System.out.println("Outside " +placesToVisit.toString());
        //removeElements(placesToVisit);
        //gettingElements(placesToVisit);
        printItinerary(placesToVisit);
        System.out.println("-".repeat(50));

        testListIterator(placesToVisit);

    }

    public static void addMoreElements(LinkedList<String> list){
        list.addFirst("Mohammed");   //void       //actionPostFix() is only on LinkedList.
        list.addLast("Ahmed");      //void
        list.add("US") ; //add appends the element to the end of the list n returns true.
        list.addLast("Mohammed");
        list.addLast("US");
        list.addLast("Ahmed");
        list.addAll(List.of("How", "Wow", "Cow"));
        list.addLast("Mohammed");
        //Queue methods
        list.offer("Offer is insert at the end");    //Returns boolean
        list.offerLast("Does the same thing as offer");   //Returns boolean
        list.offerFirst("Double ended queue lets u add to the start of the queue"); //Retunrs boolean
        //Stack methods
        list.push("Push to the stack");   //void

    }

    public static void removeElements(LinkedList<String> placesToVisit){
        System.out.println( placesToVisit.remove() );  //Removes the first element n returns it.
        System.out.println(placesToVisit.toString());
        System.out.println( placesToVisit.removeFirst() );  // Removes the first n returns it. The same as .remove() but clearer.
        System.out.println(placesToVisit.toString());
        System.out.println( placesToVisit.remove(2) );  // Removes the element at the specified index n returns the removed element.
        System.out.println(placesToVisit.toString());
        System.out.println( placesToVisit.remove("Mohammed") ); // Removes the object if it exists n returns true, otherwise returns false.
                                                                //The above .remove(object) only removes the first copy of the object.
        System.out.println(placesToVisit.toString());
        System.out.println(placesToVisit.removeFirstOccurrence("Mohammed"));  //This is the same as .remove(object) even in returning boolean
        System.out.println(placesToVisit.toString());
        System.out.println(placesToVisit.removeLastOccurrence("Ahmed"));  //Returns boolean
        System.out.println(placesToVisit);
        System.out.println( placesToVisit.removeFirst() );  // Removes the first n returns it.
        System.out.println(placesToVisit.toString());
        System.out.println( placesToVisit.removeLast() );  // Removes the last n returns it.
        System.out.println(placesToVisit.toString());

        System.out.println("Queue remove methods...".repeat(5));

        System.out.println(placesToVisit.poll());           // Removes the first element at the queue n returns it.
        System.out.println(placesToVisit.toString());
        System.out.println(placesToVisit.pollFirst());      // It is the same as .poll()
        System.out.println(placesToVisit.toString());
        System.out.println(placesToVisit.pollLast());
        System.out.println(placesToVisit.toString());

        System.out.println("Stock remove methods...".repeat(5));
        String s1= placesToVisit.pop();
        System.out.println(s1 +" Just removed");   //Removes the last added element in the stack
        System.out.println(placesToVisit + " last remaining elements");

    }

    public static void gettingElements(LinkedList<String> placesToVisit){
        System.out.println( placesToVisit.get(4) ); //Get the 5th element. Note this is a double ended LinkedList java will decide at which end to start traversing
                                //So java will never do a full travers
        System.out.println("First element " + placesToVisit.getFirst());
        System.out.println("Last element " + placesToVisit.getLast());

        System.out.println(placesToVisit.indexOf("Mohammed"));
        System.out.println(placesToVisit.lastIndexOf("Mohammed"));

        System.out.println("Queue retrieval method.....".repeat(5));
        System.out.println(placesToVisit.element());   //Returns the first element of the queue.

        System.out.println("Queue retrieval methods.....".repeat(5));
        System.out.println(placesToVisit.peek());       //First element in a stack which means lastly added element
        System.out.println(placesToVisit.peekFirst());  //Does the same as .peek()
        System.out.println(placesToVisit.peekLast());  //Firstly added to the stack. which means last element for a stack.

    }

    public static void printItinerary(LinkedList<String> placesToVisit){
        for(int i = 0; i<placesToVisit.size()-1; i++){
            System.out.printf("From %s to-> %s%n", placesToVisit.get(i), placesToVisit.get(i+1));
        }

    }
    public static void printItinerary2(LinkedList<String> placesToVisit){
        String previousTown = placesToVisit.getFirst();
        for(var nextTown : placesToVisit){
            System.out.printf("From %s to-> %s%n", previousTown, nextTown);
            previousTown = nextTown;
        }
    }

    public static void printItinerary3(LinkedList<String> placesToVisit){
        String previousTown = placesToVisit.getFirst();
        ListIterator<String> iterator = placesToVisit.listIterator(1); //U can specify at what index u want to create the ListIterator
        while (iterator.hasNext()){                                         //By default it creates the ListIterator to start at 0
            var nextTown = iterator.next();  //This returns the current item and also this is what moves the iterator to the next item
            System.out.printf("From %s to-> %s%n", previousTown, nextTown);
            previousTown = nextTown;
        }
    }

    public static void testIterator(LinkedList<String> placesToVisit){
        System.out.println(placesToVisit);
        Iterator<String> iterator = placesToVisit.iterator(); //U cannot specify at what index u want to create an Iterator
        while (iterator.hasNext()){
            var nextTown = iterator.next();  //This returns the current item and also this is what moves the iterator to the next item
            if (nextTown.equals( "US" ) ){
                iterator.remove();          //As u can see u can remove an element through the iterator. This is the only supported method.
            }
        }
        System.out.println(placesToVisit);
    }

    public static void testListIterator(LinkedList<String> placesToVisit){
        System.out.println(placesToVisit);
        ListIterator<String> iterator = placesToVisit.listIterator(); //U cannot specify at what index u want to create an Iterator
        while (iterator.hasNext()){
            var nextTown = iterator.next();  //This returns the current item and also this is what moves the iterator to the next item
            System.out.println(nextTown);
            if (nextTown.equals( "US" ) ){  //U can use .hasPrevious() to go back.
                iterator.remove();          //As u can see u can remove an element through the iterator. U can also set(), and
                iterator.add("United States Of America");                //add(). this add adds an element after this iterator.
                                                                        //These 2 lines is the same as using .set()
            }
        }
        System.out.printf("-".repeat(30) + "%n");
        while (iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
    }

}