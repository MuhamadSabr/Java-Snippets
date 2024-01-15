package com.mmd.LinkedHashSetnTreeSet.challegne;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Theatre madina = new Theatre("Madina", 27, 1560);
        madina.printSeatMap();
        if(!madina.reserve('A', 1)){
            System.out.println("The seat is reserved or you typed a wrong row or number");
        }
        if(!madina.reserve('A', 1)){
            System.out.println("The seat is reserved or you typed a wrong row or number");
        }
        madina.printSeatMap();
        madina.printEmptySeats();

        List<Character> rows = new ArrayList<>( List.of('A', 'Z') );
        List<Integer>  seatNumbers = new ArrayList<>(List.of(5,6));
        madina.reserveSet(11, rows, seatNumbers);
        madina.printSeatMap();

    }
}
