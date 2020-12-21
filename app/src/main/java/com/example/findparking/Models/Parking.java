package com.example.findparking.Models;

public class Parking {
    private int parkingId;
    private String name;
    private Coordinates coordinates;
    private int maxParkingSpaces;
    private int hourlyFee;
    private int specialNeedsParking; //number of parking spaces suitable for special needs

    public Parking(int parkingId, String name, float latitude, float longitude, int maxParkingSpaces, int hourlyFee, int specialNeedsParking) {
        this.parkingId = parkingId;
        this.name = name;
        this.coordinates = new Coordinates(latitude, longitude);
        this.maxParkingSpaces = maxParkingSpaces;
        this.hourlyFee = hourlyFee;
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

    public int getHourlyFee() {
        return hourlyFee;
    }

    public void setHourlyFee(int hourleFee) {
        this.hourlyFee = hourleFee;
    }

    public int getSpecialNeedsParking() {
        return specialNeedsParking;
    }

    public void setSpecialNeedsParking(int specialNeedsParking) {
        this.specialNeedsParking = specialNeedsParking;
    }
}
