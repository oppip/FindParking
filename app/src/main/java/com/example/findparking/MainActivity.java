package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findparking.Models.DatabaseHelper;
import com.example.findparking.Models.User;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User adduser = new User(1, "Filip", "03/10/1999", email.getText().toString(), password.getText().toString(), 1234567890);
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean success = databaseHelper.addUser(adduser);
                Toast.makeText(MainActivity.this, Boolean.toString(success), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, Cities.class);
                startActivity(intent);


            }

        });
    }
}













