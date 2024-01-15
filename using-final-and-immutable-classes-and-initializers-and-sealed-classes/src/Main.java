import com.mmd.external.Logger;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Mohammed", 1);
        CsStudent csStudent = new CsStudent("Mohammed Cs student", 1);
        Student csStudentStudentAsReferenceType = new CsStudent("CS student with Student reference type", 100);
        student.setId(2);
        System.out.println(student.getId() + student.getName());
        student.printHello();//static methods should be called with a Reference type n not a instance
        csStudent.printHello();//In the case of A reference type of parent being assigned an instance of child. calling a static method
        csStudentStudentAsReferenceType.printHello();   //still calls the reference type static method n not the instance in case of hiding.

        System.out.println("-".repeat(30));

        StringBuilder sb = new StringBuilder("Lunch is served at 12:15");
        Logger.logToConsole(sb.toString());
        sb.append(", n dinner at 6:45");
        Logger.logToConsole(sb);
        System.out.println(sb);
    }
}



class Student{
    final private String name;
    private int id;
    final static public int lastId = 1; // U cannot call a static variable using this.instanceVariable. self-explanatory.

    Student(String name, int id) {
        this.name = name;
        this.id =id;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name){ //After construction there is no changing of final variables.
//        this.name = name;
//    }

    final public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public static final void printHello(){
        System.out.println("Hello");
    }
}

interface general{
    public static final int id = 0;
}

class CsStudent extends Student{ //Cannot inherit from final class(Student)

    CsStudent(String name, int id) {
        super(name, id);
    }

    //@Override //static methods canot be annotated with @Override
    public static void printHellow(){
        System.out.println("Hello World");
    }

//    @Override             Cannot override final method.
//    public int getId(){
//        return id;
//    }




}