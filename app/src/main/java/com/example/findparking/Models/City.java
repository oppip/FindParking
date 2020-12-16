package com.example.findparking.Models;

public class City {
    private int cityId;
    private String city;
    private String country;
    private Coordinates coordinates;

    public City(int cityId, String city, String country, double latitude, double longitude) {
        this.cityId = cityId;
        this.city = city;
        this.country = country;
        this.coordinates = new Coordinates(latitude, longitude);
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}
