import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    record Person(String firstName, String lastName){
        @Override
        public String toString() {
            return firstName+" "+lastName;
        }
    }

    public static void main(String[] args) {

        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Mohammed", "Othman"),
                new Person("Mohammed", "Karim"),
                new Person("Mohammed", "Mustafa"),
                new Person("Shafiq", "Rafiq"),
                new Person("ahmed", "Nazim"),
                new Person("Ahmed", "Foriq")
        ));

        people.sort((o1, o2) -> o1.firstName.compareTo(o2.firstName));
        for(Person person : people){
            System.out.println(person);
        }

        //@FunctionalInterface this gives an error since Comparator's abstract method is inherited so this is not a SAM.
        interface enhancedComparator<T> extends Comparator<T> { // U cannot use implement for an interface, it just extends
            public int secondLevel(T o1, T o2);//private methods in interfaces should have a body.
        }

        var comparator = new enhancedComparator<Person>(){

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }

            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.firstName.compareTo(o2.firstName);
                return result == 0 ? secondLevel(o1, o2) : result;
            }
        };

        people.sort(comparator);
        System.out.println("-".repeat(30));
        for(Person person : people){
            System.out.println(person);
        }

    }
}