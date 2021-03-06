package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    //static HashMap<String, String> cheeses = new HashMap<>();

    //refactor to use ArrayList and Cheese class added to model
    //violates MVC model to have arraylist in controller
    //static ArrayList<Cheese> cheeses = new ArrayList<>();

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
        model.addAttribute("cheeses", CheeseData.getAll());
        //now we're passing a string
        model.addAttribute("title", "My List of Cheeses");
        return "cheese/index";
    }


    //controller method to display form to add cheese template
    //display of form will be GET request
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {

        model.addAttribute("title", "Add Cheese");
        //build skeleton cheese to use its properties to render the form properly
        model.addAttribute(new Cheese());
        //pass in new object to hold cheese types, will return an array of cheesetypes, can use array in view
        model.addAttribute("cheeseTypes", CheeseType.values());
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
    //@Valid validates based on @NotNull etc.
    //User errors to check if model was validated properly
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {

        //boolean; if errors in errors object
        if(errors.hasErrors()) {
            //render add form again if errors, errors displayed in form
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }
        //take cheese name and add to list of cheeses
        //cheeses.add(cheeseName);
        //removed for model binding
        //Cheese newCheese = new Cheese(cheeseName, cheeseDescription);
        CheeseData.add(newCheese);
        //put statement removed; no longer using hashmap.
        //cheeses.put(cheeseName, cheeseDescription);
        //redirect to /cheese, redirect is left blank to redirect to "cheese" controller
        return "redirect:";

    }

    //to display remove cheese form
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", CheeseData.getAll());
        //model.addAttribute("cheeses", cheeses.keySet());
        return "cheese/remove";
    }

    //to process remove cheese form
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        //no longer works removing by name because we are now using an arraylist
        //for(String c : cheese) {
            //cheeses.remove(c);
        //}

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
    }

    //method to display edit form
    //@PathVariable allows "cheeseId" to be passed into URL
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        //ask CheeseData for the object with the given cheeseId and put it in the model
        //store cheese in temporary local variable
        Cheese theCheese = CheeseData.getById((cheeseId));
        model.addAttribute("cheese", theCheese);
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("title", "Edit Cheese " + theCheese.getName() + " (id=" + theCheese.getCheeseId() + ")");
        return "cheese/edit";

    }

    //method to process edit form
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int cheeseId, @ModelAttribute @Valid Cheese aCheese, Errors errors, Model model) {

        if(errors.hasErrors()) {
            //render edit form again if errors, errors displayed in form
            Cheese theCheese = CheeseData.getById(cheeseId);
            model.addAttribute("cheeseTypes", CheeseType.values());
            model.addAttribute("title", " Edit cheese" + theCheese.getName() + " (id=" + theCheese.getCheeseId() + ")");
            return "cheese/edit";
        }

        Cheese c = CheeseData.getById(cheeseId);
        c.setName(aCheese.getName());
        c.setDescription(aCheese.getDescription());
        c.setType(aCheese.getType());
        c.setRating(aCheese.getRating());
        return "redirect:/cheese";
    }
}
