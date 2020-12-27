package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findparking.Helpers.DatabaseHelper;
import com.example.findparking.Helpers.MyReservationsAdapter;
import com.example.findparking.Helpers.Session;
import com.example.findparking.Models.Reservation;

import java.util.List;

public class MyReservations extends AppCompatActivity {

    RecyclerView recyclerView;
    private Session session;
    MyReservationsAdapter myReservationsAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);
        toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);

        session = new Session(getApplicationContext());
        List<Reservation> reservations;
        int user_id = session.getUserID();
        if (user_id == -1)
        {
            Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show();
        }
        else {
            recyclerView = findViewById(R.id.myReservationsRecyclerView);

            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            reservations = db.getReservations(user_id);

            myReservationsAdapter = new MyReservationsAdapter(reservations);
            recyclerView.setAdapter(myReservationsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }
    }

    public void notifyAdapter()
    {
        this.myReservationsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (session.getUserID() != -1) {
            notifyAdapter();
        }
    }
}