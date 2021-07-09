package com.example.uniskills.controllers;

import com.example.uniskills.Console;
import com.example.uniskills.model.Task;
import com.example.uniskills.model.TaskList;
import com.example.uniskills.services.TaskListService;
import com.example.uniskills.services.TaskService;

public class TaskController {
    static int easyXP = 20;
    static int mediumXP = 40;
    static int hardXP = 60;

    static int easyMoney = 30;
    static int mediumMoney = 60;
    static int hardMoney = 90;



    public static String createTask(String taskName, String description, String difficulty, int category, int UserID){
        //TaskService ts = new TaskService();
        TaskListService tls = new TaskListService();
        String message = null;
        int tempXP = 0;
        int tempMoney = 0;
        String saved = null;
        String newTaskEntry = null;
        if(taskName.isEmpty() || description.isEmpty() || difficulty.isEmpty()){
            message = "Please fill in the empty boxes";
        }
        else{
            if(difficulty.equals("Easy")){
                tempXP = easyXP;
                tempMoney = easyMoney;
            }
            else if(difficulty.equals("Medium")){
                tempXP = mediumXP;
                tempMoney = mediumMoney;
            }
            else{
                tempXP = hardXP;
                tempMoney = hardMoney;
            }
            Task newTask = new Task(Task.nextID(), taskName, description, difficulty, tempXP, tempMoney, category);
            TaskList newEntry = new TaskList(TaskList.nextID(), newTask.getTaskID(), UserID, "Incomplete");
            //saved = ts.insertSql(newTask);
            newTaskEntry = tls.insertSql(newEntry);
            message = "Task Saved";
        }
        Console.state(saved);
        Console.state(newTaskEntry);
        return message;
    }

    public static String deleteTask(String taskName){
        //TaskService ts = new TaskService();
        String message = null;
        String success = null;
        //ts.selectAllSql(Task.tasks);
        for(Task t:Task.tasks){
            if(t.getTitle().equals(taskName)){
                //success = ts.deleteById(t.getTaskID());
                message = "Delete Complete";
            }
            else{
                message = "Failed";
            }
        }
        Console.state(success);
        return message;
    }

    public static String updateTask(String taskName, String taskComplete, int userID, int taskID){
        //TaskService ts = new TaskService();
        TaskListService tls = new TaskListService();
        String message = null;
        String success = null;
        tls.selectAllSql(TaskList.taskLists);
        for(TaskList tl:TaskList.taskLists){
            if(tl.getFKTaskID() == taskID){
                TaskList temp = new TaskList(tl.getTaskListID(), taskID, userID, taskComplete);
                if(temp.getTaskComplete().equals("Completed")){
                    success = tls.updateSql(temp);
                    message = "Update Completed";
                }
                else{
                    message = "No update";
                }
            }
            else{
                message = "Task not found";
            }
        }
        Console.state(success);
        return message;
    }
}