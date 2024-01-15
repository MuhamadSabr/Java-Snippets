package com.mmd.includingexcludingmethods;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        IntStream.iterate((int)'A', i-> i<=(int)'z', n-> n+1)
                .filter(Character::isAlphabetic)
//                .filter(ip-> Character.toUpperCase(ip)>'E')
                .skip(5)//Must not be less than 0. skips number of elements u specify as the argument.
//                .dropWhile(pc-> Character.isAlphabetic(pc))//Drop elements until you find the first false condition. Starts the first time the condition evaluates to false
//                .takeWhile(Character::isAlphabetic)     //Take  elements until you find the first false condition.
                .forEach(ic -> System.out.printf("%c ", ic));//Stops the first time the condition evaluates to false
    }
}
