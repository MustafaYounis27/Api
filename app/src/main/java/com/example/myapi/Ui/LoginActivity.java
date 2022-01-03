package com.example.myapi.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapi.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView emailField = findViewById(R.id.email);
        final TextView passwordField = findViewById(R.id.password);

        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailField.getText().toString().isEmpty() || passwordField.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "please enter your data", Toast.LENGTH_SHORT).show();
                } else{
                    if(emailField.getText().toString().equals("admin@team.com") || passwordField.getText().toString().equals("123456")){
                        Intent IntentToMAinActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(IntentToMAinActivity);
                    } else{
                        Toast.makeText(getApplicationContext(), "Email or Password is not correct", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}