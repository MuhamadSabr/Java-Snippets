package com.mmd;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleProgram consoleProgram = new ConsoleProgram();
        boolean flag = true;
        while(flag){
            consoleProgram.menu();
            switch (scanner.nextInt()){
                case 0 -> flag= false;
                case 1 -> consoleProgram.addItems();
                case 2 -> consoleProgram.removeItems();
                default ->  flag = false;
            }
        }
    }
}