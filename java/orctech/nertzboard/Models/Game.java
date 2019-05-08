package orctech.nertzboard.Models;

import java.util.ArrayList;

public class Game {
    private int numTeams = 0;
    int roundNumber = 0;
    private ArrayList<Team> teams;

    public Game(String[] names) {
        numTeams = names.length;
        teams = new ArrayList<>(numTeams);
        for (int i = 0; i < numTeams; i++) {
            if(names[i].trim().length() == 0) {
                names[i] = "Team #" + i;
            }
            teams.add(new Team(names[i]));
        }
    }

    public void setScores(ArrayList<Integer> scores) {
        for (int i = 0; i < numTeams; i++) {
            teams.get(i).setScore(scores.get(i));
        }
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }
    public void addScores(ArrayList<Integer> scores) {
        for (int i = 0; i < numTeams; i++) {
            teams.get(i).addScore(scores.get(i));
        }
    }
}
