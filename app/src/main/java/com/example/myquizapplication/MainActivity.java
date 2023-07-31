package com.example.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity   {

    EditText loginText,passwordText;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginText=findViewById(R.id.login);
        passwordText=findViewById(R.id.password);
        loginBtn=findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=loginText.getText().toString();
                String password=passwordText.getText().toString();

                if((username.equals("user") && password.equals("123456"))||(username.equals("hudaif") && password.equals("123456"))||(username.equals("amogha") && password.equals("123456"))||(username.equals("nitte") && password.equals("123456")))
                {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),welcome.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid Username Or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}