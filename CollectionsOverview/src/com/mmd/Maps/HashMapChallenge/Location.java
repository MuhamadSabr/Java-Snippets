package com.mmd.Maps.HashMapChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Location {

    private String description;
    private final Map<Direction, String> nextPlaces;

    public Location(String description, Map<Direction, String> nextPlaces) {
        this.nextPlaces = new HashMap<>();
        this.description = description;
        this.nextPlaces.putAll( nextPlaces );
    }

    public String getPlacesToGo(){
        String result ="From here you can see:";
        for(Map.Entry<Direction, String> node : nextPlaces.entrySet()){
            result += "\n A " + node.getValue() + " to the " +node.getKey()+ "(" + node.getKey().name() +")" ;
        }
        return result;
    }

    @Override
    public String toString() {
        return "%s next places : %s".formatted(description, nextPlaces);
    }

    public String getDescription() {
        return description;
    }

    public String getNextLocation(Direction keyObject){

        return nextPlaces.getOrDefault(keyObject, "No such direction here");
    }

}



enum Direction{
    E, N, S, W;
    private final String[] directions = {"East", "North", "South", "West"};

    @Override
    public String toString() {
        return this.directions[this.ordinal()];
    }


}