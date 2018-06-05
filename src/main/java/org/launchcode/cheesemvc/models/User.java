package org.launchcode.cheesemvc.models;

public class User {

    private String username;
    private String email;
    private String password;


    public User (String username, String email, String password) {
        //call default constructor for given class
        //must be called on first line to initialize id field
        //this();
        this.username = username;
        this.email = email;
        this.password = password;
    }


    //default constructor
    public User() {
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
