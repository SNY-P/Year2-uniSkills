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
import com.example.uniskills.MainActivity;

import java.util.ArrayList;
import java.util.Random;

import com.example.uniskills.controllers.TaskController;
import com.example.uniskills.model.User;

public class FitnessTasks extends AppCompatActivity {

    private ImageButton homeBtn;
    private ImageButton taskBtn;
    private ImageButton shopBtn;
    private ImageButton settingBtn;

    private TextView username;
    private TextView currency;
    private ProgressBar expTracker;

    private EditText inTask;
    private ImageButton addTask;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listview;

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
        setContentView(R.layout.activity_fitness_tasks);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        homeBtn = findViewById(R.id.task_home_btn);
        taskBtn = findViewById(R.id.task_task_btn);
        shopBtn = findViewById(R.id.task_shop_btn);
        settingBtn = findViewById(R.id.task_settings_btn);
        inTask = findViewById(R.id.house_task);
        addTask = findViewById(R.id.addtask_button);
        listview = findViewById(R.id.listView);

        username = findViewById(R.id.username);

        User user = (User)getIntent().getSerializableExtra("user");

        username = findViewById(R.id.username);
        currency = findViewById(R.id.currency_display);
        expTracker = findViewById(R.id.exp_bar);

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
                addFitnessTask(v);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listview.setAdapter(itemsAdapter);
        listViewListener(user);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessTasks.this, SettingsActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessTasks.this, ShopActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessTasks.this, TaskPageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessTasks.this, HomePageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }
    private void addFitnessTask(View v){
        inTask = findViewById(R.id.house_task);
        String taskToSubmit = inTask.getText().toString();
        if(!taskToSubmit.equals("")){
            itemsAdapter.add(taskToSubmit);
            inTask.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Please enter something...", Toast.LENGTH_LONG).show();
        }
    }
    public void listViewListener(User user){
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show();
                Random rand = new Random();
                int n = rand.nextInt(100);
                user.setCurrency(user.getCurrency()+n);
                user.setUserExperiencePoints(user.getUserExperiencePoints()+25);
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}