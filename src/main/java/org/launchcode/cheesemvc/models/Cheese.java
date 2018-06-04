package org.launchcode.cheesemvc.models;

public class Cheese {

    private String name;
    private String description;
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

    public void setCheeseId(int cheeseId) {
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
}
