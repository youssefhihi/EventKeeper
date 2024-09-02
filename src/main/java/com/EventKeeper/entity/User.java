package com.EventKeeper.entity;
import com.EventKeeper.enums.role;

public class User {
    private static int uniqueID = 0;
    private int userID;
    private String username;
    private String password;
    private role role;

    public int getUserID() {
        return userID;
    }

  
    public User(String username,String password, role role) {
        this.userID = uniqueID++;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public role getRole() {
        return role;
    }

    public void setRole(role role) {
        this.role = role;
    }
}
