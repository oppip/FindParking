package com.example.findparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findparking.Helpers.CitiesAdapter;
import com.example.findparking.Helpers.DatabaseHelper;
import com.example.findparking.Helpers.Session;
import com.example.findparking.Models.City;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class Cities extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    RecyclerView recyclerView;
    List<City> cities;
    private Session session;
    CitiesAdapter cityAdapter;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        toolbar = (Toolbar) findViewById(R.id.include);
        setSupportActionBar(toolbar);


        session = new Session(getApplicationContext());
        int user_id = session.getUserID();
        if(user_id == -1)
        {
            Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show();
        }
        recyclerView = findViewById(R.id.ListCities);

        initData();
        initRecyclerView();
    }

    private void initData() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        cities = db.getAllCities();
    }

    private void initRecyclerView() {
        cityAdapter = new CitiesAdapter(cities);
        recyclerView.setAdapter(cityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void notifyAdapter()
    {
        this.cityAdapter.notifyDataSetChanged();
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
    protected void onResume() {
        super.onResume();
        if (!session.getDate().isEmpty()) {
            notifyAdapter();
        }
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