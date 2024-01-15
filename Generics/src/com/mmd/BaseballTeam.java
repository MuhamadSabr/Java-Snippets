package com.mmd;

import java.util.ArrayList;
import java.util.List;
public class BaseballTeam{
    private String teamName;
    private List<BaseballPlayer> teamMembers = new ArrayList<>();
    private int totalWins=0, totalLosses=0, totalTies=0;
    public BaseballTeam(String teamName) {
        this.teamName = teamName;
    }
    public void addTeamMember(BaseballPlayer player){
        if(!teamMembers.contains(player)){ //Tests all the BaseballPlayer's attributes for equality. They must match exactly
            teamMembers.add(player);
        }
    }
    public void listTeamMembers(){
        System.out.println(teamName + " Roster:");
        System.out.println(teamMembers);
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
