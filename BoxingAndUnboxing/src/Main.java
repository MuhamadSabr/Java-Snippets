import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Integer boxedInteger = Integer.valueOf(15);   // Unnecessary boxing;
        int unboxedInt = boxedInteger.intValue();       // Unnecessary unboxing;
        Integer deprecatedWay = new Integer(15);

        Integer autoBoxing = 15;            //Boxing is wrapping or boxing a primitive value
        int autoUnBoxing = autoBoxing;      //Unboxing is unwrapping or unboxing a primitive value.

        System.out.println(boxedInteger +" "+ unboxedInt +" "+ deprecatedWay +" integerClass"+ boxedInteger.getClass()+" int is not a class so no .getClass()");
        System.out.println(autoBoxing +" "+ autoUnBoxing +" "+ autoBoxing.getClass().getSimpleName() +" "+ deprecatedWay.getClass().getName()  );

        Double resultBoxed = getLiteralDoublePrimitive();   //Boxing is needed n is performed automatically by java
        double resultUnboxed = getDoubleObject();           //Unboxing is needed n is performed automatically by java.

        Integer[] IntegerArray = new Integer[]{3,5,3,6}; //As u can see autoboxing is everywhere.

        var test = test(5,3,4,6,3);
        System.out.println(test);

    }

    private static ArrayList<Integer> test(int...varargs){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(var a : varargs){
            arrayList.add(a);
        }
        return arrayList;
    }
    public static Double getDoubleObject(){
        return Double.valueOf(100.00);
    }

    public static double getLiteralDoublePrimitive(){
        return 100.0;
    }

}