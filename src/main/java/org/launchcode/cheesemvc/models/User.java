package org.launchcode.cheesemvc.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import org.hibernate.validator.constraints.Email;

public class User {

    @NotNull
    @Size(min=5, max=15, message="Username must be at least five characters, and no more than 15 characters.")
    private String username;

    @Email(message="Not a valid email.")
    private String email;

    @NotNull(message="Password required.")
    @Size(min=6, message="Password must be at least 6 characters.")
    private String password;

    @NotNull(message="Passwords do not match.")
    private String verifyPassword;

    private int userId;
    private static int nextId = 1;
    private final Date createDate;

    public User() {
        userId = nextId;
        createDate = new Date();
        nextId++;
    }


    public User (String username, String email, String password) {
        //call default constructor for given class with this()
        //must be called on first line to initialize id field
        // "this" signifies field of class, not method input
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.verifyPassword = verifyPassword;
    }


    //default constructor
    //public User() {
    //}



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
        checkPassword();
    }

    public int getUserId() { return userId; }

    private void setUserId(int userId) { this.userId = userId; }

    public Date getCreateDate() {
        return createDate;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

    public void checkPassword() {
        if (password!=null &&verifyPassword!=null && !password.equals(verifyPassword)) {
            verifyPassword = null;
        }
    }



}
