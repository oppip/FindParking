package com.example.findparking.Helpers;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findparking.Models.City;
import com.example.findparking.Models.Parking;
import com.example.findparking.R;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityVH> {
    List<City> cities;
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
        ParkingAdapter parkingAdapter = new ParkingAdapter(parking);
        holder.parkings.setAdapter(parkingAdapter);
        holder.parkings.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        String cityName = city.getCity().toLowerCase();
        int resID = holder.itemView.getContext().getResources().getIdentifier(cityName , "drawable", holder.itemView.getContext().getPackageName());
        holder.imageView.setImageResource(resID);

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

            expandLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    City city = cities.get(getAdapterPosition());
                    city.setExpanded(!city.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
