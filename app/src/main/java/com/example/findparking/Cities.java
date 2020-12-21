package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.findparking.Helpers.CitiesAdapter;
import com.example.findparking.Helpers.DatabaseHelper;
import com.example.findparking.Models.City;

import java.util.List;

public class Cities extends AppCompatActivity {

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

}