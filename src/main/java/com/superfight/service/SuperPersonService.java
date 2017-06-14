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

  public List<SuperPerson> getSuperPersons() {
    List<SuperPerson> result = jdbcTemplate.query("SELECT * FROM super_person", new RowMapper<SuperPerson>(){

      @Override
      public SuperPerson mapRow(ResultSet rs, int rowNum) throws SQLException {

        SuperPerson superPerson = new SuperPerson();
        superPerson.setId(rs.getInt("id"));
        superPerson.setName(rs.getString("name"));
        superPerson.setHealth(rs.getInt("health"));
        superPerson.setStrength(rs.getInt("strength"));
        superPerson.setArmour_rating(rs.getInt("armour_rating"));
        superPerson.setSpecial_move(rs.getString("special_move"));
        return superPerson;
      }
    });
    return result;
  }

  public List<Location> getLocation() {
    List<Location> result = jdbcTemplate.query("SELECT * FROM location", new RowMapper<Location>(){

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

  public List<Battle> getBattles() {
    List<Battle> result = jdbcTemplate.query("SELECT sp1.name Superhero1, sp2.name Superhero2, spw.name winner  "
        + "FROM battle b  "
        + "INNER JOIN super_person sp1 ON b.super_person_one_id = sp1.id  "
        + "INNER JOIN super_person sp2 ON b.super_person_two_id = sp2.id  "
        + "INNER JOIN super_person spw ON b.winner_id = spw.id  "
        + "ORDER BY spw.name DESC", new RowMapper<Battle>(){

      @Override
      public Battle mapRow(ResultSet rs, int rowNum) throws SQLException {

        Battle battle = new Battle();
        battle.setSuper_person_1(rs.getString("Superhero1"));
        battle.setSuper_person_2(rs.getString("Superhero2"));
        battle.setWinner(rs.getString("winner"));
        return battle;
      }
    });
    return result;
  }

  public List<SuperTeam> getSuperTeam() {
    List<SuperTeam> result = jdbcTemplate.query("SELECT COUNT(*) number_of_wins, st.name Team "
        + "FROM battle b "
        + "INNER JOIN super_person spw ON b.winner_id = spw.id "
        + "INNER JOIN super_team_super_person stsp ON b.winner_id = stsp.super_person_id "
        + "INNER JOIN super_team st ON stsp.super_team_id = st.id "
        + "GROUP BY st.name "
        + "ORDER BY number_of_wins DESC;", new RowMapper<SuperTeam>(){

      @Override
      public SuperTeam mapRow(ResultSet rs, int rowNum) throws SQLException {

        SuperTeam superTeam = new SuperTeam();
        superTeam.setNumberOfWins(rs.getInt("number_of_wins"));
        superTeam.setTeamName(rs.getString("Team"));

        return superTeam;
      }
    });
    return result;
  }

}
