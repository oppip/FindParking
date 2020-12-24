package com.example.findparking.Helpers;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.findparking.Cities;
import com.example.findparking.MainActivity;
import com.example.findparking.R;
import com.example.findparking.ReservationForm;

import java.text.DateFormat;
import java.util.Calendar;

public class ReservationFormFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    String time;
    EditText date;
    Spinner spinner;
    Button createReservation;
    Session session;

    private DatePicker view;
    private int year;
    private int month;
    private int dayOfMonth;

    public ReservationFormFragment() {
    }

    public static ReservationFormFragment newInstance() {
        ReservationFormFragment fragment = new ReservationFormFragment();
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

        session = new Session(getActivity().getApplicationContext());

        date = getActivity().findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        spinner = getActivity().findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Nothing has been selected", Toast.LENGTH_SHORT).show();
            }
        });

        createReservation = getActivity().findViewById(R.id.createReservation);

        createReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkTime = Integer.parseInt(time.split(":")[0]);
                String datePicked = date.getText().toString();

                if (datePicked.isEmpty()) {
                    Toast.makeText(getContext(), "You need to pick a date and time!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    session.setTime(checkTime);
                    session.setDate(datePicked);
                    if (getActivity() instanceof ReservationForm) {
                        getActivity().finish();
                    }
                    else
                    {
                        ((Cities)getActivity()).notifyAdapter();
                    }
                }

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        String[] arr = currentDateString.split("/");
        String eudate = arr[1] + "/" + arr[0] + "/" + arr[2];
        EditText editText = (EditText) getActivity().findViewById(R.id.date);
        editText.setText(eudate);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spinner, container, false);
    }
}