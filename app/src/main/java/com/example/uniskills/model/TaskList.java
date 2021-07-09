package com.example.uniskills.model;

import java.util.ArrayList;


public class TaskList {
    int TaskListID;
    int FKTaskID;
    int FKUserID;
    String TaskComplete;

    public static ArrayList<TaskList> taskLists = new ArrayList<>();

    public static int nextID(){
        int id = 0;
        for (TaskList t:taskLists){
            if (t.getTaskListID() > id){
                id = t.getTaskListID();
            }
        }
        return id +1;
    }

    public TaskList(int taskListID, int FKTaskID, int FKUserID, String taskComplete) {
        TaskListID = taskListID;
        this.FKTaskID = FKTaskID;
        this.FKUserID = FKUserID;
        TaskComplete = taskComplete;
    }

    public int getTaskListID() {
        return TaskListID;
    }

    public void setTaskListID(int taskListID) {
        TaskListID = taskListID;
    }

    public int getFKTaskID() {
        return FKTaskID;
    }

    public void setFKTaskID(int FKTaskID) {
        this.FKTaskID = FKTaskID;
    }

    public int getFKUserID() {
        return FKUserID;
    }

    public void setFKUserID(int FKUserID) {
        this.FKUserID = FKUserID;
    }

    public String getTaskComplete() {
        return TaskComplete;
    }

    public void setTaskComplete(String taskComplete) {
        TaskComplete = taskComplete;
    }
}
