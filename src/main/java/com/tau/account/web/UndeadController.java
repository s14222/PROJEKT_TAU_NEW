package com.tau.account.web;

import com.tau.account.model.Undead;
import com.tau.account.service.interfaces.UndeadService;
import com.tau.account.validator.UndeadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        return "undeadList";
    }

    @RequestMapping(value = "/undeadList", method = RequestMethod.GET)
    public String UndeadList(Model model) {

        List<Undead> undeadList = undeadService.findAll();
        model.addAttribute("undeadList", undeadList);

        return "undeadList";
    }

    @RequestMapping(value = "/addUndead", method = RequestMethod.POST)
    public String save(@ModelAttribute("undeadForm") Undead undead, BindingResult bindingResult) {

        undeadValidator.validate(undead, bindingResult);

        if (bindingResult.hasErrors()) {
            return "undeadList";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();

        String username = user.getUsername();
        undead.setUserName(username);

        undeadService.save(undead);

         return "";
    }

}
