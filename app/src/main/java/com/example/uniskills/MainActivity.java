package com.example.uniskills;

import android.util.Log;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uniskills.HomePageActivity;
import com.example.uniskills.resources.DBHelper;
import com.example.uniskills.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    public EditText eName;
    private EditText ePassword;
    private ImageButton btnLogIn;
    private ImageButton btnSignUp;
    DBHelper MyDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.eName);
        ePassword = findViewById(R.id.ePassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        MyDB = new DBHelper(this);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = eName.getText().toString();
                String pass = ePassword.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill in the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = MyDB.checkUsernamePassword(user, pass);
                    if (checkuserpass == true){
                        Log.e("login", "Successful");
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
//                        intent.putExtra("NAME", MyDB.getUserUserName(MyDB.TABLE_USER,user));
                        startActivity(intent);
                    }
                    else{
                        Log.e("login", "Unsuccessful");
                        Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}