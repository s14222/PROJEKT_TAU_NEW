package com.tau.account.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LayoutController {

    @RequestMapping(value="/layout", method=RequestMethod.GET)
    public String getLayout(Model model) {
        //model.addAttribute("undeadForm", new Undead());

        //List<Undead> undeadList = undeadService.findAll();
        model.addAttribute("un", "und");

        return "layout";
    }
}
