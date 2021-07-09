package com.example.uniskills.model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class User implements Serializable {

    static int UserID;
    static String Username;
    static String Password;
    static String Name;
    static String Email;
    int UserExperiencePoints;
    int Currency;

    public User(int userID, String username, String password, String name, String email, int userExperiencePoints, int currency) {
        UserID = userID;
        Username = username;
        Password = password;
        Name = name;
        Email = email;
        UserExperiencePoints = userExperiencePoints;
        Currency = currency;
    }

    public static int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public static String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public static String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public static String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public static String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getUserExperiencePoints() {
        return UserExperiencePoints;
    }

    public void setUserExperiencePoints(int userExperiencePoints) {
        UserExperiencePoints = userExperiencePoints;
    }

    public int getCurrency() {
        return Currency;
    }

    public void setCurrency(int currency) {
        Currency = currency;
    }
}

