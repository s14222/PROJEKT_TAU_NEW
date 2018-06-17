package com.tau.account.web;

import com.tau.account.model.Undead;
import com.tau.account.service.interfaces.UndeadService;
import com.tau.account.validator.UndeadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UndeadController {

    @Autowired
    private UndeadService undeadService;

    @Autowired
    private UndeadValidator undeadValidator;

    @RequestMapping(value = "/addUndead", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("undeadForm", new Undead());

        List<Undead> undeadList = undeadService.findAll();
        model.addAttribute("undeadList", undeadList);

        return "addUndead";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String edit(Model model, Undead undead){

        undeadService.update(undead);

        return "addUndead";
    }

    @RequestMapping(value = "/delete/{undeadId}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable String undeadId){

        Long id = Long.parseLong(undeadId);

        Undead undead = undeadService.findById(id);

        undeadService.delete(undead);

        return "redirect:/addUndead";
    }

    @RequestMapping(value = "/saveUndead", method = RequestMethod.POST)
    public String save(@ModelAttribute("undeadForm") Undead undead, BindingResult bindingResult) {

        undeadValidator.validate(undead, bindingResult);

        if (bindingResult.hasErrors()) {
            return "addUndead";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();

        String username = user.getUsername();
        undead.setUserName(username);

        undeadService.save(undead);

        return "redirect:/addUndead";
    }
}
