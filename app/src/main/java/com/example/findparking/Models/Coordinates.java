package com.example.findparking.Models;

public class Coordinates {
    private float latitude;
    private float longitude;

    public Coordinates(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates getCoordinates(){
        return new Coordinates(this.latitude, this.longitude);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
