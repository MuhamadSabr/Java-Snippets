package com.mmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) /*throws IOException //The application throwing an exception wouldn't be ideal*/{
        String filePathNName = "files/test.csv";
        String filePathNNameAbs = "/files/test.csv";
        Path path = Paths.get(filePathNNameAbs);
//        File file = new File(filePathNName);//The below overloaded version constructor of File takes 2 args, the 1st is parent
        File file = new File("/", filePathNName); // "/" is root.
        file = new File(".", filePathNName);   // "." means current working directory.
        file = new File(new File("").getAbsolutePath(), filePathNName);
        file = new File(new File("."), filePathNName);//This version takes a File instance as the parent.
        file = new File(new File("").getAbsoluteFile(), filePathNName);

        System.out.println("pwd : " + file.getAbsolutePath());
        for(var f : File.listRoots()){
            System.out.println(f);
        }
//        try {
//            List<String> lines = Files.readAllLines(path);
//        } catch (IOException e) {
//            throw new RuntimeException(e);//This code assumes the file exists, n throws n exception if not.
//        }called EAFP(easier to ask forgiveness than permission). Assumes an operation will usually succeed, n then
                                                //handles any errors that occur, if they do occur.

//        testFile2(null);//Passing null to a FileReader does not result in FileNotFoundException but in NullPointerException

        if(!file.exists()){//This style U check for errors before performing an operation. called LBYL(look before you jump)
            System.out.println("I cannot run unless this file exists");
            System.out.println("Quitting application go figure it out");

            StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");//Any empty {}, n not null
            stringJoiner.add("Pineapple");
            System.out.println(stringJoiner.add("Apple").add("Orange"));

            return;
        }System.out.println("I am good to go");

        Path path1 = Paths.get("files/test.csv");
        System.out.println(path1.getFileName());
        if(Files.exists(path1)){
            System.out.println(path1 + " exists");
        }

    }

    public static void testFile(String fileName){
        Path path = Paths.get(fileName);
        FileReader reader = null;
        try {
//            List<String> lines = Files.readAllLines(path);
            reader = new FileReader(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);//Not handling it but, masking it as a RuntimeException
        }finally {//Even if an exception does not occur finally is executed. Does not  have any parameters.
            System.out.println("Ow ow ");
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void testFile2(String fileName){//U can't put catching an Exception after another more general exception
        try (FileReader reader = new FileReader(fileName)) {//that results in catching that one too. 1st Most specific then parents
        } catch (FileNotFoundException e) {//Might occur when opening the resource. Having these 2 different exceptions is for
            System.out.println("File " + fileName + " was not found");
          throw new RuntimeException(e);//when u want to do different operations for each one. If one action is needed combine both
        } catch (NullPointerException | IllegalArgumentException e){//U can combine two or more exceptions in one catch clause
            System.out.println("Bad Data");//There still needs to be 1 arg,declared as the last arg in the EXPlist separated by |
        } catch (IOException e) { //Might occur when working with or closing the resource.
            System.out.println("File was not open or something blocked w/r operation");
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.println("Something unexpected n unrelated to resources happened");
        }
        System.out.println("File exists and able to use as a resource");


    }

}