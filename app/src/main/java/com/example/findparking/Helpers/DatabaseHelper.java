package com.example.findparking.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.findparking.Cities;
import com.example.findparking.MainActivity;
import com.example.findparking.Models.City;
import com.example.findparking.Models.Parking;
import com.example.findparking.Models.Reservation;
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
    public static final String TIME_FOR_RESERVATION = "time_for_reservation";


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
                " `" + DATE_FOR_RESERVATION + "` DATE NOT NULL, `" + TIME_FOR_RESERVATION + "` INT," + "`" + QRCODE + "` TEXT NOT NULL," +
                " `" + USER_ID + "` INT NOT NULL, `" + PARKING_ID + "` INT NOT NULL," +
                "FOREIGN KEY(`" + USER_ID + "`) REFERENCES " + USER + "(`"+ USER_ID + "`), " +
                "FOREIGN KEY(`" + PARKING_ID + "`) REFERENCES " + PARKING + "(`" + PARKING_ID + "`))";
        db.execSQL(createReservationTable);
        ContentValues cv = new ContentValues();
        List<City> returnlist = new ArrayList<>();
        returnlist.add(new City(1,"Skopje", "Macedonia", (float)41.9981002807617, (float)21.4253997802734));
        returnlist.add(new City(1,"Krushevo", "Macedonia", (float)41.3706016540527, (float)21.2502002716064));
        returnlist.add(new City(1,"Ohrid", "Macedonia", (float)41.1231002807617, (float)20.8015995025635));
        returnlist.add(new City(1,"Bitola", "Macedonia", (float)41.0297012329102, (float)21.3292007446289));
        returnlist.add(new City(1,"Shtip", "Macedonia", (float)41.7463989257813, (float)22.1996994018555));
        returnlist.add(new City(1,"Kochani", "Macedonia", (float)41.9168014526367, (float)22.4083003997803));
        returnlist.add(new City(1,"Strumica", "Macedonia", (float)41.437801361084, (float)22.6427001953125));
        returnlist.add(new City(1,"Veles", "Macedonia", (float)41.7164993286133, (float)21.7723007202148));
        for (int i =0; i<returnlist.size(); i++) {
            cv.put(NAME, returnlist.get(i).getCity());
            cv.put(COUNTRY, returnlist.get(i).getCountry());
            cv.put(LATITUDE, returnlist.get(i).getCoordinates().getLatitude());
            cv.put(LONGITUDE, returnlist.get(i).getCoordinates().getLongitude());
            db.insert(CITY, null, cv);
        }
        cv.clear();

        List<Parking> parkingList = new ArrayList<>();

        parkingList.add(new Parking(1, "Skopje Parking", 100, (float)41.9981002807617, (float)21.4253997802734, 5, 0));
        parkingList.add(new Parking(1, "Krusevo Parking", 100, (float)41.3706016540527, (float)21.2502002716064, 5, 0));
        parkingList.add(new Parking(1, "Ohrid Parking", 100, (float)41.1231002807617, (float)20.8015995025635, 5, 0));
        parkingList.add(new Parking(1, "Bitola Parking", 100, (float)41.0297012329102, (float)21.3292007446289, 5, 0));
        parkingList.add(new Parking(1, "Shtip Parking", 100, (float)41.7463989257813, (float)22.1996994018555, 5, 0));
        parkingList.add(new Parking(1, "Kochani Parking", 100, (float)41.9168014526367, (float)22.4083003997803, 5, 0));
        parkingList.add(new Parking(1, "Strumica Parking", 100, (float)41.437801361084, (float)22.6427001953125, 5, 0));
        parkingList.add(new Parking(1, "Veles Parking", 100, (float)41.7164993286133, (float)21.7723007202148, 5, 0));
        parkingList.add(new Parking(1, "Skopje Parking", 100, (float)41.9981002807617, (float)21.4253997802734, 5, 0));
        parkingList.add(new Parking(1, "Krusevo Parking", 100, (float)41.3706016540527, (float)21.2502002716064, 5, 0));
        parkingList.add(new Parking(1, "Ohrid Parking", 100, (float)41.1231002807617, (float)20.8015995025635, 5, 0));
        parkingList.add(new Parking(1, "Bitola Parking", 100, (float)41.0297012329102, (float)21.3292007446289, 5, 0));
        parkingList.add(new Parking(1, "Shtip Parking", 100, (float)41.7463989257813, (float)22.1996994018555, 5, 0));
        parkingList.add(new Parking(1, "Kochani Parking", 100, (float)41.9168014526367, (float)22.4083003997803, 5, 0));
        parkingList.add(new Parking(1, "Strumica Parking", 100, (float)41.437801361084, (float)22.6427001953125, 5, 0));
        parkingList.add(new Parking(1, "Veles Parking", 100, (float)41.7164993286133, (float)21.7723007202148, 5, 0));
        parkingList.add(new Parking(1, "Skopje Parking", 100, (float)41.9981002807617, (float)21.4253997802734, 5, 0));
        parkingList.add(new Parking(1, "Krusevo Parking", 100, (float)41.3706016540527, (float)21.2502002716064, 5, 0));
        parkingList.add(new Parking(1, "Ohrid Parking", 100, (float)41.1231002807617, (float)20.8015995025635, 5, 0));
        parkingList.add(new Parking(1, "Bitola Parking", 100, (float)41.0297012329102, (float)21.3292007446289, 5, 0));
        parkingList.add(new Parking(1, "Shtip Parking", 100, (float)41.7463989257813, (float)22.1996994018555, 5, 0));
        parkingList.add(new Parking(1, "Kochani Parking", 100, (float)41.9168014526367, (float)22.4083003997803, 5, 0));
        parkingList.add(new Parking(1, "Strumica Parking", 100, (float)41.437801361084, (float)22.6427001953125, 5, 0));
        parkingList.add(new Parking(1, "Veles Parking", 100, (float)41.7164993286133, (float)21.7723007202148, 5, 0));
        parkingList.add(new Parking(1, "Skopje Parking", 100, (float)41.9981002807617, (float)21.4253997802734, 5, 0));
        parkingList.add(new Parking(1, "Krusevo Parking", 100, (float)41.3706016540527, (float)21.2502002716064, 5, 0));
        parkingList.add(new Parking(1, "Ohrid Parking", 100, (float)41.1231002807617, (float)20.8015995025635, 5, 0));
        parkingList.add(new Parking(1, "Bitola Parking", 100, (float)41.0297012329102, (float)21.3292007446289, 5, 0));
        parkingList.add(new Parking(1, "Shtip Parking", 100, (float)41.7463989257813, (float)22.1996994018555, 5, 0));
        parkingList.add(new Parking(1, "Kochani Parking", 100, (float)41.9168014526367, (float)22.4083003997803, 5, 0));
        parkingList.add(new Parking(1, "Strumica Parking", 100, (float)41.437801361084, (float)22.6427001953125, 5, 0));
        parkingList.add(new Parking(1, "Veles Parking", 100, (float)41.7164993286133, (float)21.7723007202148, 5, 0));

        for (int i =0; i<parkingList.size(); i++) {
            cv.put(NAME, parkingList.get(i).getName() + " " + String.valueOf((i/8)+1));
            cv.put(PARKING_SPACES, parkingList.get(i).getMaxParkingSpaces());
            cv.put(LATITUDE, parkingList.get(i).getCoordinates().getLatitude());
            cv.put(LONGITUDE, parkingList.get(i).getCoordinates().getLongitude());
            cv.put(SPEACIAL_NEEDS_PARKING, parkingList.get(i).getSpecialNeedsParking());
            cv.put(HOURLY_FEE, parkingList.get(i).getHourlyFee());
            cv.put(CITY_ID, (i%8)+1);
            db.insert(PARKING, null, cv);
        }
    }

    public void create(){
        /*SQLiteDatabase dbwrite = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        List<Reservation> returnlist = new ArrayList<>();
        returnlist.add(new Reservation(1, "30/12/20", 0));
        returnlist.add(new Reservation(1, "30/12/20", 0));

        for (int i =0; i<returnlist.size(); i++) {
            cv.put(DATE_FOR_RESERVATION, returnlist.get(i).getReservationDate());
            cv.put(TIME_FOR_RESERVATION, returnlist.get(i).getTime());
            cv.put(QRCODE, returnlist.get(i).toString(1, 1));
            cv.put(USER_ID, 1);
            cv.put(PARKING_ID, 1);
            dbwrite.insert(RESERVATION, null, cv);
        }*/
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

    public int getUser(String email){
        String query = "SELECT * FROM " + USER + " WHERE email = '" + email + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int userID = -1;

        if (cursor.moveToFirst()) {
            userID = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return userID;
    }

    public int getUser(int userID){
        String query = "SELECT * FROM " + USER + " WHERE user_id = " + userID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int user_id = -1;

        if (cursor.moveToFirst()) {
            user_id = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return user_id;
    }

    public User loginCredentials(String Lemail, String Lpassword){
        User user;
        String query = "SELECT * FROM " + USER + " WHERE email = '" + Lemail + "' and password = '" + Lpassword + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst() && cursor.isLast()) {
            int userID = cursor.getInt(0);
            String name = cursor.getString(1);
            String birth_date = cursor.getString(2);
            String email = cursor.getString(3);
            int phonenumber = cursor.getInt(5);

            user = new User(userID, name, birth_date, email, null, phonenumber);
        }
        else
        {
            user = null;
        }
        cursor.close();
        db.close();
        return user;
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

    public String getCityName(int city_id) {
        String query = "SELECT * FROM " + CITY + " WHERE city_id = " + String.valueOf(city_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String name;
        if (cursor.moveToFirst()) {
            name = cursor.getString(1);
        }
        else
        {
            name = "Some Error";
        }
        cursor.close();
        db.close();
        return name;

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

                Parking getParking = new Parking(parkingID, name,parkingSpaces , latitude, longitude, fee, specialNeeds);
                returnlist.add(getParking);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;
    }

    public int ReservedSpacesFor(String date, int time, int city_id, int parking_id) {
        String query = "SELECT * FROM " + RESERVATION + " WHERE "+ DATE_FOR_RESERVATION+" = '" + date +
                "' and " + PARKING_ID + " = " + String.valueOf(parking_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int returnValue = cursor.getCount();
        cursor.close();
        db.close();
        return returnValue;
    }

    public int NumberOfReservations(int user_id) {
        String query = "SELECT * FROM " + RESERVATION + " WHERE "+ USER_ID+" = " + String.valueOf(user_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int returnValue = cursor.getCount();
        cursor.close();
        db.close();
        return returnValue;
    }

    public boolean addReservation(Reservation newreservation, int user_id, int parking_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DATE_FOR_RESERVATION, newreservation.getReservationDate());
        cv.put(TIME_FOR_RESERVATION, newreservation.getTime());
        cv.put(QRCODE, newreservation.toString(user_id, parking_id));
        cv.put(USER_ID, user_id);
        cv.put(PARKING_ID, parking_id);
        long insert = db.insert(RESERVATION, null, cv);

        return insert>0;
    }

    public String fixTimeFrame(int hour)
    {
        String result = "";
        if (hour<10)
        {
            result += "0" + hour;
        }
        else
        {
            result += hour;
        }
        result += ":00-";
        ++hour;
        if (hour<10)
        {
            result += "0" + hour;
        }
        else
        {
            result += hour;
        }
        result += ":00";
        return result;
    }

    public List<Reservation> getReservations(int user_id) {
        List<Reservation> returnlist = new ArrayList<>();
        String query = "SELECT * FROM " + RESERVATION + " WHERE "+ USER_ID+" = " + String.valueOf(user_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int reservationID = cursor.getInt(0);
                String date = cursor.getString(1);
                int time = cursor.getInt(2);

                Reservation getReservation = new Reservation(reservationID, date, time);
                returnlist.add(getReservation);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;
    }

    public int ParkingForReservation(int reservation_id) {
        String query = "SELECT * FROM " + RESERVATION + " WHERE "+ RESERVATION_ID+" = " + String.valueOf(reservation_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int returnValue = cursor.getInt(5);

        cursor.close();
        db.close();
        return returnValue;
    }

    public Reservation getReservation(int user_id, int parking_id, int time, String date) {
        String query = "SELECT * FROM " + RESERVATION + " WHERE "+ USER_ID+" = " + String.valueOf(user_id) +
                " and " + PARKING_ID + " = " + String.valueOf(parking_id) + " and " + TIME_FOR_RESERVATION +" = " + String.valueOf(time) +
                " and " + DATE_FOR_RESERVATION + " = '" + date + "'"  ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Reservation getReservation = null;
        if (cursor.moveToFirst()) {
                int reservationID = cursor.getInt(0);
                String dateD = cursor.getString(1);
                int timeD = cursor.getInt(2);

                getReservation = new Reservation(reservationID, dateD, timeD);

            }
        cursor.close();
        db.close();
        return getReservation;
    }


    public void deleteReservation(int reservationID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RESERVATION, RESERVATION_ID + "=" + String.valueOf(reservationID), null);
    }

    public Parking getParking(int reservation_id) {
        String query = "SELECT * FROM " + PARKING + " WHERE " + PARKING_ID + " = " + String.valueOf(ParkingForReservation(reservation_id));
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Parking parking = null;
        if (cursor.moveToFirst()) {
            int parkingID = cursor.getInt(0);
            String name = cursor.getString(1);
            int parkingSpaces = cursor.getInt(2);
            float latitude = cursor.getFloat(3);
            float longitude = cursor.getFloat(4);
            int specialNeeds = cursor.getInt(5);
            int fee = cursor.getInt(6);

            parking = new Parking(parkingID, name, parkingSpaces , latitude, longitude, fee, specialNeeds);
        }
        cursor.close();
        db.close();
        return parking;
    }

    public City getCityFromParking(int parking_id) {
        String query = "SELECT * FROM " + PARKING + " WHERE " + PARKING_ID + " = " + String.valueOf(parking_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Parking parking = null;
        int cityID;
        City city = null;
        if (cursor.moveToFirst()) {
            cityID = cursor.getInt(7);
            query = "SELECT * FROM " + CITY + " WHERE " + CITY_ID + " = " + String.valueOf(cityID);
            Cursor cursorCity = db.rawQuery(query, null);
            if (cursorCity.moveToFirst()) {
                String name = cursor.getString(1);
                String country = cursor.getString(2);
                float latitude = cursor.getFloat(3);
                float longitude = cursor.getFloat(4);

                city = new City(cityID, name, country, latitude, longitude);

            }
            cursorCity.close();
        }
        cursor.close();
        db.close();
        return city;
    }
}
