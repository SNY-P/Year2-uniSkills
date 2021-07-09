package com.example.uniskills;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageButton;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniskills.model.User;

import java.util.concurrent.TimeUnit;

public class HomePageActivity extends AppCompatActivity {


    private ImageButton homeBtn;
    private ImageButton taskBtn;
    private ImageButton shopBtn;
    private ImageButton settingBtn;
    private TextView username;
    private TextView currency;
    private TextView level;
    private ProgressBar expTracker;
    private ImageView avatar;
    private TextView avaterText;
    MediaPlayer song;



   /** This page is the bridge between the UI and and the logic,
    * it handles the input from the user by displaying buttons
    * and changing the page depending on user input.
    * @author T6
    * @version 1.0
    * @param savedInstanceState this is the bundle of objects that are
    *                           passed into the method when a new page
    *                           is created.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        homeBtn = findViewById(R.id.home_home_btn);
        taskBtn = findViewById(R.id.home_task_btn);
        shopBtn = findViewById(R.id.home_shop_btn);
        settingBtn = findViewById(R.id.home_setting_btn);
        username = findViewById(R.id.username);
        currency = findViewById(R.id.currency_display);
        level = findViewById(R.id.level_num);
        expTracker = findViewById(R.id.exp_bar);
        avatar = findViewById(R.id.avatar);
        avaterText = findViewById(R.id.avaterText);

//        User user = (User)getIntent().getSerializableExtra("User");

        song = MediaPlayer.create(this, R.raw.music);
        song.start();

        User user = new User(1,"MinecraftMan420","thissucks","Chub","ah@ah.ah",5224,748192);
        username.setText(user.getUsername());

        String displayCurrency = String.valueOf(user.getCurrency());
        currency.setText("$" + displayCurrency);

        int setLevel = (int) user.getUserExperiencePoints() / 100;
        level.setText(String.valueOf(setLevel));

        expTracker.setProgress(user.getUserExperiencePoints() - setLevel*100);


        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(avatar.getVisibility() == View.INVISIBLE){
                    avaterText.setVisibility(View.VISIBLE);
                }else{
                    avaterText.setVisibility(View.INVISIBLE);
                }
            }
        });

        //start of navbar
        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, TaskPageActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}
