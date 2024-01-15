package com.mmd;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class Main {
    public static void main(String ... args) {

        int [] a = new int[5];  //Every element is set to 0
        Float b [] = new Float [5]; //if not specified.
        int c []; //Variable has not be initialized
        Integer [] d = {1,3,4,5,5};
        Character[] chr;
        chr=new Character[5];//{1,2,3,4,5}; Array creation with both dimension exp n initialization is illegal. Remove the size
        System.out.println(chr[0]);

        String[] str = new String[5];
        //System.out.println(str[0].equals("Hi")); // U cannot do any operation on a null variable


        TestClass [] tc = new TestClass[5]; //Every element is null
        tc[0] = new TestClass(5,3);  //If u use tc[0].anyMethod or field u get null exception
        tc[2] = new TestClass(0,0);

        for(int i =0; i<tc.length; i++){
            if (tc[i] == null) {
                continue;
            }
            System.out.printf("iteration %d is %d%n", i, tc[i].getX());
        }
        for(TestClass element : tc){
            if (element== null) {
                continue;
            }
            System.out.printf("for each loop, value %d%n", element.getX());
        }

        System.out.println();
        System.out.println(Arrays.toString(tc)); //Will call the java.lang.Object toString. pacakgeName.TypeOfArray@HashCode is the result

        Object[] objectVar = new Object[5];
        Object objectVarIn = chr;  //Assigning an array at declaration to an already created array.

        objectVar[0] = 1;
        objectVar[1] = "Hello";
        objectVar[2] = 'h';
        objectVar[3] = 2.50;
        objectVar[4] = new StringBuilder("String builder");
        for(Object element : objectVar){
            System.out.println("Element of array of Object type that could contain element of every type " + element);
        }

        int[] array1 = getRandomArray(7);
        int[] array1Copy = Arrays.copyOf( array1,array1.length-1);
        //Arrays.sort(array1Copy,Collections.reverseOrder()); //For Collections.reverseOrder() to work u v to use it on a wrapper class. Primative types r not supported
        System.out.printf("Not sorted %s%n", Arrays.toString(array1));
        System.out.printf("After sorting %s%n", Arrays.toString(array1Copy));

        int [] dsc =getRandomArray(10);
        System.out.printf("Before sorting : %s%n", Arrays.toString(dsc));
        sortDesc(dsc);
        System.out.printf("After sorting : %s%n", Arrays.toString(dsc));
        String y = "";
        changeY(y);
        System.out.println("Y : " + y);

        String[] splitString = "Hello World Wide".split(" ");
        print(splitString);
        print("Hello"); // Be careful u v to use the ... not String[] in ur user-defined method
        print("Hello", "Mr", "Ahmed", "How ", "u", "doing", "today");

        //String[] userArray = readIntegers();
        //print(userArray);
        //System.out.println("Minimum value : " + findMin(userArray) );

        int [] aa [] = new int[][] {
                                    {1,2,3,4,5},
                                    {1,2,3,4,5},
                                    {1,2,3,4,5} }; // U have length 3, 3 elements of 5 element
        for(int[] outerArrays : aa){                        //One dimensional array does not need the [] but after that number of [] will be num-dimension-1
            System.out.println(Arrays.toString(outerArrays));
        }
        System.out.println(Arrays.toString(aa));
        for(int i=0; i<aa.length; i++){
            for(int j=0; j<aa[0].length; j++){
                System.out.printf(aa[i][j] +" ");
            }
            System.out.println();
        }
            System.out.println("-".repeat(30));
        for(var outer : aa){
            System.out.println(Arrays.toString(outer));
            for(var inner : outer){
                System.out.print(inner + " ");
            }
            System.out.println();
        }

        System.out.println("-".repeat(30));
        System.out.println(Arrays.deepToString(aa)); //Method to print one-dimension, two...

        int[][] arr = new int[5][];
        arr[0] = new int[3];
        arr[1] = new int[1];
        arr[2] = new int[4];

        Object [] arrO = new Object[5];
        arrO[0] = new String[3][];
        arrO[1] = new int [3];
        arrO[2] = new Double[4];
        for(var outer : arrO){
            System.out.println(Arrays.deepToString( (Object[]) outer));
            }
    }


    public static void print(String... args){
        for(String element : args){
            System.out.printf("%s%n", element);
        }
    }

    private static void reverse(int[] arr){
        System.out.println("         Array = " + Arrays.toString(arr) );

        for(int i=0; i<(arr.length/2); i++){
            int temp = arr[i];
            arr[i]= arr[arr.length-1-i];
            arr[arr.length-1-i]= temp;

        }
        System.out.println("Reversed array = " + Arrays.toString(arr) );
    }
    public static String[] readIntegers(){
        Scanner scanner = new Scanner(System.in);
        String values="";
        values = scanner.nextLine();
        System.out.println(values);
        String[] strNumber = values.split(",");
        /*
        int[] numberArray = new int[values.split];
        for(int i=0;i<numberArray.length; i++){
            numberArray[i] = Integer.parseInt(strNumber[i].trim()); // .trim to cut any spaces
            }
         */
        return strNumber;
    }
    public static int findMin(String[] array){
        int min = Integer.parseInt(array[0]);
        for(String element : array){
            if(Integer.parseInt(element) <min){
                min=Integer.parseInt(element);
            }
        }
        return min;
    }

    public static void changeY(String y){
        y = "Hi";
    }
    public static void sortDesc(int[] arr){
        //int[] arrResult = Arrays.copyOf(arr,arr.length);
        for(int i=0; i<arr.length; i++){
            for(int j = i; j<arr.length; j++){
                if(arr[i]<arr[j]){
                    int temp = arr[i];
                    arr[i]= arr[j];
                    arr[j]= temp;
                }
            }
        }
        //return arr;
    }
    public static int[] getRandomArray(int len){
        Random random = new Random();
        int[] newInt = new int[len];

        for(int i = 0 ; i<len ; i++){
            newInt[i] = random.nextInt(50); //returns a random number between 0 and 49.
        }

        return newInt;
    }

}