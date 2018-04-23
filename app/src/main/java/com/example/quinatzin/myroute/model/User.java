package com.example.quinatzin.myroute.model;

import java.util.HashMap;

/**
 * Created by quinatzin on 4/18/2018.
 */

public class User {
    private String name;
    private String email;
    private HashMap<String, Object> timestampJoined;
    private boolean hasLoggedInWithPassword;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /* public User(String name, String email, HashMap<String, Object> timestampJoined, boolean hasLoggedInWithPassword) {
         this.name = name;
         this.email = email;
         this.timestampJoined = timestampJoined;
         this.hasLoggedInWithPassword = hasLoggedInWithPassword;
     }
     */
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    /*public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    public boolean isHasLoggedInWithPassword() {
        return hasLoggedInWithPassword;
    }
    */
}


