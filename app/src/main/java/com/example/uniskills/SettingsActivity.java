package com.example.uniskills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.uniskills.model.User;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton homeBtn;
    private ImageButton taskBtn;
    private ImageButton shopBtn;
    private ImageButton settingBtn;

    private ImageButton logoutBtn;

        /** This page handles 
     * @author T6
     * @version 1.0
     * @param savedInstanceState this is the bundle of objects that are
     *                           passed into the method when a new page
     *                           is created.
     * @param View.OnClickListener() this detects any events related to the UI such as 
     *                           clicking or tapping parts of a screen.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        homeBtn = findViewById(R.id.settings_home_btn);
        taskBtn = findViewById(R.id.settings_task_btn);
        shopBtn = findViewById(R.id.settings_shop_btn);
        settingBtn = findViewById(R.id.settings_settings_btn);

        logoutBtn = findViewById(R.id.logout_btn);

        User user = (User)getIntent().getSerializableExtra("user");

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, HomePageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ShopActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, TaskPageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

