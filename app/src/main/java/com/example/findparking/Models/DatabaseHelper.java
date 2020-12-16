package com.example.findparking.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

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

    public DatabaseHelper(@Nullable Context context) {
        super(context, "parking.db", null, 1);
    }

    //called the first time you access the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE `" + USER + "` (`" + USER_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `" + NAME + "` VARCHAR(40), `" + BIRTH_DATE + "` DATE, " +
                " `" + EMAIL + "` VARCHAR(30), `" + PASSWORD + "` VARCHAR(500), `" + PHONE_NUMBER + "` BIGINT DEFAULT NULL);";
        db.execSQL(createTable);
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
}
