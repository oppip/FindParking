package com.example.findparking.Helpers;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findparking.Cities;
import com.example.findparking.MainActivity;
import com.example.findparking.Models.Parking;
import com.example.findparking.R;
import com.example.findparking.ReservationForm;
import com.example.findparking.SignUp;

import java.util.List;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingVH> {

    private Session session;
    List<Parking> parkings;
    int city_id;
    DatabaseHelper db;
    public ParkingAdapter(List<Parking> parking, int city_id) {
        this.parkings = parking;
        this.city_id = city_id;
    }

    @NonNull
    @Override
    public ParkingVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_parkings, parent, false);
        return new ParkingVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingVH holder, int position) {
        db = new DatabaseHelper(holder.itemView.getContext().getApplicationContext());
        String date = session.getDate();
        int time = session.getTime();
        int numberOfReservedSpots = db.ReservedSpacesFor(date, time, city_id, parkings.get(position).getParkingId());
        Parking parking = parkings.get(position);
        holder.parkingName.setText(parking.getName());
        holder.parkingSpaces.setText(String.valueOf(parking.getMaxParkingSpaces() - numberOfReservedSpots));
        holder.reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ReservationForm.class);
                holder.itemView.getContext().startActivity(intent);
                Toast.makeText(holder.itemView.getContext(), holder.parkingName.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return parkings.size();
    }

    public class ParkingVH extends RecyclerView.ViewHolder {
        TextView parkingName, parkingSpaces;
        Button reserve;
        public ParkingVH(View itemview) {
            super(itemview);
            parkingName = itemview.findViewById(R.id.ParkingName);
            parkingSpaces = itemview.findViewById(R.id.ParkingSpaces);
            reserve = itemview.findViewById(R.id.reserveButton);
            session = new Session(itemview.getContext().getApplicationContext());
        }
    }
}
