package org.launchcode.cheesemvc.controllers;

//import org.launchcode.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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

    //ArrayList was moved from below to make list accessible to below methods
    //data will only exist while application is running
    //not a sub for a database
    //static ArrayList<String> cheeses = new ArrayList<>();
    //storing cheese in hashmap allows cheeses to be overwritten if there are two cheeses of same name
    //use POJO (java class) to store data instead
    static HashMap<String, String> cheeses = new HashMap<>();
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


        //create arraylist to pass in list of cheeses to the view
        //ArrayList<String> cheeses = new ArrayList<>();
        //hardcoded cheeses, but we want user to be able to enter cheeses
        //cheeses.add("cheddar");
        //cheeses.add("parmesan");
        //cheeses.add("brie");

        //now we want to pass this into the view
        //now we're passing an object
        model.addAttribute("cheeses", cheeses);
        //now we're passing a string
        model.addAttribute("title", "My List of Cheeses");
        return "cheese/index";
    }


    //controller method to display form to add cheese template
    //display of form will be GET request
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    //handler to process form
    //processing of form will be POST request
    @RequestMapping(value = "add", method = RequestMethod.POST)
    //get data out of form via HttpServletRequest
    //public String processAddCheeseForm(HttpServletRequest request) {
        //"cheeseName" matches data attribute name in input in template
        //String cheeseName = request.getParameter("cheeseName");
    //}
    //ORRRRR get data out of form via @RequestParam (More "Spring-like")
    //controller-handler expects to be passed a string named cheeseName
    //method param needs to match value in form
    //add @requestparam cheeseDiscription for use of hashmap
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {
        //take cheese name and add to list of cheeses
        //cheeses.add(cheeseName);
        cheeses.put(cheeseName, cheeseDescription);
        //redirect to /cheese, redirect is left blank to redirect to "cheese" controller
        return "redirect:";

    }

    //to display remove cheese form
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeses.keySet());
        return "cheese/remove";
    }

    //to process remove cheese form
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheese) {

        for(String c : cheese) {
            cheeses.remove(c);
        }

        return "redirect:";
    }
}
