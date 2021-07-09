package com.example.uniskills.controllers;
import com.example.uniskills.model.Task;
import com.example.uniskills.model.TaskList;
import com.example.uniskills.controllers.TaskController;
import com.example.uniskills.model.User;
import com.example.uniskills.services.TaskListService;
import com.example.uniskills.services.TaskService;

import java.util.Locale;

public class TaskListController{

    //int taskListID, String taskName, String description, String difficulty, int category
//    public static Task[] taskGrabber(){
//        Task tempTask = null;
//        String message = null;
//        TaskListService tl = new TaskListService();
//        TaskController tc = new TaskController();
//        Task[] userList = new Task[10];
//        //String[] userList = ts.getTasksListID();
//        for(TaskList tl:TaskList.taskLists){
//            if(tl.getFKUserID() == User.getUserID()){
//                tempTask = tc.selectByID(tl.getFKUserID());
//                userList.add(tempTask);
//                message = "Tasks found";
//            }
//            else{
//                message = "User not found";
//            }
//        }
//        return userList;
//    }

    public Task[] taskGrabber(Task[] categoryTask){
        Task tempTask = null;
        TaskListService tls = new TaskListService();
        //TaskService ts = new TaskService();
        Task[] userList = categoryTask;
        int i = 0;

        tls.selectAllSql(TaskList.taskLists);
        for(TaskList tlt:TaskList.taskLists){
            if(tlt.getFKUserID() == User.getUserID()){
                //tempTask = ts.selectByID(tlt.getFKTaskID());
                userList[i] = tempTask;
            }
            i++;
        }
        return userList;
    }

    public Task[] categoriseTasks(int category){
        //TaskService ts = new TaskService();
        Task taskToCategorise;
        Task[] tempCategory = new Task[TaskList.taskLists.size()];
        Task[] finalList;
        int i = 0;

        //ts.selectAllSql(Task.tasks);
        for(Task t:Task.tasks){
            if(t.getTaskCategoryID() == category){
                taskToCategorise = t;
                tempCategory[i] = taskToCategorise;
            }
            i++;
        }
        finalList = taskGrabber(tempCategory);
        return finalList;
    }
}
