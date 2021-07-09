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
import com.example.uniskills.MainActivity;

public class SignUpActivity extends AppCompatActivity {
    DBHelper DB;

    private EditText inUname;
    private EditText inEmail;
    private EditText inPword;
    private EditText inPword2;
    private EditText inName;
    private ImageButton eSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        inUname = (EditText) findViewById(R.id.eUname);
        inPword = (EditText) findViewById(R.id.ePassword1);
        inPword2 = (EditText) findViewById(R.id.ePassword2);
        eSignup = (ImageButton) findViewById(R.id.eSignupBtn);
        DB = new DBHelper(this);

        eSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = inUname.getText().toString();
                String pass = inPword.getText().toString();
                String repass = inPword2.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("")){
                    Log.e("sign in", "Unsuccessful, empty boxes");
                    Toast.makeText(SignUpActivity.this, "Missing fields. Please fill in empty fields", Toast.LENGTH_SHORT).show();
                }
                else if(pass.length() < 8) {
                    Log.e("sign in", "Unsuccessful, password to short");
                    Toast.makeText(SignUpActivity.this, "Password must be more than 8 characters long", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkUser = DB.checkUsername(user);
                        if(checkUser == false){
                            Boolean insert = DB.insertUserData(user, pass);
                            if(insert == true){
                                Log.e("sign in", "Successful");
                                Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Log.e("sign in", "Unsuccessful");
                                Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Log.e("sign in", "Unsuccessful");
                            Toast.makeText(SignUpActivity.this, "User already exist. Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Log.e("sign in", "Unsuccessful");
                        Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}