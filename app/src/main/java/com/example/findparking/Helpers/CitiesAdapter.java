package com.example.findparking.Helpers;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findparking.Models.City;
import com.example.findparking.Models.Parking;
import com.example.findparking.R;
import com.example.findparking.ReservationForm;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityVH> {
    List<City> cities;
    Session session;

    public CitiesAdapter(List<City> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public CityVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cities, parent, false);
        return new CityVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityVH holder, int position) {
        City city = cities.get(position);
        holder.cityName.setText(city.getCity());
        DatabaseHelper db = new DatabaseHelper(holder.itemView.getContext());
        List<Parking> parking = db.getAllParkingsForCity(city.getCityId());
        ParkingAdapter parkingAdapter = new ParkingAdapter(parking,city.getCityId());
        holder.parkings.setAdapter(parkingAdapter);
        holder.parkings.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        String cityName = city.getCity().toLowerCase();
        int resID = holder.itemView.getContext().getResources().getIdentifier(cityName , "drawable", holder.itemView.getContext().getPackageName());
        holder.imageView.setImageResource(resID);

        if (city.getCityId() == session.getCityPicked())
        {
            city.setExpanded(true);
            session.setCityPicked(-1);
        }

        boolean isExpanded = city.isExpanded();
        holder.constraintLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CityVH extends RecyclerView.ViewHolder {

        TextView cityName;
        RecyclerView parkings;
        ConstraintLayout constraintLayout, expandLayout;
        ImageView imageView;
        public CityVH(@NonNull final View itemview) {
            super(itemview);
            cityName = itemview.findViewById(R.id.ExpandableCityName);
            parkings = itemview.findViewById(R.id.listParkings);
            constraintLayout = itemview.findViewById(R.id.constraintLayout);
            imageView = itemview.findViewById(R.id.cityImage);
            expandLayout = itemview.findViewById(R.id.relativeLayout);
            session = new Session(itemview.getContext().getApplicationContext());

            expandLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int isTimePicked = session.getTime();
                    String isDatePicked = session.getDate();
                    if (isTimePicked != -1 && isDatePicked != "")
                    {
                        City city = cities.get(getAdapterPosition());
                        city.setExpanded(!city.isExpanded());
                        notifyItemChanged(getAdapterPosition());
                    }
                    else
                    {
                        session.setCityPicked(cities.get(getAdapterPosition()).getCityId());
                        int orientation =  itemview.getContext().getResources().getConfiguration().orientation;
                        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            Toast.makeText(itemview.getContext(), "Please choose a date from here^", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(itemview.getContext(), "Please pick date", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(itemview.getContext(), ReservationForm.class);
                            itemview.getContext().startActivity(intent);
                        }
                    }
                }
            });
        }
    }
}
