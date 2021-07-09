package com.example.uniskills.resources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;



public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Database.db";
    public static final String TABLE_USER = "users";

        /** This page handles the creating the Database as well as using SQL
     * statements to allow the backend and front end to connct and work with
     * the database.
     * @author T6
     * @version 1.0
     * @param context Represents the environment. Represents the state at where you are in your system.
     */

    public DBHelper(Context context) {
        super(context, "Database.db", null, 1);
    }
    /** These are the commands to create the tables and inserting data into them
     * by using SQL statements.
     * @author T6
     * @version 1.0
     * @param myDB this is the database that allows us to connect and use SQLite.
     */

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(userid INTEGER primary key AUTOINCREMENT, username TEXT, password TEXT)");
        myDB.execSQL("create Table fitness(taskid INTEGER primary key AUTOINCREMENT, taskname TEXT, difficulty TEXT, userID INTEGER, foreign key (userID) REFERENCES user(userid))");
        myDB.execSQL("create Table household(taskid INTEGER primary key AUTOINCREMENT, taskname TEXT, difficulty TEXT,userID INTEGER, foreign key (userID) REFERENCES user(userid))");
        myDB.execSQL("create Table creative(taskid INTEGER primary key AUTOINCREMENT, taskname TEXT, difficulty TEXT,userID INTEGER,foreign key (userID) REFERENCES user(userid))");
        myDB.execSQL("create Table education(taskid INTEGER primary key AUTOINCREMENT, taskname TEXT, difficulty TEXT, userID INTEGER, foreign key (userID) REFERENCES user(userid))");
        myDB.execSQL("insert into  users(userid, username, password) values('1', minecraftman97', 'admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists users");
        myDB.execSQL("drop Table if exists fitness");
        myDB.execSQL("drop Table if exists household");
        myDB.execSQL("drop Table if exists creative");
        myDB.execSQL("drop Table if exists education");
    }

    /** This method allows user input to be added to the database of users which can later
     * on be accessed when the user wants to log back in.
     * @author T6
     * @version 1.0
     * @param username this checks and inputs data into the username column.
     * @param password this checks and inputs data into the password column.
     */

    public Boolean insertUserData(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues userList = new ContentValues();
        userList.put("username", username);
        userList.put("password", password);
        myDB.insert("users", null, userList);
        return true;
    }


    /** This method checks the username to see if it already exists in the database.
     * @author T6
     * @version 1.0
     * @param username uses the username column to see if a username is already in the database.
     */

    public Boolean checkUsername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    /** This method accesses the database to check whether the username and password is in the database when the user
     * tries to log in.
     * @author T6
     * @version 1.0
     * @param username uses the username column to retrieve the username from the database.
     * @param password uses the password column to retrieve the passwords from the database.
     */

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    /** This method allows user input to be added to the database which adds it to the 
     * creative table.
     * @author T6
     * @version 1.0
     * @param taskName this checks and inputs data into the taskname column.
     * @param difficulty this checks and inputs data into the difficulty column.
     */

    public Boolean insertCreativeData(String taskname, String difficulty){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues creativeList = new ContentValues();
        creativeList.put("taskname", taskname);
        creativeList.put("difficulty", difficulty);
        myDB.insert("creative", null, creativeList);
        return true;
    }

    /** This method allows user input to be added to the database which adds it to the 
     * fitness table.
     * @author T6
     * @version 1.0
     * @param taskName this checks and inputs data into the taskname column.
     * @param difficulty this checks and inputs data into the difficulty column.
     */

    public Boolean insertFitnessData(String taskname, String difficulty){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues fitnessList = new ContentValues();
        fitnessList.put("taskname", taskname);
        fitnessList.put("difficulty", difficulty);
        myDB.insert("fitness", null, fitnessList);
        return true;
    }


    /** This method allows user input to be added to the database which adds it to the 
     * household table.
     * @author T6
     * @version 1.0
     * @param taskName this checks and inputs data into the taskname column.
     * @param difficulty this checks and inputs data into the difficulty column.
     */
    public Boolean insertHouseHoldData(String taskname, String difficulty){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues householdList = new ContentValues();
        householdList.put("taskname", taskname);
        householdList.put("difficulty", difficulty);
        myDB.insert("household", null, householdList);
        return true;
    }

    /** This method allows user input to be added to the database which adds it to the 
     * education table.
     * @author T6
     * @version 1.0
     * @param taskName this checks and inputs data into the taskname column.
     * @param difficulty this checks and inputs data into the difficulty column.
     */

    public Boolean insertEducationData(String taskname, String difficulty){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues educationList = new ContentValues();
        educationList.put("taskname", taskname);
        educationList.put("difficulty", difficulty);
        myDB.insert("education", null, educationList);
        return true;
    }

    /** This method checks if a task in the creative table already exists.
     * @author T6
     * @version 1.0
     * @param taskName this retrieves data from the taskname column.
     * @param difficulty this retrieves data from the difficulty column.
     */

    public Boolean checkCreativeTask(String taskname, String difficulty){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from creative where taskname = ? and difficulty = ?", new String[] {taskname, difficulty});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    /** This method checks if a task in the education table already exists.
     * @author T6
     * @version 1.0
     * @param taskName this retrieves data from the taskname column.
     * @param difficulty this retrieves data from the difficulty column.
     */

    public Boolean checkEducationTask(String taskname, String difficulty){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from creative where taskname = ? and difficulty = ?", new String[] {taskname, difficulty});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }
    
    /** This method checks if a task in the fitness table already exists.
     * @author T6
     * @version 1.0
     * @param taskName this retrieves data from the taskname column.
     * @param difficulty this retrieves data from the difficulty column.
     */

    public Boolean checkFitnessTask(String taskname, String difficulty){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from creative where taskname = ? and difficulty = ?", new String[] {taskname, difficulty});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    /** This method checks if a task in the household table already exists.
     * @author T6
     * @version 1.0
     * @param taskName this retrieves data from the taskname column.
     * @param difficulty this retrieves data from the difficulty column.
     */

    public Boolean checkHouseHoldTask(String taskname, String difficulty){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from creative where taskname = ? and difficulty = ?", new String[] {taskname, difficulty});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    /** This method retrieves data from the fitness table and puts it into an arraylist
     * @author T6
     * @version 1.0
     */

    public ArrayList getFitness(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = myDB.rawQuery("Select * from fitness where taskname = ? and difficulty = ?", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("taskname")));
            cursor.moveToNext();

        }
        return arrayList;
    }

    /** This method retrieves data from the household table and puts it into an arraylist
     * @author T6
     * @version 1.0
     */

    public ArrayList getHousehold(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = myDB.rawQuery("Select * from household where taskname = ? and difficulty = ?", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("taskname")));
            cursor.moveToNext();

        }
        return arrayList;
    }

    /** This method retrieves data from the creative table and puts it into an arraylist
     * @author T6
     * @version 1.0
     */
    
    public ArrayList getCreative(){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = myDB.rawQuery("Select * from creative where taskname = ? and difficulty = ?", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("taskname")));
            cursor.moveToNext();

        }
        return arrayList;
    }

    /** This method retrieves data from the education table and puts it into an arraylist
     * @author T6
     * @version 1.0
     */
    
    public ArrayList getEducation(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = myDB.rawQuery("Select * from education where taskname = ? and difficulty = ?", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("taskname")));
            cursor.moveToNext();

        }
        return arrayList;
    }
}
