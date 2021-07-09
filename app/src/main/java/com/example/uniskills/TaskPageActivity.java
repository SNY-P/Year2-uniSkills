package com.example.uniskills;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.uniskills.model.User;

public class TaskPageActivity extends AppCompatActivity {

    private ImageButton homeBtn;
    private ImageButton taskBtn;
    private ImageButton shopBtn;
    private ImageButton settingBtn;


    private ImageButton fitnessBtn;
    private ImageButton houseBtn;
    private ImageButton creativityBtn;
    private ImageButton educationBtn;

    private TextView username;
//    private String uName;

    private TextView currency;
    private ProgressBar expTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_page);

        homeBtn = findViewById(R.id.task_home_btn);
        taskBtn = findViewById(R.id.task_task_btn);
        shopBtn = findViewById(R.id.task_shop_btn);
        settingBtn = findViewById(R.id.task_settings_btn);

        fitnessBtn = findViewById(R.id.fitness_button);
        houseBtn = findViewById(R.id.houseHold_button);
        creativityBtn = findViewById(R.id.creativity_button);
        educationBtn = findViewById(R.id.education_button);

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

//        Bundle extras = getIntent().getExtras();
//        uName = extras.getString("User");
//        username.setText(uName);

        fitnessBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TaskPageActivity.this, FitnessTasks.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });

        educationBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TaskPageActivity.this, EducationTasks.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });

        creativityBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TaskPageActivity.this, CreativeTasks.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });

        houseBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TaskPageActivity.this, HouseholdTasks.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });

        // start of navbar
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TaskPageActivity.this, HomePageActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });

        shopBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TaskPageActivity.this, ShopActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });

        settingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TaskPageActivity.this, SettingsActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        });
    }
}