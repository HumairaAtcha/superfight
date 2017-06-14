package com.superfight.model;

public class SuperTeam {

  private int id;
  private int numberOfWins;
  private String teamName;

  public int getNumberOfWins() {
    return numberOfWins;
  }

  public void setNumberOfWins(int numberOfWins) {
    this.numberOfWins = numberOfWins;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String name) {
    this.teamName = name;
  }
}
