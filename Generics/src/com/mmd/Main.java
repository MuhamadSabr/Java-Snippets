package com.mmd;

import java.util.ArrayList;
import java.util.List;

interface Player {
    String name();
}
record BaseballPlayer(String name, String position) implements Player {} // records come with an implicit equals method.
record FootballPlayer(String name, String position) implements Player {}

public class Main {
    public static void main(String[] args) {
//        BaseballTeam Erbil = new BaseballTeam("Erbil Team");
//        BaseballTeam Duhok = new BaseballTeam("Duhok Team");
//        scoreResult(Erbil, 5, Duhok, 3);
//        BaseballPlayer mohammed = new BaseballPlayer("Mohammed S. Othman", "Right Fielder");
//        BaseballPlayer ahmed    = new BaseballPlayer("Ahmed Jalal", "Left Fielder");
//        Erbil.addTeamMember(mohammed);
//        Duhok.addTeamMember(ahmed);
//        Erbil.listTeamMembers();
//        Duhok.listTeamMembers();

        Team<FootballPlayer> erbilFootballTeam = new Team<>("Erbil Team");
        Team<FootballPlayer> duhokFootballTeam = new Team("Duhok Team");
        Team<BaseballPlayer> erbilBaseballTeam = new Team<>("Erbil Team");
        Team<BaseballPlayer> duhokBaseballTeam = new Team("Duhok Team");
        scoreResult(erbilFootballTeam, 5, duhokFootballTeam, 3);
        scoreResult(erbilBaseballTeam, 3, duhokBaseballTeam, 3);
        FootballPlayer mohammedF = new FootballPlayer("Mohammed S.", "Right Fielder");
        BaseballPlayer mohammedB= new BaseballPlayer("Mohammed S. Othman", "Right Fielder");
        erbilFootballTeam.addTeamMember(mohammedF);
        erbilFootballTeam.listTeamMembers();
        duhokBaseballTeam.addTeamMember(mohammedB);
        duhokBaseballTeam.listTeamMembers();

//        Team<String> erStr = new Team<>("er String type");
//        Team<String> duStr = new Team<>("du String type");
//        scoreResult(erStr,1, duStr, 2);
    }

    public static void scoreResult(BaseballTeam team1, int team1Score,
                                   BaseballTeam team2, int team2Score){
        String message = team1.setScore(team1Score, team2Score);
        team2.setScore(team2Score, team1Score);
        System.out.printf("%s %s %s%n", team1, message, team2);
    }

    public static void scoreResult(Team team1, int team1Score, //It is strongly encouraged to specify the parameter type
                                   Team team2, int team2Score){// When referencing or instantiating a generic type. Not Row Use of it.
        String message = team1.setScore(team1Score, team2Score);
        team2.setScore(team2Score, team1Score);
        System.out.printf("%s %s %s%n", team1, message, team2);
    }
}