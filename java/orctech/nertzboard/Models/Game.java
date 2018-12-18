package orctech.nertzboard.Models;

import java.util.ArrayList;

public class Game {
    private int numTeams = 5;
    int roundNumber = 0;
    private ArrayList<Team> teams = new ArrayList<>(0);
    public Game() {
        for (int i = 0; i < numTeams; i++) {
            teams.add(new Team("Team "+i, 0));
        }
    }
    public Game(String[] names) {
        numTeams = names.length;
        teams = new ArrayList<>(numTeams);
        for (int i = 0; i < numTeams; i++) {
            teams.add(new Team(names[i], 0));
        }
    }

    public void setScores(ArrayList<Integer> scores) {
        for (int i = 0; i < numTeams; i++) {
            teams.get(i).setScore(scores.get(i));
        }
    }
    public int getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
    public Team getTeam(int index) {
        return teams.get(index);
    }
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void scoreRound(ArrayList<Integer> scores) {
        for (int i = 0; i < numTeams; i++) {
            teams.get(i).addScore(scores.get(i));
        }
    }
}
