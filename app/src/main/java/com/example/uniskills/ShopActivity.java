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

public class ShopActivity extends AppCompatActivity {

    private ImageButton homeBtn;
    private ImageButton taskBtn;
    private ImageButton shopBtn;
    private ImageButton settingBtn;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        homeBtn = findViewById(R.id.shop_home_btn);
        taskBtn = findViewById(R.id.shop_task_btn);
        shopBtn = findViewById(R.id.shop_shop_btn);
        settingBtn = findViewById(R.id.shop_settings_btn);

        User user = (User)getIntent().getSerializableExtra("user");

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, HomePageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, TaskPageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, SettingsActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }
}

