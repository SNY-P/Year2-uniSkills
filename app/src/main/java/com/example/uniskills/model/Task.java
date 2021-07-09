package com.example.uniskills.model;

import java.util.ArrayList;

public class Task {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static int nextID(){
        int id = 0;
        for (Task t:tasks){
            if (t.getTaskID() > id){
                id = t.getTaskID();
            }
        }
        return id +1;
    }

    static int TaskID;
    static String Title;
    static String Description;
    static String Difficulty;
    static int XPReward;
    static int Reward;
    static int TaskCategoryID;

    public Task(int taskID, String title, String description, String difficulty, int XPReward, int reward, int taskcategoryID) {
        TaskID = taskID;
        Title = title;
        Description = description;
        Difficulty = difficulty;
        this.XPReward = XPReward;
        Reward = reward;
        TaskCategoryID = taskcategoryID;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void setTasks(ArrayList<Task> tasks) {
        Task.tasks = tasks;
    }

    public static int getTaskID() {
        return TaskID;
    }

    public void setTaskID(int taskID) {
        TaskID = taskID;
    }

    public static String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public static String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public static int getXPReward() {
        return XPReward;
    }

    public void setXPReward(int XPReward) {
        this.XPReward = XPReward;
    }

    public static int getReward() {
        return Reward;
    }

    public void setReward(int reward) {
        Reward = reward;
    }

    public static int getTaskCategoryID() {
        return TaskCategoryID;
    }

    public void setTaskCategoryID(int taskCategoryID) {
        TaskCategoryID = taskCategoryID;
    }
}
//TASK CONTROLLER
// package main.java.com.example.uniskills.controllers;

// import main.java.com.example.uniskills.Console;
// import main.java.com.example.uniskills.model.Task;
// import main.java.com.example.uniskills.model.TaskList;
// import main.java.com.example.uniskills.services.TaskListService;
// import main.java.com.example.uniskills.services.TaskService;

// public class TaskController {
//     static int easyXP = 20;
//     static int mediumXP = 40;
//     static int hardXP = 60;

//     static int easyMoney = 30;
//     static int mediumMoney = 60;
//     static int hardMoney = 90;

//     public static String createTask(String taskName, String description, String difficulty, int category, int UserID){
//         TaskService ts = new TaskService();
//         TaskListService tls = new TaskListService();
//         String message = null;
//         int tempXP = 0;
//         int tempMoney = 0;
//         String saved = null;
//         String newTaskEntry = null;
//         if(taskName.isEmpty() || description.isEmpty() || difficulty.isEmpty()){
//             message = "Please fill in the empty boxes";
//         }
//         else{
//             if(difficulty.equals("Easy")){
//                 tempXP = easyXP;
//                 tempMoney = easyMoney;
//             }
//             else if(difficulty.equals("Medium")){
//                 tempXP = mediumXP;
//                 tempMoney = mediumMoney;
//             }
//             else{
//                 tempXP = hardXP;
//                 tempMoney = hardMoney;
//             }
//             Task newTask = new Task(Task.nextID(), taskName, description, difficulty, tempXP, tempMoney, category);
//             TaskList newEntry = new TaskList(TaskList.nextID(), newTask.getTaskID(), UserID, "Incomplete");
//             saved = ts.insertSql(newTask);
//             newTaskEntry = tls.insertSql(newEntry);
//             message = "Task Saved";
//         }
//         Console.state(saved);
//         Console.state(newTaskEntry);
//         return message;
//     }

//     public static String deleteTask(String taskName){
//         TaskService ts = new TaskService();
//         String message = null;
//         String success = null;
//         ts.selectAllSql(Task.tasks);
//         for(Task t:Task.tasks){
//             if(t.getTitle().equals(taskName)){
//                 success = ts.deleteById(t.getTaskID());
//                 message = "Delete Complete";
//             }
//             else{
//                 message = "Failed";
//             }
//         }
//         Console.state(success);
//         return message;
//     }

//     public static String updateTask(String taskName, String taskComplete, int userID, int taskID){
//         Task t = new TaskService();
//         TaskListService tls = new TaskListService();
//         String message = null;
//         String success = null;
//         ts.selectAllSql(TaskList.tasks);
//         for(TaskList tl:TaskList.tasks){
//             if(t.getTitle().equals(taskName)){
//                 TaskList temp = new TaskList(tl.getTaskListID(), userID, taskID, taskComplete);
//                 if(temp.getTaskComplete.equals("Completed")){
//                     success = tls.updateSQL(tl.getTaskID());
//                     message = "Update Completed";
//                 }
//                 else{
//                     message = "No update";
//                 }
//             }
//             else{
//                 message = "Task not found";
//             }
//         }
//         Console.state(success);
//         return message;
//     }
// }

//TASK SERVICE

// package main.java.com.example.uniskills.services;

// import main.java.com.example.uniskills.Console;
// import main.java.com.example.uniskills.model.Task;
// import main.java.com.example.uniskills.model.User;
// import main.java.com.example.uniskills.resources.DatabaseConnection;

// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.List;

// //Kyle do this please

// public class TaskService {
//     public static String selectAllSql(List<Task> targetList) {
//         targetList.clear();
//         try {
//             PreparedStatement statement = DatabaseConnection.newStatement(
//                     "SELECT TaskID, Title, Description, Difficulty, XPReward, Reward, TaskCategoryID FROM Tasks"
//             );
//             if (statement != null) {
//                 ResultSet results = statement.executeQuery();
//                 if (results != null) {
//                     while (results.next()) {
//                         targetList.add(new Task(results.getInt("TaskID"), results.getString("Title"), results.getString("Description"), results.getString("Difficulty"),results.getInt("XPReward"), results.getInt("Reward"), results.getInt("TaskCategoryID")));


//                     }
//                 }
//             }
//         } catch (SQLException resultsException) {
//             String error = "Database error - can't select all from 'Tasks' table: " + resultsException.getMessage();

//             Console.state(error);
//             return error;
//         }
//         return "OK";
//     }

//     public static Task selectByID(int id){
//         Task result = null;
//         try{
//             PreparedStatement statement = DatabaseConnection.newStatement(
//                     "SELECT TaskID, Title, Description, Difficulty, XPReward, Reward, TaskCategoryID FROM Tasks WHERE TaskID = ?"
//             );
//             if (statement != null){
//                 statement.setInt(1, id);
//                 ResultSet results = statement.executeQuery();
//                 if (results != null && results.next()){
//                     result = new Task(results.getInt("TaskID"), results.getString("Title"), results.getString("Description"), results.getString("Difficulty"),results.getInt("XPReward"), results.getInt("Reward"), results.getInt("TaskCategoryID"));
//                 }
//             }
//         }
//         catch (SQLException resultsException){
//             String error = "Database error - can't select by ID from 'Tasks' table: " + resultsException.getMessage();

//             Console.state(error);
//         }
//         return result;
//     }


//     public static String insertSql(Task taskToSave){
//         try{
//             PreparedStatement statement = DatabaseConnection.newStatement(
//                     "INSERT INTO Tasks (TaskID, Title, Description, Difficulty, XPReward, Reward, TaskCategoryID) VALUES (?, ?, ?, ?, ?, ?, ?)"
//             );
//             statement.setInt(1, taskToSave.getTaskID());
//             statement.setString(2, taskToSave.getTitle());
//             statement.setString(3, taskToSave.getDescription());
//             statement.setString(4, taskToSave.getDifficulty());
//             statement.setInt(5, taskToSave.getXPReward());
//             statement.setInt(6, taskToSave.getReward());
//             statement.setInt(7, taskToSave.getTaskCategoryID());

//             statement.executeUpdate();
//             return "OK";
//         }
//         catch(SQLException resultsException){
//             String error = "Database error - Can't insert element into 'Tasks' table";

//             Console.state(error);
//             return error;
//         }
//     }

//     public static String updateSql(Task itemToSave){
//         try {
//             PreparedStatement statement = DatabaseConnection.newStatement(
//                     "UPDATE Tasks SET Title = ?, Description = ?, Difficulty = ?, XPReward = ?, Reward = ?, TaskCategoryID = ? WHERE TaskID = ?"
//             );
//             statement.setString(1, itemToSave.getTitle());
//             statement.setString(2, itemToSave.getDescription());
//             statement.setString(3, itemToSave.getDifficulty());
//             statement.setInt(4, itemToSave.getXPReward());
//             statement.setInt(5, itemToSave.getReward());
//             statement.setInt(6, itemToSave.getTaskCategoryID());

//             statement.setInt(7, itemToSave.getTaskID());
//             statement.executeUpdate();
//             return "OK";
//         } catch (SQLException resultsException) {
//             String error = "Database error - can't update 'Tasks' table: " + resultsException.getMessage();

//             Console.state(error);
//             return error;
//         }
//     }

//     public static String deleteById(int id){
//         try {
//             PreparedStatement statement = DatabaseConnection.newStatement(
//                     "DELETE FROM Tasks WHERE TaskID = ?"
//             );
//             statement.setInt(1, id);
//             statement.executeUpdate();
//             return "OK";
//         } catch (SQLException resultsException) {
//             String error = "Database error - can't delete by id from 'Tasks' table: " + resultsException.getMessage();

//             Console.state(error);
//             return error;
//         }
//     }
// }
