package com.mmd.includingexcludingmethods.mapsortpeek;

import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final int maxSeats = 100;
        final int seatInRow = 10;

        var stream = Stream.iterate(0, i-> i<maxSeats, i-> i+1)
                .map(i-> new Seat( (char)('A'+ i/seatInRow), (i%seatInRow)+1 ) )
                .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString))
                .mapToDouble(seat-> seat.price())
                .mapToObj(price->"%.2f".formatted(price));

        stream.forEach(System.out::println);
    }
}
