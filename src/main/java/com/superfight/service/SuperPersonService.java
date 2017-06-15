package com.superfight.service;

import com.superfight.model.Battle;
import com.superfight.model.Location;
import com.superfight.model.SuperPerson;
import com.superfight.model.SuperTeam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class SuperPersonService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<SuperPerson> getSuperPersons(String orderBy) {
    String query = "SELECT COUNT(*) number_of_wins, spw.name, spw.health health, spw.strength strength, spw.armour_rating armour_rating, spw.special_move special_move, spw.id id\n"
        + "FROM battle b\n"
        + "\tJOIN super_person spw ON b.winner_id = spw.id\n"
        + "GROUP BY name, health, strength, armour_rating, special_move, id\n"
        + "ORDER BY " + orderBy + " ASC";
    List<SuperPerson> result = jdbcTemplate.query(query, new RowMapper<SuperPerson>(){

      @Override
      public SuperPerson mapRow(ResultSet rs, int rowNum) throws SQLException {

        SuperPerson superPerson = new SuperPerson();
        superPerson.setId(rs.getInt("id"));
        superPerson.setName(rs.getString("name"));
        superPerson.setHealth(rs.getInt("health"));
        superPerson.setStrength(rs.getInt("strength"));
        superPerson.setArmour_rating(rs.getInt("armour_rating"));
        superPerson.setSpecial_move(rs.getString("special_move"));
        superPerson.setWins(rs.getInt("number_of_wins"));
        return superPerson;
      }
    });
    return result;
  }

  public List<Location> getLocation(String orderBy) {
    String query = "SELECT * FROM location ORDER BY " + orderBy + " ASC";
    List<Location> result = jdbcTemplate.query(query, new RowMapper<Location>(){

      @Override
      public Location mapRow(ResultSet rs, int rowNum) throws SQLException {

        Location location = new Location();
        location.setId(rs.getInt("id"));
        location.setName(rs.getString("name"));
        location.setCity(rs.getString("city"));
        return location;
      }
    });
    return result;
  }

  public List<Battle> getBattles(String orderBy) {
    String query = "SELECT sp1.name Superhero1, sp2.name Superhero2, spw.name winner, b.id id, l.name location  "
        + "FROM battle b  "
        + "INNER JOIN super_person sp1 ON b.super_person_one_id = sp1.id  "
        + "INNER JOIN super_person sp2 ON b.super_person_two_id = sp2.id  "
        + "INNER JOIN super_person spw ON b.winner_id = spw.id  "
        + "INNER JOIN location l ON b.location_id = l.id "
        + "ORDER BY " + orderBy + " DESC";
    List<Battle> result = jdbcTemplate.query(query, new RowMapper<Battle>(){

      @Override
      public Battle mapRow(ResultSet rs, int rowNum) throws SQLException {

        Battle battle = new Battle();
        battle.setId(rs.getInt("id"));
        battle.setSuper_person_1(rs.getString("Superhero1"));
        battle.setSuper_person_2(rs.getString("Superhero2"));
        battle.setLocation(rs.getString("location"));
        battle.setWinner(rs.getString("winner"));
        return battle;
      }
    });
    return result;
  }

  public List<SuperTeam> getSuperTeam(String orderBy) {
    String query = "SELECT COUNT(*) number_of_wins, st.name Team, st.id id "
        + "FROM battle b "
        + "INNER JOIN super_person spw ON b.winner_id = spw.id "
        + "INNER JOIN super_team_super_person stsp ON b.winner_id = stsp.super_person_id "
        + "INNER JOIN super_team st ON stsp.super_team_id = st.id "
        + "GROUP BY st.name, id "
        + "ORDER BY " + orderBy + " DESC";
    List<SuperTeam> result = jdbcTemplate.query(query, new RowMapper<SuperTeam>(){

      @Override
      public SuperTeam mapRow(ResultSet rs, int rowNum) throws SQLException {

        SuperTeam superTeam = new SuperTeam();
        superTeam.setId(rs.getInt("id"));
        superTeam.setNumberOfWins(rs.getInt("number_of_wins"));
        superTeam.setTeamName(rs.getString("Team"));

        return superTeam;
      }
    });
    return result;
  }

}
