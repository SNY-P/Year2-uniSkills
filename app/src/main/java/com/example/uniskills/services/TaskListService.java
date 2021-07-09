package com.example.uniskills.services;

import com.example.uniskills.Console;
import com.example.uniskills.model.TaskList;
import com.example.uniskills.resources.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskListService {
    public static String selectAllSql(List<TaskList> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT TaskListID, TaskID, UserID, TaskCompleted FROM TaskList"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new TaskList(results.getInt("TaskListID"),results.getInt("TaskID"),results.getInt("UserID"),results.getString("TaskComplete")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'TaskList' table: " + resultsException.getMessage();

            Console.state(error);
            return error;
        }
        return "OK";
    }

    public static TaskList selectByID(int id){
        TaskList result = null;
        try{
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT TaskListID, TaskID, UserID, TaskCompleted FROM TaskList WHERE TaskListID = ?"
            );
            if (statement != null){
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()){
                    result = new TaskList(results.getInt("TaskListID"),results.getInt("TaskID"),results.getInt("UserID"),results.getString("TaskComplete"));
                }
            }
        }
        catch (SQLException resultsException){
            String error = "Database error - can't select by ID from 'TaskList' table: " + resultsException.getMessage();

            Console.state(error);
        }
        return result;
    }


    public static String insertSql(TaskList tasklistToSave){
        try{
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO TaskList (TaskListID, TaskID, UserID, TaskCompleted) VALUES (?, ?, ?, ?)"
            );
            statement.setInt(1, tasklistToSave.getTaskListID());
            statement.setInt(2, tasklistToSave.getFKTaskID());
            statement.setInt(3, tasklistToSave.getFKUserID());
            statement.setString(4, tasklistToSave.getTaskComplete());

            statement.executeUpdate();
            return "OK";
        }
        catch(SQLException resultsException){
            String error = "Database error - Can't insert element into 'TaskList' table";

            Console.state(error);
            return error;
        }
    }

    public static String updateSql(TaskList itemToSave){
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE TaskList SET TaskListID = ?, TaskID = ?, UserID = ?, TaskComplete = ? WHERE TaskListID = ?"
            );
            statement.setInt(1, itemToSave.getFKTaskID());
            statement.setInt(2, itemToSave.getFKUserID());
            statement.setString(3, itemToSave.getTaskComplete());

            statement.setInt(7, itemToSave.getTaskListID());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'TaskList' table: " + resultsException.getMessage();

            Console.state(error);
            return error;
        }
    }

    public static String deleteById(int id){
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM TaskList WHERE TaskListID = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'TaskList' table: " + resultsException.getMessage();

            Console.state(error);
            return error;
        }
    }
}
