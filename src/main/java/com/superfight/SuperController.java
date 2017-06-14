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

  @RequestMapping("/super-fight")
  public String superFight(ModelMap model, @RequestParam("orderBy") String orderBy) {

    model.addAttribute("superPersons", superPersonService.getSuperPersons(orderBy));
    model.addAttribute("locations", superPersonService.getLocation());
    model.addAttribute("battles", superPersonService.getBattles());
    model.addAttribute("superTeams", superPersonService.getSuperTeam());
    return "super-fight";
  }

}