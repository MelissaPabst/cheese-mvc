package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;




@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "All Users");
        return "user/index";
    }



    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddForm(Model model) {
        model.addAttribute("title", "Add User");
        //skeleton user properties will help us render form properly
        //same as model.addAttribute(user new User()); default without "user" creates lower-case version of User class
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddForm(Model model, @ModelAttribute @Valid User newUser, Errors errors) {

        if (errors.hasErrors()){
            model.addAttribute("title", "Add User");
            model.addAttribute(newUser);
            model.addAttribute("username", newUser.getUsername());
            model.addAttribute("email", newUser.getEmail());
            return "user/add";
        }

        UserData.add(newUser);
        model.addAttribute("user", newUser);
        model.addAttribute("title", "All Users");
        return "redirect:";
    }

    @RequestMapping(value = "{userId}")
    public String userDetails(Model model, @PathVariable int userId) {
        User aUser = UserData.getById(userId);
        model.addAttribute("user", aUser);
        model.addAttribute("title", "User Details");
        return "user/details";
    }


    }





