package com.example.findparking.Helpers;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.findparking.R;

public class ReservationConfirmedFragment extends Fragment {

    TextView cityAndParkingName, dateForReservation, timeFrameForReservation;
    Button delete, navigate;
    int reservationID;


    public ReservationConfirmedFragment() {
    }

    public static ReservationConfirmedFragment newInstance() {
        ReservationConfirmedFragment fragment = new ReservationConfirmedFragment();
        fragment.setArguments(null);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());

        cityAndParkingName = getActivity().findViewById(R.id.cityAndParkingName);
        dateForReservation = getActivity().findViewById(R.id.dateForReservation);
        timeFrameForReservation = getActivity().findViewById(R.id.timeFrameForReservation);
        reservationID = getActivity().getIntent().getExtras().getInt("reservationID");
        String name, date, time;
        name = " " + getActivity().getIntent().getExtras().getString("cityandparkingname");
        date = getActivity().getIntent().getExtras().getString("ReservationDate");
        time = getActivity().getIntent().getExtras().getString("TimeframeForReservation");

        cityAndParkingName.setText(name);
        dateForReservation.setText(date);
        timeFrameForReservation.setText(time);

        delete = getActivity().findViewById(R.id.DeleteReservation);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteReservation(reservationID);
                getActivity().finish();
            }
        });

        float lat, lon;
        lat = getActivity().getIntent().getExtras().getFloat("latitude");
        lon = getActivity().getIntent().getExtras().getFloat("longitude");
        navigate = getActivity().findViewById(R.id.NavigateToDestination);
        navigate.setOnClickListener(new View.OnClickListener() {
            String destination = "google.navigation:q=" + String.valueOf(lat) + "," + String.valueOf(lon) + "&mode=d";
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(destination));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getContext(), "You dont have g maps", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation_confirmed, container, false);
    }
}
