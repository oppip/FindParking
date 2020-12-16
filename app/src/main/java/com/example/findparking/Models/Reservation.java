package com.example.findparking.Models;

import java.util.Date;

public class Reservation {
    private int reservationId;
    private Date reservationDate;
    private int time;

    public Reservation(int reservationId, Date reservationDate, int time) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.time = time;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public int getTime() {
        return time;
    }
}

