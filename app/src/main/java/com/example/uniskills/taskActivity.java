package com.example.uniskills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class taskActivity extends AppCompatActivity {

    private Button homeBtn;
    private Button taskBtn;
    private Button shopBtn;
    private Button settingBtn;

    /** This page is what allows the user to choose what page
     * they want to go on by selecting the options which are
     * available to them.
     * @author T6
     * @version 1.0
     * @param savedInstanceState this is the bundle of objects that are
     *                           passed into the method when a new page
     *                           is created.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        homeBtn = findViewById(R.id.task_home_btn);
        taskBtn = findViewById(R.id.task_task_btn);
        shopBtn = findViewById(R.id.task_shop_btn);
        settingBtn = findViewById(R.id.task_setting_btn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(taskActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(taskActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(taskActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}