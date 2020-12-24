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
import com.example.findparking.Models.Reservation;
import com.example.findparking.R;
import com.example.findparking.ReservationConfrimation;
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
        holder.reservedParkings.setText(String.valueOf(numberOfReservedSpots));
        holder.parkingSpaces.setText(String.valueOf(parking.getMaxParkingSpaces() - numberOfReservedSpots));
        holder.reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                List<Reservation> reservations = db.getReservations(session.getUserID());
                if(db.NumberOfReservations(session.getUserID()) >= 3)
                {
                    Toast.makeText(holder.itemView.getContext(), "Only 3 reservations at a time", Toast.LENGTH_SHORT).show();
                }
                else {
                    for (int i =0; i<reservations.size(); i++)
                    {
                        if(db.ParkingForReservation(reservations.get(i).getReservationId()) == parking.getParkingId() && reservations.get(i).getTime() == time && reservations.get(i).getReservationDate().equals(date))
                        {
                            flag = true;
                        }
                    }
                    if(!flag) {
                        Reservation add = new Reservation(1, date, time);
                        db.addReservation(add, session.getUserID(), parking.getParkingId());
                        Intent intent = new Intent(holder.itemView.getContext(), ReservationConfrimation.class);
                        intent.putExtra("cityandparkingname", parking.getName() + ", " + db.getCityName(city_id));
                        intent.putExtra("ReservationDate", date);
                        intent.putExtra("TimeframeForReservation", db.fixTimeFrame(time));
                        intent.putExtra("QRCodeText", db.getReservation(session.getUserID(), parking.getParkingId(), time, date).toString(session.getUserID(), parking.getParkingId()));
                        intent.putExtra("reservationID", db.getReservation(session.getUserID(), parking.getParkingId(), time, date).getReservationId());
                        holder.itemView.getContext().startActivity(intent);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return parkings.size();
    }

    public class ParkingVH extends RecyclerView.ViewHolder {
        TextView parkingName, parkingSpaces, reservedParkings;
        Button reserve;
        public ParkingVH(View itemview) {
            super(itemview);
            parkingName = itemview.findViewById(R.id.ParkingName);
            parkingSpaces = itemview.findViewById(R.id.ParkingSpaces);
            reservedParkings = itemview.findViewById(R.id.ReservedParkingSpaces);
            reserve = itemview.findViewById(R.id.reserveButton);
            session = new Session(itemview.getContext().getApplicationContext());
        }
    }
}
