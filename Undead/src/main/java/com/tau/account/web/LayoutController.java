package com.tau.account.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LayoutController {

    @RequestMapping(value="/layout", method=RequestMethod.GET)
    public String getLayout(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isLoggedIn = false;

        if(!authentication.getPrincipal().equals("anonymousUser")){
            isLoggedIn = true;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);

        return "layout";
    }
}
