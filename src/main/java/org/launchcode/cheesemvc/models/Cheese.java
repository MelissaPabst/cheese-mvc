package org.launchcode.cheesemvc.models;

public class Cheese {

    private String name;
    private String description;

    //public Cheese(String aName, String aDescription){
        //name =aName;
        //description =aDescription;
    //}
    
    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
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
