package com.superfight.model;

public class Battle {

  private int id;
  private String super_person_1;
  private String super_person_2;
  private String location;
  private String winner;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSuper_person_1() {
    return super_person_1;
  }

  public void setSuper_person_1(String super_person_1) {
    this.super_person_1 = super_person_1;
  }

  public String getSuper_person_2() {
    return super_person_2;
  }

  public void setSuper_person_2(String super_person_2) {
    this.super_person_2 = super_person_2;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getWinner() {
    return winner;
  }

  public void setWinner(String winner) {
    this.winner = winner;
  }
}
