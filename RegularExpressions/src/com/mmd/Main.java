package com.mmd;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String string = "%s %s!".formatted("Hello", "World");
        String string2= String.format("%s %s!", "Hello", "World");
        System.out.println(string +"\n" + string2);

        System.out.println(format("%s %s %s", "Hello", "World", "How are you doing!"));


        String testString = "Anyone can learn abc's, 123's, and any regular expression";
        String replacement= "(-)";
        String[] patterns = {"[a-zA-Z]*$", "[a-zA-Z]${3}", "^Z?", "[aA]ny\\b"};//last must end at a word boundary not Anyone but Any
        for(String pa : patterns){
            String output = testString.replaceFirst(pa, replacement);
            System.out.println("Pattern: " + pa + " => " + output);
        }

        String paragraph = """
        Double, double toil and trouble;
        Fire burn and caldron bubble.
        Fillet of a fenny snake,
        In the caldron boil and bake;
        Eye of newt and toe of frog,
        Wool of bat and tongue of dog,
        Adder's fork and blind-worm's sting,
        Lizard's leg and howlet's wing,
        For a charm of powerful trouble,
        Like a hell-broth boil and bubble.""";

        int numberOfLines = paragraph.split("\\R").length;//Line break matcher. Any Unicode line break sequence.
        System.out.println("Number of lines : " + numberOfLines);
        int numberOfWords = paragraph.split("\\s").length;// \h A horizontal white space.\s A whitespace character: [ \t\n\x0B\f\r]
        System.out.println("Number of words: " + numberOfWords);
        String grub = paragraph.replaceAll("[a-zA-Z]+ble", "GRUB");
        System.out.println(grub);
        System.out.println("-".repeat(30));
        Scanner scanner = new Scanner(paragraph);
        scanner.useDelimiter("\\R");
//        while (scanner.hasNext()) {
//            System.out.println(scanner.next());
////            System.out.println(scanner.delimiter());
//        }
//        scanner.tokens()
//                .map(fs-> fs.replaceAll("\\p{Punct}", ""))// \p{Punct} matches every punction there is.
//                .flatMap(fs-> Arrays.stream(fs.split("\\s+")))
//                .filter(sp-> sp.matches("[a-zA-Z]+ble[.,;]?"))
////                .map(sl-> Arrays.stream(sl.split("\\s+")).count())
////                .map(sl-> sl.split("\\s").length)
//                .forEach(System.out::println);// tokens returns a stream of the scanner.

        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));
        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));
        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));//This method returns null when it reaches end of line like below.
        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));//This is null, if u want to check the next line u have to call
        scanner.nextLine();
        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));//returns the first match in the next line n moves forward.
        scanner.close();
    }

    public static String format(String regex, String... args){
        int index =0;
        while (regex.matches(".*%s.*")){//Matches checks for the entire string matching the regex not just a subString of it.
            regex = regex.replaceFirst("%s", args[index++]);
        }
        return regex;
    }
}