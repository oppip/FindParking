package com.example.findparking.Helpers;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.findparking.Models.Reservation;
import com.example.findparking.R;
import com.example.findparking.ReservationConfrimation;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRCodeFragment extends Fragment {

    String textToEncode = "";
    Session session;

    public QRCodeFragment() {
    }

    public static QRCodeFragment newInstance(String fragment1) {
        QRCodeFragment fragment = new QRCodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", fragment1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            textToEncode = getArguments().getString("text");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView QRCode = getActivity().findViewById(R.id.QRCode);
        session = new Session(getActivity().getApplicationContext());
        if(textToEncode.isEmpty())
        {
            Toast.makeText(getContext(), "There is an error with the reservation", Toast.LENGTH_SHORT).show();
        }
        else {
            QRGEncoder qrgEncoder = new QRGEncoder(textToEncode, null, QRGContents.Type.TEXT, 500);
            try {
                Bitmap bitmap = qrgEncoder.getBitmap();
                QRCode.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(getActivity() instanceof ReservationConfrimation) {
            textToEncode = getActivity().getIntent().getExtras().getString("QRCodeText");
        }
        return inflater.inflate(R.layout.fragment_q_r_code, container, false);
    }

    /*
        KOGA KJE JA PRAKJAM VREDNOSTA NA RESERVATIONS VAKA OBAVEZNO!!!

        Bundle bundle = new Bundle();
        bundle.putString("edttext", "From Activity");
        // set Fragmentclass Arguments
        Fragmentclass fragobj = new Fragmentclass();
        fragobj.setArguments(bundle);


        EVENTUALNO AKO MI TREBA KAO SLIKA
        QRGSaver qrgSaver = new QRGSaver();
        qrgSaver.save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
    */
}