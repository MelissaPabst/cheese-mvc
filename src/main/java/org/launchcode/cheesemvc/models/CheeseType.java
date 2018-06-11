package org.launchcode.cheesemvc.models;


//enum is a special type of class, a listing
public enum CheeseType {

    HARD ("Hard"),
    SOFT ("Soft"),
    FAKE ("Fake");

    //final because once they are created we don't want them to be changed
    private final String name;

    CheeseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
