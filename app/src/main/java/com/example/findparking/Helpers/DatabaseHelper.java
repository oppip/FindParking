package com.example.findparking.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.findparking.Models.City;
import com.example.findparking.Models.Parking;
import com.example.findparking.Models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USER_ID = "user_id";
    public static final String NAME = "name";
    public static final String BIRTH_DATE = "birth_date";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String USER = "User";
    public static final String CITY = "City";
    public static final String CITY_ID = "city_id";
    public static final String PARKING = "Parking";
    public static final String PARKING_ID = "parking_id";
    public static final String COUNTRY = "country";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String PARKING_SPACES = "parking_spaces";
    public static final String SPEACIAL_NEEDS_PARKING = "speacial_needs_parking";
    public static final String HOURLY_FEE = "hourly_fee";
    public static final String RESERVATION = "Reservation";
    public static final String RESERVATION_ID = "reservation_id";
    public static final String DATE_FOR_RESERVATION = "date_for_reservation";
    public static final String QRCODE = "QRcode";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "parking.db", null, 1);
    }

    //called the first time you access the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //USER TABLE
        String createUserTable = "CREATE TABLE `" + USER + "` (`" + USER_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `" + NAME + "` VARCHAR(40), `" +
                BIRTH_DATE + "` DATE, " + " `" + EMAIL + "` VARCHAR(30), `" + PASSWORD +
                "` VARCHAR(500), `" + PHONE_NUMBER + "` BIGINT DEFAULT NULL);";
        db.execSQL(createUserTable);
        //CITY TABLE
        String createCityTable = "CREATE TABLE `" + CITY + "` ( `" + CITY_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `" + NAME + "` VARCHAR(20) NOT NULL," +
                " `" + COUNTRY + "` VARCHAR(40) NOT NULL DEFAULT 'Macedonia', `" + LATITUDE + "` FLOAT NOT NULL, `" + LONGITUDE + "` FLOAT NOT NULL)";
        db.execSQL(createCityTable);
        //PARKING TABLE
        String createParkingTable = "CREATE TABLE `" + PARKING + "` (`" + PARKING_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `" + NAME + "` VARCHAR(30) NOT NULL, " +
                " `" + PARKING_SPACES + "` INT NOT NULL, `" + LATITUDE + "` FLOAT NOT NULL, `" + LONGITUDE + "` FLOAT NOT NULL," +
                "`" + SPEACIAL_NEEDS_PARKING + "` INT NOT NULL, `" + HOURLY_FEE + "` INT DEFAULT NULL, `" + CITY_ID + "` INT," +
                "FOREIGN KEY(`" + CITY_ID + "`) REFERENCES " + CITY + "(`" + CITY_ID + "`))";
        db.execSQL(createParkingTable);
        //RESERVATION TABLE
        String createReservationTable = "CREATE TABLE `" + RESERVATION + "` ( `" + RESERVATION_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT," +
                " `" + DATE_FOR_RESERVATION + "` DATETIME NOT NULL," + "`" + QRCODE + "` TEXT NOT NULL," +
                " `" + USER_ID + "` INT NOT NULL, `" + PARKING_ID + "` INT NOT NULL," +
                "FOREIGN KEY(`" + USER_ID + "`) REFERENCES " + USER + "(`"+ USER_ID + "`), " +
                "FOREIGN KEY(`" + PARKING_ID + "`) REFERENCES " + PARKING + "(`" + PARKING_ID + "`))";
        db.execSQL(createReservationTable);
    }

    public void create(){
        /*SQLiteDatabase dbwrite = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, "Krushevo");
        dbwrite.update(CITY, cv, "city_id = ?", new String[]{"2"});*/

    }

    public boolean addUser(User newuser){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, newuser.getName());
        cv.put(BIRTH_DATE, newuser.getDateOfBirth());
        cv.put(EMAIL, newuser.getEmail());
        cv.put(PASSWORD, newuser.getPassword());
        cv.put(PHONE_NUMBER, newuser.getPhoneNumber());

        long insert = db.insert(USER, null, cv);

        return insert>0;
    }

    public List<User> getAllUsers(){
        List<User> returnlist = new ArrayList<>();
        String query = "SELECT * FROM " + USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int userID = cursor.getInt(0);
                String name = cursor.getString(1);
                String birth_date = cursor.getString(2);
                String email = cursor.getString(3);
                int phonenumber = cursor.getInt(5);

                User getuser = new User(userID, name, birth_date, email, null, phonenumber);
                returnlist.add(getuser);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnlist;
    }


    //if you update a database table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<City> getAllCities() {
        List<City> returnlist = new ArrayList<>();
        String query = "SELECT * FROM " + CITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int cityID = cursor.getInt(0);
                String name = cursor.getString(1);
                String country = cursor.getString(2);
                float latitude = cursor.getFloat(3);
                float longitude = cursor.getFloat(4);

                City getCity = new City(cityID, name, country, latitude, longitude);
                returnlist.add(getCity);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;

    }

    public List<Parking> getAllParkingsForCity(int city_id) {
        List<Parking> returnlist = new ArrayList<>();
        String query = "SELECT * FROM " + PARKING + " WHERE city_id = " + String.valueOf(city_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int parkingID = cursor.getInt(0);
                String name = cursor.getString(1);
                int parkingSpaces = cursor.getInt(2);
                float latitude = cursor.getFloat(3);
                float longitude = cursor.getFloat(4);
                int specialNeeds = cursor.getInt(5);
                int fee = cursor.getInt(6);

                Parking getParking = new Parking(parkingID, name, latitude, longitude,parkingSpaces, fee, specialNeeds);
                returnlist.add(getParking);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;
    }
}