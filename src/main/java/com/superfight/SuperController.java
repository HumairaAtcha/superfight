package com.superfight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuperController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @RequestMapping("/super-fight")
  public String helloWorld(ModelMap model) {
    List<SuperPerson> result = jdbcTemplate.query("SELECT * FROM super_person", new RowMapper<SuperPerson>(){

      @Override
      public SuperPerson mapRow(ResultSet rs, int rowNum) throws SQLException {

          SuperPerson superPerson = new SuperPerson();
          superPerson.setName(rs.getString("name"));
          superPerson.setHealth(rs.getInt("health"));
          superPerson.setStrength(rs.getInt("strength"));
          superPerson.setArmour_rating(rs.getInt("armour_rating"));
          superPerson.setSpecial_move(rs.getString("special_move"));
          return superPerson;
      }
    });
    model.addAttribute("result", result);
    return "super-fight";
  }

}