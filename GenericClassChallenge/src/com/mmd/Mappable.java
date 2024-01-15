package com.mmd;

import java.util.Arrays;

public interface Mappable {
    void render();
    static double[] stringToLatLon(String location){
        var splits = location.split(",");
        double lat = Double.parseDouble(splits[0]);
        double lon = Double.parseDouble(splits[1]);
        return new double[]{lat, lon};
    }
}

abstract class Point implements Mappable{
    private double[] location = new double[2];
    public Point(String location){
        this.location = Mappable.stringToLatLon(location);
    }
    @Override
    public void render(){
        System.out.printf("Render %s as POINT (%s)%n", this, location());
    }

    private String location(){
        return Arrays.toString(location);
    }
}


abstract class Line implements Mappable{
    private double[][] locations;

    public Line(String... locations){
        this.locations = new double[locations.length][2]; // U r gonna have length elements of 2 elements;
        int index=0;
        for(String l : locations){
            this.locations[index++] = Mappable.stringToLatLon(l); // {lat, long} will be returned. They will be assigned to each 1st element
        }
    }

    @Override
    public void render(){
        System.out.printf("Render %s as LINE (%s)%n", this, locations());
    }

    private String locations(){
        return Arrays.deepToString(locations);
    }
}
