package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findparking.Helpers.DatabaseHelper;
import com.example.findparking.Helpers.DatePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class SignUp extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText date;
    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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
                Toast.makeText(getApplicationContext(), "updated db", Toast.LENGTH_SHORT);
                DatabaseHelper create = new DatabaseHelper(getApplicationContext());
                create.create();
            }
        });
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        EditText editText = (EditText) findViewById(R.id.birthday);
        editText.setText(currentDateString);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}