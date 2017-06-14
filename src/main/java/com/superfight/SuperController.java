package com.superfight;

import com.superfight.service.SuperPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuperController {
  @Autowired
  private SuperPersonService superPersonService;

  @RequestMapping("/super-fight")
  public String superFight(ModelMap model) {

    model.addAttribute("superPersons", superPersonService.getSuperPersons());
    model.addAttribute("locations", superPersonService.getLocation());
    model.addAttribute("battles", superPersonService.getBattles());
    model.addAttribute("superTeams", superPersonService.getSuperTeam());
    return "super-fight";
  }

}