package com.example.findparking.Models;

public class Reservation {
    private int reservationId;
    private String reservationDate;
    private int time;

    public Reservation(int reservationId, String reservationDate, int time) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.time = time;
    }

    public String toString(int user, int parking) {
        return "" + reservationId + "%^&" + reservationDate + "%^&" + time + "%^&" + user + "%^&" + parking;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public int getTime() {
        return time;
    }
}

