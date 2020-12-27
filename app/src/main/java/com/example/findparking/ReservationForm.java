package com.example.findparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.example.findparking.Helpers.DatePickerFragment;
import com.example.findparking.Helpers.ReservationFormFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class ReservationForm extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);
        toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        String[] arr = currentDateString.split("/");
        String eudate = arr[1] + "/" + arr[0] + "/" + arr[2];
        EditText editText = (EditText) findViewById(R.id.date);
        editText.setText(eudate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.buttonMyReservations)
        {
            Intent intent = new Intent(this, MyReservations.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}