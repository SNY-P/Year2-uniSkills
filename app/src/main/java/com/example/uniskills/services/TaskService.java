package com.example.uniskills.services;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.uniskills.Console;
import com.example.uniskills.model.Task;
import com.example.uniskills.model.User;
import com.example.uniskills.resources.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//Kyle do this please

public class TaskService {
    Task[] allTasks;

    public void transferInformation(Task[] toClone){
        allTasks = toClone.clone();
        Log.i("transferInformation()","Transferred!");
    }

    public Task[] insertTask(Task[] currentTaskArray, Task toInsert){
        for (int i = 0; i < currentTaskArray.length;i++){
            if (currentTaskArray[i] == null){
                currentTaskArray[i] = toInsert;
            }
            else{
                Log.i("Array Size","Position full! Trying next");
            }
        }
        allTasks = currentTaskArray.clone();
        return currentTaskArray;
    }

    public Task[] getAllTasks(){
        return allTasks;
    }

    public Task selectTask(Task[] array,String taskName){
        Task toReturn = null;
        Task tempValue;
        for (int i = 0;i<array.length;i++){
            tempValue = array[i];
            if (tempValue.getTitle().equals(taskName)){
                toReturn = tempValue;
            }
        }
        return toReturn;
    }








//    public static String selectAllSql(List<Task> targetList) {
//        targetList.clear();
//        try {
//            PreparedStatement statement = DatabaseConnection.newStatement(
//                    "SELECT TaskID, Title, Description, Difficulty, XPReward, Reward, TaskCategoryID FROM Tasks"
//            );
//            if (statement != null) {
//                ResultSet results = statement.executeQuery();
//                if (results != null) {
//                    while (results.next()) {
//                        targetList.add(new Task(results.getInt("TaskID"), results.getString("Title"), results.getString("Description"), results.getString("Difficulty"),results.getInt("XPReward"), results.getInt("Reward"), results.getInt("TaskCategoryID")));
//
//
//                    }
//                }
//            }
//        } catch (SQLException resultsException) {
//            String error = "Database error - can't select all from 'Tasks' table: " + resultsException.getMessage();
//
//            Console.state(error);
//            return error;
//        }
//        return "OK";
//    }
//
//    public static Task selectByID(int id){
//        Task result = null;
//        try{
//            PreparedStatement statement = DatabaseConnection.newStatement(
//                    "SELECT TaskID, Title, Description, Difficulty, XPReward, Reward, TaskCategoryID FROM Tasks WHERE TaskID = ?"
//            );
//            if (statement != null){
//                statement.setInt(1, id);
//                ResultSet results = statement.executeQuery();
//                if (results != null && results.next()){
//                    result = new Task(results.getInt("TaskID"), results.getString("Title"), results.getString("Description"), results.getString("Difficulty"),results.getInt("XPReward"), results.getInt("Reward"), results.getInt("TaskCategoryID"));
//                }
//            }
//        }
//        catch (SQLException resultsException){
//            String error = "Database error - can't select by ID from 'Tasks' table: " + resultsException.getMessage();
//
//            Console.state(error);
//        }
//        return result;
//    }
//
//
//    public static String insertSql(Task taskToSave){
//        try{
//            PreparedStatement statement = DatabaseConnection.newStatement(
//                    "INSERT INTO Tasks (TaskID, Title, Description, Difficulty, XPReward, Reward, TaskCategoryID) VALUES (?, ?, ?, ?, ?, ?, ?)"
//            );
//            statement.setInt(1, taskToSave.getTaskID());
//            statement.setString(2, taskToSave.getTitle());
//            statement.setString(3, taskToSave.getDescription());
//            statement.setString(4, taskToSave.getDifficulty());
//            statement.setInt(5, taskToSave.getXPReward());
//            statement.setInt(6, taskToSave.getReward());
//            statement.setInt(7, taskToSave.getTaskCategoryID());
//
//            statement.executeUpdate();
//            return "OK";
//        }
//        catch(SQLException resultsException){
//            String error = "Database error - Can't insert element into 'Tasks' table";
//
//            Console.state(error);
//            return error;
//        }
//    }

//    public Boolean insertSql(Task taskToSave){
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("TaskID", Task.getTaskID());
//        contentValues.put("Title", Task.getTitle());
//        contentValues.put("Difficulty", Task.getDifficulty());
//        contentValues.put("XPReward", Task.getXPReward());
//        contentValues.put("Reward", Task.getReward());
//        contentValues.put("TaskCategoryID", Task.getTaskCategoryID());
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
//    public static String updateSql(Task itemToSave){
//        try {
//            PreparedStatement statement = DatabaseConnection.newStatement(
//                    "UPDATE Tasks SET Title = ?, Description = ?, Difficulty = ?, XPReward = ?, Reward = ?, TaskCategoryID = ? WHERE TaskID = ?"
//            );
//            statement.setString(1, itemToSave.getTitle());
//            statement.setString(2, itemToSave.getDescription());
//            statement.setString(3, itemToSave.getDifficulty());
//            statement.setInt(4, itemToSave.getXPReward());
//            statement.setInt(5, itemToSave.getReward());
//            statement.setInt(6, itemToSave.getTaskCategoryID());
//
//            statement.setInt(7, itemToSave.getTaskID());
//            statement.executeUpdate();
//            return "OK";
//        } catch (SQLException resultsException) {
//            String error = "Database error - can't update 'Tasks' table: " + resultsException.getMessage();
//
//            Console.state(error);
//            return error;
//        }
//    }
//
//    public static String deleteById(int id){
//        try {
//            PreparedStatement statement = DatabaseConnection.newStatement(
//                    "DELETE FROM Tasks WHERE TaskID = ?"
//            );
//            statement.setInt(1, id);
//            statement.executeUpdate();
//            return "OK";
//        } catch (SQLException resultsException) {
//            String error = "Database error - can't delete by id from 'Tasks' table: " + resultsException.getMessage();
//
//            Console.state(error);
//            return error;
//        }
//    }
}
