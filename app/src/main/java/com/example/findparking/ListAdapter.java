package com.example.findparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findparking.Models.DatabaseHelper;
import com.example.findparking.Models.User;

import java.util.ArrayList;
import java.util.List;

class ListAdapter extends RecyclerView.Adapter{

    DatabaseHelper db = new DatabaseHelper(null);
    List<User> users = db.getAllUsers();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView city, parkingSpacesInCity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.city);
            parkingSpacesInCity = (TextView) itemView.findViewById(R.id.cityParkingCapacity);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            city.setText(users.get(position).getName());
            parkingSpacesInCity.setText(users.get(position).getEmail());
        }

        public void onClick(View view) {

        }
    }



}