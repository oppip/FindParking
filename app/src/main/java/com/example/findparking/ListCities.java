package com.example.findparking;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.findparking.Models.DatabaseHelper;
import com.example.findparking.Models.User;

import java.util.List;

public class ListCities extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_cities, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById((R.id.citiesRecycler));
        ListAdapter listAdapter = new ListAdapter();
        return inflater.inflate(R.layout.fragment_list_cities, container, false);
    }

}