package com.mmd.LinkedHashSetnTreeSet.challegne;

import java.util.*;

public class Theatre {

    private String theaterName;
    private int seatsInRow;
    private int numberOfRows;
    private NavigableSet<Seat> seats;

    public Theatre(String theaterName, int numberOfRows, int numberOfSeats){
        this.theaterName = theaterName;
        seats = new TreeSet<>();

        this.numberOfRows = Math.min(numberOfRows, 26);
        seatsInRow =  numberOfSeats/this.numberOfRows;

        for(int i=0; i< this.numberOfRows; i++){
            for(int j=0 ; j< seatsInRow; j++){
                seats.add(new Seat((char)(65+i), j+1));
            }
        }
    }

    public void printSeatMap(){
        System.out.println("-".repeat(90));
        List<Seat> seatList = new ArrayList<>(seats);
        for(int i=0; i<numberOfRows*seatsInRow; i++){
                System.out.print(seatList.get(i) + "\t");
                if((i+1)%seatsInRow==0){
                    System.out.println();
                }
            }
    }

    public void printEmptySeats(){
        System.out.println("-".repeat(90));
        List<Seat> seatList = new ArrayList<>(seats);
        for(int i=0; i<numberOfRows*seatsInRow; i++){
            if(seatList.get(i).reserved){
                System.out.printf("%-11s%-6s","-".repeat(11)," ");
                continue;
            }
            System.out.print(seatList.get(i) + "\t");
            if((i+1)%seatsInRow==0){
                System.out.println();
            }
        }
    }

    public boolean reserve(char row, int seatNumber){
        Seat seat = new Seat(row, seatNumber);
        seat = seats.floor(seat);
        if(seat.reserved || (!seats.contains(seat)) ){
            return false;
        }
        seat.reserved=true;

        return true;
    }


    public void reserveSet(int reservationNumberRequested, List<Character> rowsList, List<Integer> seatNumberList ) {
        List<Seat> seatsList= (List<Seat>) seats;
    }




    private static class Seat implements Comparable<Seat>{
        private char row;
        private int seatNumber;
        private String seatIdentifier;
        private boolean reserved;

        private Seat(char row, int seatNumber){
            this.row = row;
            this.seatNumber = seatNumber;
            reserved = false;
            seatIdentifier = seatNumber>= 100 ? row + seatNumber+""
                                 : seatNumber >=10  ? row+"0"+seatNumber
                                 :                    row+"00"+seatNumber;
        }

        @Override
        public String toString(){
            return "%-4s%-7s".formatted(  seatIdentifier + " ", (reserved ? "reserved" : "empty,") );
        }

        @Override
        public int compareTo(Seat o) {
            return seatIdentifier.compareTo(o.seatIdentifier);
        }
    }

}
