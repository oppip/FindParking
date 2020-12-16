package com.example.findparking.Models;

import java.net.PasswordAuthentication;
import java.util.Date;

public class User {
    private int userId;
    private String name;
    private String dateOfBirth;
    private String email;
    private String password;
    private long phoneNumber;

    public User(int userId, String filip, String dateOfBirth, String email, String jasu, Long o) {
    }

    public User(int userId, String name, String dateOfBirth, String email, String password, long phoneNumber) {
        this.userId = userId; //dali ova vaka kje ostane?
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    //getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
