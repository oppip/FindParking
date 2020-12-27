package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findparking.Helpers.DatabaseHelper;
import com.example.findparking.Helpers.DatePickerFragment;
import com.example.findparking.Helpers.Session;
import com.example.findparking.Models.User;

import java.text.DateFormat;
import java.util.Calendar;

public class SignUp extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText date, name, email, password, phoneNumber;
    Button createAccount;
    Toolbar toolbar;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        session = new Session(getApplicationContext());

        name = findViewById(R.id.name);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);

        date = (EditText) findViewById(R.id.birthday);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        createAccount = findViewById(R.id.createAccount);
        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Ename, Eemail, Epassword, Edate;
                long Ephonenumber = 0;
                Ename = name.getText().toString();
                Eemail = email.getText().toString();
                Epassword = password.getText().toString();
                Edate = date.getText().toString();

                if (phoneNumber.getText().toString().split(" ").length != 0)
                {
                    Ephonenumber = Long.parseLong(phoneNumber.getText().toString());
                }

                if (!Ename.isEmpty() && !Edate.isEmpty() && !Eemail.isEmpty() && !Epassword.isEmpty() && Ephonenumber != 0) {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    if ((db.getUser(Eemail)) != -1)
                    {
                        Toast.makeText(SignUp.this, "The user already exists", Toast.LENGTH_LONG).show();
                    }
                    else {
                        User addUser = new User(1, Ename, Edate, Eemail, Epassword, Ephonenumber);
                        db.addUser(addUser);
                        int userID = db.getUser(Eemail);
                        session.deleteAll();
                        session.setUserID(userID);
                        Intent intent = new Intent(SignUp.this, Cities.class);
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(SignUp.this, "All fields must be filled", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        String[] arr = currentDateString.split("/");
        String eudate = arr[1] + "/" + arr[0] + "/" + arr[2];
        EditText editText = (EditText) findViewById(R.id.birthday);
        editText.setText(eudate);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}