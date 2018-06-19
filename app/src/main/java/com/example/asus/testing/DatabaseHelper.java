package com.example.asus.testing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "myschool", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE student" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fname TEXT," +
                "lname TEXT," +
                "phone TEXT," +
                "email TEXT," +
                "date TEXT," +
                "gender TEXT," +
                "grade TEXT," +
                "hobby TEXT," +
                "address TEXT," +
                "image BLOB)";



        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
