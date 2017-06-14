package com.superfight;

import com.superfight.service.SuperPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SuperController {
  @Autowired
  private SuperPersonService superPersonService;

  @RequestMapping("/super-people")
  public String getSuperPeople(ModelMap model, @RequestParam(value = "orderBy", required=false) String orderBy) {

    model.addAttribute("superPersons", superPersonService.getSuperPersons(orderBy));
    return "super-people";
  }

  @RequestMapping("/locations")
  public String getLocations(ModelMap model, @RequestParam(value = "orderBy", required=false) String orderBy) {
    model.addAttribute("locations", superPersonService.getLocation(orderBy));
    return "locations";
  }

  @RequestMapping("/battles")
  public String getBattles(ModelMap model, @RequestParam(value = "orderBy", required=false) String orderBy) {
    model.addAttribute("battles", superPersonService.getBattles(orderBy));
    return "battles";
  }

  @RequestMapping("/teams")
  public String getTeams(ModelMap model, @RequestParam(value = "orderBy", required=false) String orderBy) {
    model.addAttribute("superTeams", superPersonService.getSuperTeam(orderBy));
    return "teams";
  }

}