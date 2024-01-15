package com.mmd.Maps.HashMapChallenge;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<String, Location> map = new HashMap<>();
        Map<Direction, String> nextPlaces = new HashMap<>();

        nextPlaces.put(Direction.W, "valley");
        nextPlaces.put(Direction.N, "well house");
        map.put("stream", new Location("near a stream with a rocky bed", nextPlaces));

        nextPlaces.clear();
        nextPlaces.put(Direction.W, "forest");
        nextPlaces.put(Direction.S, "well house");
        map.put("lake", new Location("by an alpine lake surrounded by wildflowers", nextPlaces));

        nextPlaces.clear();
        nextPlaces.put(Direction.E, "lake");
        nextPlaces.put(Direction.S, "road");
        map.put("forest", new Location("at the edge of a thick dark forest", nextPlaces));

        nextPlaces.clear();
        nextPlaces.put(Direction.N, "road");
        nextPlaces.put(Direction.W, "hill");
        nextPlaces.put(Direction.E, "stream");
        map.put("valley", new Location("in a forest valley beside a tumbling stream", nextPlaces));

        nextPlaces.clear();
        nextPlaces.put(Direction.W, "road");
        nextPlaces.put(Direction.N, "lake");
        nextPlaces.put(Direction.S, "stream");
        map.put("well house", new Location("inside a well house for a small spring", nextPlaces));

        nextPlaces.clear();
        nextPlaces.put(Direction.N, "forest");
        nextPlaces.put(Direction.E, "road");
        map.put("hill", new Location("on top of hill with a view in all directions", nextPlaces));


        nextPlaces.clear();

        nextPlaces.put(Direction.W, "hill");
        nextPlaces.put(Direction.E, "well house");
        nextPlaces.put(Direction.S, "valley");
        nextPlaces.put(Direction.N, "forest");
        map.put("road", new Location("at the end of the road", nextPlaces));

        System.out.println("*** You are standing " + map.get("road").getDescription()+" ***");
        System.out.println(map.get("road").getPlacesToGo());
        System.out.println("Select your compass direction (Q to quit) >>");
        List<String> direction = new ArrayList<>();
        direction.add("road");
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("q")) break;
            if(!"NWES".contains(String.valueOf(input.toUpperCase().charAt(0)))){
                System.out.println("Invalid direction...");
                continue;
            }
            String nextLocation = map.get(direction.get(direction.size()-1))
                    .getNextLocation(Direction.valueOf(String.valueOf(input.toUpperCase().charAt(0))));
            direction.add(nextLocation);
            System.out.println("*** You are standing " + map.get(nextLocation).getDescription()+ " ***");
            System.out.println(map.get(nextLocation).getPlacesToGo());
            System.out.println("Select your compass direction (Q to quit) >>");
        }
        System.out.println("End of the game...");
    }

}
