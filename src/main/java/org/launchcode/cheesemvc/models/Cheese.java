package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    //@NotNull actually passes an empty string--Define @size and add message.
    @Size(min=1, message="Description must not be empthy")
    private String description;

    //enum class. create enum type and add to another class
    //private CheeseType type = CheeseType.HARD;
    //set type of cheeses through view and controller instead of CheeseType.HARD
    private CheeseType type;

    //unique number to identify each object created from cheese class as different
    private int cheeseId;
    private static int nextId = 1;

    //public Cheese(String aName, String aDescription){
        //name =aName;
        //description =aDescription;
    //}

    public Cheese(String name, String description) {
        //call default constructor for given class
        //must be called on first line to initialize id field
        this();
        this.name = name;
        this.description = description;
    }


    //default constructor
    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }


    public int getCheeseId() {
        return cheeseId;
    }

    private void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }
}
