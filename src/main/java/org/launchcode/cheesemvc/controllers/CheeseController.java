package org.launchcode.cheesemvc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*add bootRun {
        sourceResources sourceSets.main
        }
to bottom of build.gradle to allow templates to refresh without stopping server (for Spring 2.0)*/


@Controller
//@RequestMapping added at top of controller class to specify
// a base request path for every method in the controller
// now all will be /cheese
@RequestMapping("cheese")
public class CheeseController {


    //request path /cheese
    @RequestMapping(value = "")
    //@ResponseBody removed to pass control to a view
    //give name of template i want to render, "index", leave off .html
    //templates MUST be in templates folder, so Spring bOot can find them (default location)
    //index is a static template; no data is passed to it
    public String index(Model model) {
        //added Model model parameter, used to pass data to template (view)
        //modify "index" to be "/cheese/index" after making a cheese directory in templates
        //added th:text attribute to template

        model.addAttribute("title", "My List of Cheeses");
        return "cheese/index";
    }

}
