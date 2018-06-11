package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class UserData {


    //methods to access list of users via add, getAll, and getById

    static ArrayList<User> users = new ArrayList<>();

    //add
    public static void add (User newUser){
        users.add(newUser);
    }

    //get all
    public static ArrayList<User> getAll() {
        return users;
    }


    //get user based on its id
    public static User getById(int id) {
        //loop through list, look for user
        //declare User local variable
        //initialize it to be null
        User theUser = null;

        for (User candidateUser : users) {
            if (candidateUser.getUserId() == id) {
                theUser = candidateUser;
            }
        }

        return theUser;
    }







}
