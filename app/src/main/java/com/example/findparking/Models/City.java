package com.example.findparking.Models;

public class City {
    private int cityId;
    private String city;
    private String country;
    private Coordinates coordinates;
    private boolean expanded;

    public City(int cityId, String city, String country, float latitude, float longitude) {
        this.cityId = cityId;
        this.city = city;
        this.country = country;
        this.coordinates = new Coordinates(latitude, longitude);
        this.expanded = false;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }

    public boolean isExpanded() {
        return this.expanded;
    }
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
