package com.mmd.terminaloperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        var result = IntStream.iterate(0, n-> n<1000 , n-> n+1)
                .summaryStatistics()
                .getAverage();
        System.out.println(result);

        var result2 = IntStream.iterate(2000, n-> n<=2024, n-> n+1)
                .filter(ip-> ip%4==0)// Leap year is divisible by 4.
                .peek(System.out::println)// Then we are going to have a little peek of those years. Useful to have a window
                .summaryStatistics();//finally print a summaryStatistics of these leap years.
        System.out.println(result2);

        Seat[] seats = new Seat[100];
        Arrays.setAll(seats, seatI-> new Seat( (char)('A'+ seatI/10), seatI%10+1 ));

        long result3 =Arrays.stream(seats)
                .filter(Seat::isReserved)
                .count();
        System.out.println(result3);


        boolean hasBookings = Arrays.stream(seats)
                .anyMatch(Seat::isReserved);

        System.out.println(hasBookings);

        boolean noBookings = Arrays.stream(seats)
                .noneMatch(Seat::isReserved);

        System.out.println(noBookings);

        boolean fullyBooked = Arrays.stream(seats)
                .allMatch(Seat::isReserved);

        System.out.println(fullyBooked);
    }
}
