package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.web.bind.annotation.PathVariable;



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
        return "user/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddForm(Model model, @ModelAttribute User user, String verify) {

        if (user.getPassword() != null && user.getPassword().equals(verify)) {
            model.addAttribute("name", user.getUsername());
            UserData.add(user);
            return "user/index";
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("error", "Passwords do not match");
        return "user/add";

    }

    @RequestMapping(value = "{userId}")
    public String userDetails(Model model, @PathVariable int userId) {
        User aUser = UserData.getById(userId);
        model.addAttribute("user", aUser);
        model.addAttribute("title", "User Details");
        return "user/details";
    }


    }





