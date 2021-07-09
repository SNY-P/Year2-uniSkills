package com.example.uniskills.controllers;

import android.util.Log;

import com.example.uniskills.Console;
import com.example.uniskills.model.User;
import com.example.uniskills.services.UserService;

public class UserController{
    User[] userControllerArray = new User[57];

    public String loginUser(User[] loginArray, String username, String password){
        String message = null;
        User tempUser;
        for (int i = 0; i <loginArray.length; i++){
            tempUser = loginArray[i];
            try{
                if(tempUser.getUsername().equals(username)){
                    if(tempUser.getPassword().equals(password)){
                        message = "Login Successful";
                    }
                }
                else{
                    message = "Username or Password is incorrect";
                }
            }catch (Exception e){
                Log.e("sign in error", String.valueOf(e));
            }

        }

        return message;
    }

    public static String signUpUser(User[] users, String username, String name, String email, String passwordOne, String passwordTwo){
        String message;
        User[] saved = null;
        UserService us = new UserService();
        int nextUserID = 0;
        if(username.isEmpty() || email.isEmpty() || name.isEmpty() || passwordOne.isEmpty() || passwordTwo.isEmpty()){
            message = "Please fill in the empty boxes";
        }
        else if(!(email.contains("@")) || !(email.contains("."))){
            message = "Email must contain @ or .";
        }
        else if(passwordOne.length() < 8){
            message = "Password must contain more than 8 characters";
        }
        else if(!passwordTwo.equals(passwordOne)){
            message = "Please make sure the password matches" + passwordOne + passwordTwo;
        }
        else{
            User user = new User(nextID(users), username, passwordTwo, name, email, 0,0);
            saved = us.insertUser(users, user);
            message = "Account Created";
        }

        us.transferInformation(saved);
        return message;
    }

    public static int nextID(User[] users){
        int id = 0;
        for (int i = 0; i < users.length; i++){
            if (users[i].getUserID() > id){
                id = users[i].getUserID();
            }
        }
        return id +1;
    }

//    public static String loginUser(String username, String password ){
//        String message = null;
//        UserService us = new UserService();
//        us.selectAllSql(User.users);
//        for(User u:User.users){
//            if((u.getUsername().toLowerCase()).equals(username.toLowerCase())){
//                if((u.getPassword()).equals(password)){
//                    message = "Login Successful";
//                }
//                else{
//                    message = "Incorrect Password";
//                }
//            }
//            else{
//                message = "Username is incorrect";
//            }
//        }
//        return message;
//    }

//    public User getUser(String username, String password) {
//        UserService us = new UserService();
//        us.selectAllSql(User.users);
//        for (User u : User.users) {
//            if ((u.getUsername().toLowerCase()).equals(username.toLowerCase())) {
//                if ((u.getPassword()).equals(password)) {
//                    return u;
//                }
//            }
//        }
//        return null;
//    }

//    public static String signUpUser(String username, String name, String email, String passwordOne, String passwordTwo){
//        String message = null;
//        String saved = null;
//        UserService us = new UserService();
//        if(username.isEmpty() || email.isEmpty() || name.isEmpty() || passwordOne.isEmpty() || passwordTwo.isEmpty()){
//            message = "Please fill in the empty boxes";
//        }
//        else if(!(email.contains("@")) || !(email.contains("."))){
//            message = "Email must contain @ or .";
//        }
//        else if(passwordOne.length() < 8){
//            message = "Password must contain more than 8 characters";
//        }
//        else if(!passwordTwo.equals(passwordOne)){
//            message = "Please make sure the password matches" + passwordOne + passwordTwo;
//        }
//        else{
//            User user = new User(User.nextId(), username, passwordTwo, name, email, 0,0);
//            saved = us.insertSql(user);
//            message = "Account Created";
//        }
//        Console.state(saved);
//        return message;
//    }
}