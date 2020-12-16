package com.example.findparking.Models;

public class Parking {
    private int parkingId;
    private String name;
    private Coordinates coordinates;
    private int maxParkingSpaces;
    private boolean isFree;
    private int hourleFee;
    private int specialNeedsParking; //number of parking spaces suitable for special needs

    public Parking(int parkingId, String name, Coordinates coordinates, int maxParkingSpaces, boolean isFree, int hourleFee, int specialNeedsParking) {
        this.parkingId = parkingId;
        this.name = name;
        this.coordinates = coordinates;
        this.maxParkingSpaces = maxParkingSpaces;
        this.isFree = isFree;
        this.hourleFee = hourleFee;
        this.specialNeedsParking = specialNeedsParking;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getMaxParkingSpaces() {
        return maxParkingSpaces;
    }

    public void setMaxParkingSpaces(int maxParkingSpaces) {
        this.maxParkingSpaces = maxParkingSpaces;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public int getHourleFee() {
        return hourleFee;
    }

    public void setHourleFee(int hourleFee) {
        this.hourleFee = hourleFee;
    }

    public int getSpecialNeedsParking() {
        return specialNeedsParking;
    }

    public void setSpecialNeedsParking(int specialNeedsParking) {
        this.specialNeedsParking = specialNeedsParking;
    }
}
