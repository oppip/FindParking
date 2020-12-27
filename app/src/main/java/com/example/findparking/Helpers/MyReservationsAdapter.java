package com.example.findparking.Helpers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findparking.Models.Parking;
import com.example.findparking.Models.Reservation;
import com.example.findparking.R;
import com.example.findparking.ReservationConfrimation;

import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MyReservationsAdapter extends RecyclerView.Adapter<MyReservationsAdapter.ReservationVH> {

    List<Reservation> reservations;
    Session session;
    DatabaseHelper db;
    public MyReservationsAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public ReservationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_reservations, parent, false);
        return new ReservationVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationVH holder, int position) {
        Reservation reservation = reservations.get(position);
        DatabaseHelper db = new DatabaseHelper(holder.itemView.getContext().getApplicationContext());
        Parking parking = db.getParking(reservation.getReservationId());
        session = new Session(holder.itemView.getContext().getApplicationContext());
        String number = "Reservation " + String.valueOf(position+1);
        holder.cityAndParkingName.setText(" " + parking.getName() + ", " + db.getCityName((db.getCityFromParking(parking.getParkingId())).getCityId()));
        holder.reservationNumber.setText(number);
        holder.dateForReservations.setText(reservation.getReservationDate());
        holder.timeFrame.setText(db.fixTimeFrame(reservation.getTime()));
        String textToEncode = db.getReservation(session.getUserID(),
                db.ParkingForReservation(reservation.getReservationId()), reservation.getTime(), reservation.getReservationDate())
                .toString(session.getUserID(), db.ParkingForReservation(reservation.getReservationId()));

        QRGEncoder qrgEncoder = new QRGEncoder(textToEncode, null, QRGContents.Type.TEXT, 500);
        try {
            Bitmap bitmap = qrgEncoder.getBitmap();
            holder.qrcode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.viewReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reservation reservation = reservations.get(holder.getAdapterPosition());
                Intent intent = new Intent(holder.itemView.getContext(), ReservationConfrimation.class);
                intent.putExtra("cityandparkingname", parking.getName() + ", " + db.getCityName((db.getCityFromParking(parking.getParkingId())).getCityId()));
                intent.putExtra("ReservationDate", reservation.getReservationDate());
                intent.putExtra("TimeframeForReservation", db.fixTimeFrame(reservation.getTime()));
                intent.putExtra("QRCodeText", db.getReservation(session.getUserID(), parking.getParkingId(), reservation.getTime(), reservation.getReservationDate()).toString(session.getUserID(), parking.getParkingId()));
                intent.putExtra("reservationID", db.getReservation(session.getUserID(), parking.getParkingId(), reservation.getTime(), reservation.getReservationDate()).getReservationId());
                intent.putExtra("longitude", parking.getCoordinates().getLongitude());
                intent.putExtra("latitude", parking.getCoordinates().getLatitude());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    public class ReservationVH extends RecyclerView.ViewHolder {

        TextView reservationNumber, cityAndParkingName, timeFrame, dateForReservations;
        Button delete, viewReservation;
        ImageView qrcode;
        DatabaseHelper db;
        public ReservationVH(@NonNull View itemView) {
            super(itemView);
            db = new DatabaseHelper(itemView.getContext().getApplicationContext());
            reservationNumber = (TextView) itemView.findViewById(R.id.ReservationNumber);
            cityAndParkingName = itemView.findViewById(R.id.myReservationsCityAndParkingName);
            timeFrame = itemView.findViewById(R.id.myReservationstimeFrameForReservation);
            dateForReservations = itemView.findViewById(R.id.myReservationsDateForReservation);
            delete = itemView.findViewById(R.id.myReservationsDeleteReservation);
            viewReservation = itemView.findViewById(R.id.ViewReservation);
            qrcode = itemView.findViewById(R.id.QRCodeimageView);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Reservation reservation = reservations.get(getAdapterPosition());
                    db.deleteReservation(reservation.getReservationId());
                    reservations.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(0, reservations.size());
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return reservations.size();
    }
}
