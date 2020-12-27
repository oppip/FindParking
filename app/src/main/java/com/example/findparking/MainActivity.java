package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findparking.Helpers.DatabaseHelper;
import com.example.findparking.Helpers.Session;
import com.example.findparking.Models.User;

public class MainActivity extends AppCompatActivity {

    Button login, signUp;
    EditText email, password;
    private Session session;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signUp);
        session = new Session(getApplicationContext());
        toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.create();
                Intent intent = new Intent(MainActivity.this, Cities.class);
                startActivity(intent);
                session.deleteAll();
                session.setUserID(3);*/
                session.deleteAll();


                String Eemail, Epassword;
                Eemail = email.getText().toString();
                Epassword = password.getText().toString();
                if (!Eemail.isEmpty() && !Epassword.isEmpty()) {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    User checkUser = db.loginCredentials(Eemail, Epassword);
                    if (checkUser == null)
                    {
                        Toast.makeText(MainActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent(MainActivity.this, Cities.class);
                        session.setUserID(checkUser.getUserId());
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "All fields must be filled", Toast.LENGTH_LONG).show();
                }
            }

        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

    }
}













