package com.example.findparking.Helpers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        name = getActivity().getIntent().getExtras().getString("cityandparkingname");
        date = getActivity().getIntent().getExtras().getString("ReservationDate");
        time = getActivity().getIntent().getExtras().getString("TimeframeForReservation");

        cityAndParkingName.setText(name);
        dateForReservation.setText(date);
        timeFrameForReservation.setText(time);

        delete = getActivity().findViewById(R.id.DeleteReservation);
        delete.setBackgroundColor(Color.RED);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteReservation(reservationID);
            }
        });

        navigate = getActivity().findViewById(R.id.NavigateToDestination);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), MapParking.class);
//                startActivity(intent);
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
