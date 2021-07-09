package com.example.uniskills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.Random;

import com.example.uniskills.controllers.TaskController;
import com.example.uniskills.model.User;

public class HouseholdTasks extends AppCompatActivity {

    private ImageButton homeBtn;
    private ImageButton taskBtn;
    private ImageButton shopBtn;
    private ImageButton settingBtn;

    private TextView username;
    private TextView currency;
    private ProgressBar expTracker;
    private ProgressBar taskTracker;

    private EditText inTask;
    private ImageButton addTask;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listview;
    private int TaskCounter;
    private int CompletedTasks;

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
        setContentView(R.layout.activity_household_tasks);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        homeBtn = findViewById(R.id.task_home_btn);
        taskBtn = findViewById(R.id.task_task_btn);
        shopBtn = findViewById(R.id.task_shop_btn);
        settingBtn = findViewById(R.id.task_settings_btn);
        inTask = findViewById(R.id.house_task);
        addTask = findViewById(R.id.addtask_button);
        listview = findViewById(R.id.listView);

        //TaskController tc = new TaskController();

        username = findViewById(R.id.username);

        User user = (User)getIntent().getSerializableExtra("user");

        username = findViewById(R.id.username);
        currency = findViewById(R.id.currency_display);
        expTracker = findViewById(R.id.exp_bar);
        taskTracker = findViewById(R.id.task_bar);

//        User user = (User)getIntent().getSerializableExtra("User");

        username.setText(user.getUsername());

        String displayCurrency = String.valueOf(user.getCurrency());
        currency.setText("$" + displayCurrency);

        int setLevel = (int) user.getUserExperiencePoints() / 100;
        expTracker.setProgress(user.getUserExperiencePoints() - setLevel*100);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHouseholdTask(v);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listview.setAdapter(itemsAdapter);
        listViewListener();
        updateTasks(user);

        //code to display tasks done
        taskTracker.setMax(TaskCounter);
        taskTracker.setProgress(CompletedTasks);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseholdTasks.this, SettingsActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseholdTasks.this, ShopActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseholdTasks.this, TaskPageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseholdTasks.this, HomePageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }

    //add tasks
    public void addHouseholdTask(View v){
        inTask = findViewById(R.id.house_task);
        String taskToSubmit = inTask.getText().toString();
        if(!taskToSubmit.equals("")){
            AddTaskCounter(true);
            itemsAdapter.add(taskToSubmit);
            inTask.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Please enter something...", Toast.LENGTH_LONG).show();
        }
    }

    //remove task
    public void listViewListener(){
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();

                AddTaskCounter(false);
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