package external;

import com.mmd.constructors.Parent;

import java.util.Random;

public class Child extends Parent {
    public Child(){super("Jadr", "10-09-1998", 5);
    System.out.println("Child's constructor , " + birthOrderString + ", " + birthOrder);}
//    public Child(String name, String dob) {
//        super(name, dob);
//        System.out.println("In child constructor");
//    }


    {
        System.out.println("In child initializer");
    }

    private final int getBirthOrder(){
        if(siblings==0) return 1;//We can access this field during construction or initialization because parent constructor has been called
        return new Random().nextInt(1, siblings+2);//and this is been initialized since this is a derived field.
    }



    private final int birthOrder = getBirthOrder();// Calling a final method is another way to initialize a final instance field.

    {
        if(siblings==0) birthOrderString = "Only";
        else if (birthOrder==1) birthOrderString ="First";//This is the only case so far where forward referencing is illegal
        else if (birthOrder== siblings+2) birthOrderString = "Last";//Declaring a final field instance after calling a instance initializer
        else birthOrderString = "Middle";// and referencing it. Declare the final field that u use in the initializer u won't face issues.
    }
    private final String birthOrderString;
}
