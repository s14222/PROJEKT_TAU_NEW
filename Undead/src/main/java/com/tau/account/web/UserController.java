package com.tau.account.web;

import com.tau.account.model.Undead;
import com.tau.account.model.User;
import com.tau.account.service.interfaces.SecurityService;
import com.tau.account.service.interfaces.UndeadService;
import com.tau.account.service.interfaces.UserService;
import com.tau.account.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UndeadService undeadService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        //wyswi. form
        //User user = userService.findByUsername("qkilled");
        User user = userService.findById(5L);
        //List<Undead> undeads = userService.findByUndeadList(user);

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/addUndead";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Undead> undeadList = undeadService.findAll();
        model.addAttribute("undeadList", undeadList);
        return "welcome";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String addUndead(Model model) {
        return "redirect:/addUndead";
    }
}
