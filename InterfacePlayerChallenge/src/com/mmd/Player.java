package com.mmd;

import java.util.ArrayList;
import java.util.List;

public class Player implements ISaveable{

    private String name;
    private String weapon;
    private int    hitPoints;
    private int    strength;

    public Player(String name, int hitPoints, int strength){
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        weapon = "Sword";
    }

    public String getName(){
        return name;
    }
    public String getWeapon(){
        return weapon;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public int getStrength(){
        return strength;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setWeapon(String weapon){
        this.weapon = weapon;
    }
    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }
    public void setStrength(int strength){
        this.strength = strength;
    }

    public List<String> write(){
        return List.of(name, weapon, String.valueOf( hitPoints ), String.valueOf(strength) ) ;

    }
    public void read(List<String> list){
        if(list!=null && list.size()>0){
            name = list.get(0);
            hitPoints = Integer.parseInt(list.get(1));
            strength = Integer.parseInt(list.get(2));
            weapon = list.get(3);
        }
    }

    public String toString(){
        //return "Player{name='%s', hitPoints=%d, strength=%d, weapon='%s'}".formatted(name, hitPoints, strength, weapon);
        return  "Player{name='" +name+ "', hitPoints=" +hitPoints+ ", strength=" + strength +", weapon='" + weapon +''"}";
    }
}