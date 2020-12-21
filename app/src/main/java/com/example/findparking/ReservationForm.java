package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.example.findparking.Helpers.DatePickerFragment;
import com.example.findparking.Helpers.ReservationFormFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class ReservationForm extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

       /* DatePickerFragment date = new DatePickerFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentSpinner, date, date.getTag());*//*

        ReservationFormFragment spinnerFragment = new ReservationFormFragment();
        FragmentManager spinnermanager = getSupportFragmentManager();
        spinnermanager.beginTransaction().replace(R.id.time, spinnerFragment, ti.getTag());*/

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
}