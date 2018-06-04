package org.launchcode.cheesemvc.models;

import java.util.ArrayList;


//mimics behavior of db until we can learn db
//all are static, no constructor needed
public class CheeseData {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    //get all
    public static ArrayList<Cheese> getAll() {
        return cheeses;
    }

    //add
    public static void add (Cheese newCheese){
        cheeses.add(newCheese);
    }

    //remove by id
    public static void remove (int id) {
        Cheese cheeseToRemove = getById(id); {
            cheeses.remove(cheeseToRemove);
        }
    }



    //get cheese based on its id
    public static Cheese getById(int id) {
        //loop through list, look for cheese
        //declare Cheese local variable
        //initialize it to be null
        Cheese theCheese = null;

        for (Cheese candidateCheese : cheeses) {
            if (candidateCheese.getCheeseId() == id) {
                theCheese = candidateCheese;
            }
        }

        return theCheese;
    }
}
