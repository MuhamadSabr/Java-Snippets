package com.mmd;

import java.util.ArrayList;
import java.util.List;

public class Team<T extends Player>{  //This establishes what is known as "Upper Bound".
    // Means the passed type must be Player or extend or implement Player. In other words Player could be a class or an interface
    // T could be Player or subclass of it.
    private String teamName;
    private List<T> teamMembers = new ArrayList<>();
    private int totalWins=0, totalLosses=0, totalTies=0;
    public Team(String teamName) {
        this.teamName = teamName;
    }
    public void addTeamMember(T player){
        if(!teamMembers.contains(player)){ //Tests all the FootballPlayer's attributes for equality. They must match exactly
            teamMembers.add(player);
        }
    }
    public void listTeamMembers(){
        System.out.println(teamName + " Roster:");
        for(T el : teamMembers){
            System.out.println(el.name());  // U cannot use the generic type to refer to anything other than what is on Player
        }
    }
    public int ranking(){
        return (totalLosses*2) + totalTies+1;
    }
    public String setScore(int ourScore, int theirScore){
        String message = "Lost to";
        if(ourScore>theirScore){
            totalWins++;
            message = "Beat";
        } else if (theirScore==ourScore) {
            totalTies++;
            message = "Tied";
        }
        else {
            totalLosses++;
        }
        return message;
    }
    public String toString(){
        return "%s Ranked(%d)".formatted(teamName, ranking());
    }

}
