package com.example.findparking.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setEmail(String email) {
        prefs.edit().putString("email", email).commit();
    }

    public String getEmail() {
        String email = prefs.getString("email","");
        return email;
    }

    public void setUserID(int user_id)
    {
        prefs.edit().putInt("user_id", user_id).commit();
    }

    public int getUserID()
    {
        int userID = prefs.getInt("user_id",-1);
        return userID;
    }

    public void setTime(int time)
    {
        prefs.edit().putInt("time", time).commit();
    }

    public int getTime()
    {
        int time = prefs.getInt("time",-1);
        return time;
    }

    public void setDate(String date)
    {
        prefs.edit().putString("date", date).commit();
    }

    public String getDate()
    {
        String date = prefs.getString("date","");
        return date;
    }

    public void setCityPicked(int city_id)
    {
        prefs.edit().putInt("cityPicked", city_id).commit();
    }

    public int getCityPicked()
    {
        int cityPicked = prefs.getInt("cityPicked",-1);
        return cityPicked;
    }

    public boolean hasValue(String checkKey)
    {
        return prefs.contains(checkKey);
    }

    public void  deleteAll()
    {
        prefs.edit().clear().commit();
    }

}
