package com.example.findparking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;

import com.example.findparking.Models.DatabaseHelper;
import com.example.findparking.Models.User;

import java.util.List;

public class Cities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        FragmentManager fragment = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragment.beginTransaction();
        fragmentTransaction.add(R.id.citiesRecycler, fragment);
        fragmentTransaction.commit();
    }
}