package mmd.com;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Integer number = 5;
        int[] numbers = {0,5,-50,30,7};
        for(int num : numbers){
            int val = number.compareTo(num);
            System.out.printf("%d %s %d comapreToResult=%d%n", number,
                    (val==0 ? "==" : val>0 ? ">" : "<" ), num, val );
        }
        System.out.println("-".repeat(30));
        String banana = "banana";
        String [] fruits = {"apple", "Banana", "bananaaa", "bananb"};
        //First characters are going to be compared if they were equal the following characters are going to be checked otherwise
        //The difference between the first different instance's character with the argument is going to be returned.
        //If number of characters are different then DifferenceNumber of character is going to be return n
        //positive if the instance has the extra characters, negative if vise versa. banana.compareTo("bananaaa") = -2
        for(String frui : fruits){
            int val = banana.compareTo(frui);
            System.out.printf("%s %s %s compareToResult=%d%n", banana,
                   val==0 ? "==" : val>0 ? ">" : "<", frui, val);
        }

        Student student =new Student( "Mohammed");
        Student[] students = {new Student("Mohammed"), new Student("mohammed"), new Student("Ahmed"),
        new Student("brwa"), new Student("arivan")};
        Arrays.sort(students); //Remember if ur class does not implement Comparable somehow u r going to get a run-time error.
        System.out.println(Arrays.toString(students));
        //System.out.println(student.compareTo("Ahmed")); //This is not good, u got no error when u passed a name instead of a student
        //Cannot access class could happen when u have to classes in the same file n the package is the default package.
        System.out.println(student.compareTo(new Student("Mohammed")));

        //To use the class that implements Comparator
        //Create an instance of the Comparator with the same reference of the class that implements it.
        //and assign a new instance of the class that implements the Comparator.
        Comparator<Student> gpaSorter = new StudentGPAComparator();
        //Arrays.sort(students, gpaSorter); // Then u call Arrays.sort(theClass u want to sort, TheComparatorUjustCreated)
        Arrays.sort(students,gpaSorter.reversed());// In case u want to sort in reverse order.
        System.out.println(Arrays.toString(students));
    }
}

class StudentGPAComparator implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2){
        return (o1.gpa+o1.name).compareTo(o2.gpa+o2.name); // If two students have the same GPA then sorts by name.
        //return (o2.gpa+o2.name).compareTo(o1.gpa+o1.name); //This reverses. If u wanted to sort in reverse order. But dont use this.
    }
}

//class Student implements Comparable{ // This is implementing the raw version of Comparable.
class Student implements Comparable<Student>{ //U should specify a type parameter when using Comparable. Raw version is not good
    private static int lastId = 1000;
    private static Random random = new Random();

    protected String name;
    private int id;
    protected double gpa;

    Student(String name){
        this.name = name;
        id = lastId++;
        gpa = random.nextDouble(1.0,4.0);
    }

    @Override
    public String toString() {
        return "%s %d %.2f".formatted(name, id, gpa);
    }

    //    @Override
//    public int compareTo(Object o) { //When u implement comparable on a class u should specify a type parameter.
//        Student other = (Student) o; // This is not a good idea since anything could be passed to compareTo.
//        return name.compareTo(other.name);
//    }

    @Override
    public int compareTo(Student o) {
        //return name.compareTo(o.name);
        return Integer.valueOf(id).compareTo(o.id);
    }

//    @Override
//    public int compareTo(Object o) {  //Having the same erasure problem means that Java at run-time cannot figure out which method to call.
//        Student other = (Student) o;
//        return name.compareTo(other.name);
//    }
}