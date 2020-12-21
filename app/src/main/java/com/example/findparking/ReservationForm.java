package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.findparking.Helpers.DatePickerFragment;
import com.example.findparking.Helpers.ReservationFormFragment;

public class ReservationForm extends AppCompatActivity {

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
}