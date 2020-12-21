package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.findparking.Helpers.CitiesAdapter;
import com.example.findparking.Helpers.DatabaseHelper;
import com.example.findparking.Models.City;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class Cities extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    RecyclerView recyclerView;
    List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        recyclerView = findViewById(R.id.ListCities);

        initData();
        initRecyclerView();
    }

    private void initData() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        cities = db.getAllCities();
    }

    private void initRecyclerView() {
        CitiesAdapter cityAdapter = new CitiesAdapter(cities);
        recyclerView.setAdapter(cityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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