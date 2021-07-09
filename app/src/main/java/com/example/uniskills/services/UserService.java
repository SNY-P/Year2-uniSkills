package com.example.uniskills.services;

import android.util.Log;
import com.example.uniskills.model.User;

public class UserService{
    User[] allUsers;

    public void transferInformation(User[] toClone){
        allUsers = toClone.clone();
        Log.i("transferInformation()","Transferred!");
    }

    public User[] insertUser(User[] currentArray, User toInsert){
        for (int i = 0; i < currentArray.length;i++){
            if (currentArray[i] == null){
                currentArray[i] = toInsert;
            }
            else{
                Log.i("Array Size","Position full! Trying next");
            }
        }
        allUsers = currentArray.clone();
        return currentArray;
    }

    public User[] getAllUsers(){
        return allUsers;
    }

    public User selectUser(User[] array,String username){
        User toReturn = null;
        User tempValue;
        for (int i = 0;i<array.length;i++){
            tempValue = array[i];
            if (tempValue.getUsername().equals(username)){
                toReturn = tempValue;
            }
        }
        return toReturn;
    }











////    public static String selectAllSql(List<User> targetList) {
////        targetList.clear();
////        try {
////            PreparedStatement statement = DatabaseConnection.newStatement(
////                    "SELECT UserID, Username, Password, Name, Email, UserExperiencePoints, Currency FROM User"
////            );
////            if (statement != null) {
////                ResultSet results = statement.executeQuery();
////                if (results != null) {
////                    while (results.next()) {
////                        targetList.add(new User(results.getInt("UserID"), results.getString("Username"), results.getString("Password"), results.getString("Name"),results.getString("Email"), results.getInt("UserExperiencePoints"), results.getInt("Currency")));
////
////
////                    }
////                }
////            }
////        } catch (SQLException resultsException) {
////            String error = "Database error - can't select all from 'User' table: " + resultsException.getMessage();
////
////            Console.state(error);
////            return error;
////        }
////        return "OK";
////    }
////
////    public static User selectByID(int id){
////        User result = null;
////        try{
////            PreparedStatement statement = DatabaseConnection.newStatement(
////                    "SELECT UserID, Username, Password, Name, Email, UserExperiencePoints, Currency FROM User WHERE UserID = ?"
////            );
////            if (statement != null){
////                statement.setInt(1, id);
////                ResultSet results = statement.executeQuery();
////                if (results != null && results.next()){
////                    result = new User(results.getInt("UserID"), results.getString("Username"), results.getString("Password"), results.getString("Name"),results.getString("Email"), results.getInt("UserExperiencePoints"), results.getInt("Currency"));
////                }
////            }
////        }
////        catch (SQLException resultsException){
////            String error = "Database error - can't select by ID from 'User' table: " + resultsException.getMessage();
////
////            Console.state(error);
////        }
////        return result;
////    }
//
//
////    public static String insertSql(User userToSave){
////        try{
////            PreparedStatement statement = DatabaseConnection.newStatement(
////                    "INSERT INTO User (UserID, Username, Password, Name, Email, UserExperiencePoints, Currency) VALUES (?, ?, ?, ?, ?, ?, ?)"
////            );
////            statement.setInt(1, userToSave.getUserID());
////            statement.setString(2, userToSave.getUsername());
////            statement.setString(3, userToSave.getPassword());
////            statement.setString(4, userToSave.getName());
////            statement.setString(5, userToSave.getEmail());
////            statement.setInt(6, userToSave.getUserExperiencePoints());
////            statement.setInt(7, userToSave.getCurrency());
////
////            statement.executeUpdate();
////            return "OK";
////        }
////        catch(SQLException resultsException){
////            String error = "Database error - Can't insert element into 'User' table";
////
////            Console.state(error);
////            return error;
////        }
////    }
//
//    public User selectbyID(int id){
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        User userFound;
//        Cursor cursor = myDB.rawQuery("Select * from User where UserID = ?", new String[]{});
//        return;
//    }
//
//    public Boolean insertSql(User userToSave){
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("UserID", User.getUserID());
//        contentValues.put("Username", User.getUsername());
//        contentValues.put("Password", User.getPassword());
//        contentValues.put("Name", User.getName());
//        contentValues.put("Email", User.getEmail());
//        contentValues.put("UserExperiencePoints", 0);
//        contentValues.put("Currency", 0);
//
//        long result = myDB.insert("User", null, contentValues);
//        if(result == -1){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }
//
//    public static String updateSql(User itemToSave){
//        try {
//            PreparedStatement statement = DatabaseConnection.newStatement(
//                    "UPDATE User SET Username = ?, Password = ?, Name = ?, Email = ?, UserExperiencePoints = ?, Currency = ? WHERE UserID = ?"
//            );
//            statement.setString(1, itemToSave.getUsername());
//            statement.setString(2, itemToSave.getPassword());
//            statement.setString(3, itemToSave.getName());
//            statement.setString(4, itemToSave.getEmail());
//            statement.setInt(5, itemToSave.getUserExperiencePoints());
//            statement.setInt(6, itemToSave.getCurrency());
//
//            statement.setInt(7, itemToSave.getUserID());
//            statement.executeUpdate();
//            return "OK";
//        } catch (SQLException resultsException) {
//            String error = "Database error - can't update 'User' table: " + resultsException.getMessage();
//
//            Console.state(error);
//            return error;
//        }
//    }
//
//    public static String deleteById(int id){
//        try {
//            PreparedStatement statement = DatabaseConnection.newStatement(
//                    "DELETE FROM User WHERE UserID = ?"
//            );
//            statement.setInt(1, id);
//            statement.executeUpdate();
//            return "OK";
//        } catch (SQLException resultsException) {
//            String error = "Database error - can't delete by id from 'User' table: " + resultsException.getMessage();
//
//            Console.state(error);
//            return error;
//        }
//    }

}