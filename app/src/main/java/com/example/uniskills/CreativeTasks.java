package com.example.uniskills;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.uniskills.controllers.TaskController;
import com.example.uniskills.model.User;
import com.example.uniskills.resources.DBHelper;

public class CreativeTasks extends AppCompatActivity {

    private ImageButton homeBtn;
    private ImageButton taskBtn;
    private ImageButton shopBtn;
    private ImageButton settingBtn;
    private EditText difficulty;

    private TextView username;
    private TextView currency;
    private ProgressBar expTracker;
    private ProgressBar taskTracker;

    private EditText inTask;
    private ImageButton addTask;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listview;
    private ArrayList arraylist1;
    private ArrayAdapter arrayadapter1;

    DBHelper myDB;


    private int TaskCounter;
    private int CompletedTasks;

    ArrayAdapter arrayAdapter1, arrayAdapter2;



    /** This page is the bridge between the UI and and the logic,
     * it handles the input from the user by allowing the user to
     * enter information about the task and then adding it to the
     * database.
     * @author T6
     * @version 1.0
     * @param savedInstanceState this is the bundle of objects that are
     *                           passed into the method when a new page
     *                           is created.
         */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creative_tasks);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        homeBtn = findViewById(R.id.task_home_btn);
        taskBtn = findViewById(R.id.task_task_btn);
        shopBtn = findViewById(R.id.task_shop_btn);
        settingBtn = findViewById(R.id.task_settings_btn);
        inTask = findViewById(R.id.house_task);
        addTask = findViewById(R.id.addtask_button);
        listview = findViewById(R.id.listView);

        username = findViewById(R.id.username);
        difficulty = findViewById(R.id.difficulty);

        User user = (User)getIntent().getSerializableExtra("user");


        username = findViewById(R.id.username);
        currency = findViewById(R.id.currency_display);
        expTracker = findViewById(R.id.exp_bar);
        taskTracker = findViewById(R.id.task_bar);

        myDB = new DBHelper(this);

//        User user = (User)getIntent().getSerializableExtra("User");

        username.setText(user.getUsername());

        String displayCurrency = String.valueOf(user.getCurrency());
        currency.setText("$" + displayCurrency);

        int setLevel = (int) user.getUserExperiencePoints() / 100;
        expTracker.setProgress(user.getUserExperiencePoints() - setLevel*100);

        //TaskController tc = new TaskController();

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCreativeTask(v);
            }
        });

        arraylist1 = myDB.getCreative();
        items = new ArrayList<>();
        arrayadapter1 = new ArrayAdapter(CreativeTasks.this, android.R.layout.simple_list_item_1, items);
        listview.setAdapter(arrayadapter1);
        listViewListener();
        updateTasks(user);

        //code to display tasks done
        taskTracker.setMax(TaskCounter);
        taskTracker.setProgress(CompletedTasks);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreativeTasks.this, SettingsActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreativeTasks.this, ShopActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreativeTasks.this, TaskPageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreativeTasks.this, HomePageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }

    //add task
    public void addCreativeTask(View v){

        try {
            String taskName = inTask.getText().toString();
            String option = difficulty.getText().toString();
            if(taskName.equals("") || option.equals("")){
                Toast.makeText(CreativeTasks.this, "Please fill in the blanks", Toast.LENGTH_SHORT).show();
            }


            else {
                if ((taskName.split("\\s+").length == 1) && (option.equals("Easy") || option.equals("Medium") || option.equals("Hard"))) {
                    Boolean checkTask = myDB.checkCreativeTask(taskName, option);

                    if(checkTask == false){
                        Boolean insert = myDB.insertCreativeData(taskName, option);
                        Log.e("task adding", String.valueOf(insert));
                        if(insert == true){
                            Toast.makeText(CreativeTasks.this, "Task added", Toast.LENGTH_SHORT).show();
                            arrayadapter1.add(taskName);
                            arraylist1.clear();
                            arraylist1.addAll(myDB.getCreative());
                            inTask.setText("");
                            difficulty.setText("");
                            arrayadapter1.notifyDataSetChanged();

                        }else{
                            Toast.makeText(CreativeTasks.this, "Not able to add", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(CreativeTasks.this, "Task already exist", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(CreativeTasks.this, "Please enter a one word task or enter Easy, Medium or Hard", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Log.e("task adding", "something went wrong");
        }


    }

    //remove task
    public void listViewListener(){
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();

                //AddTaskCounter(false);
                Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show();

                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    //complete task
    public void updateTasks(User user){
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){

                Random rand = new Random();
                int n = rand.nextInt(100);
                user.setCurrency(user.getCurrency()+n);
                user.setUserExperiencePoints(user.getUserExperiencePoints()+25);

                AddCompletedTasks(true);

                Toast.makeText(getApplicationContext(), "Task completed! Earned $" + n + " and 25 xp!", Toast.LENGTH_LONG).show();

                items.set(position, "Completed");
                //items.setTextColor(Color.GREEN);
                itemsAdapter.notifyDataSetChanged();

            }
        });
    }

    public void AddTaskCounter(Boolean b){
        if (b){
            TaskCounter++;
        }
        else{
            TaskCounter--;
        }
    }

    public void AddCompletedTasks(Boolean b){
        if (b){
            CompletedTasks++;
        }
    }


}