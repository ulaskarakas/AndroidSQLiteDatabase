package com.ulask.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "members";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PERSONS = "persons";
    private static final String ROW_ID = "id";
    private static final String ROW_NAME = "name";
    private static final String ROW_SURNAME = "surname";
    private static final String ROW_PHONE = "phone";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PERSONS + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_NAME + " TEXT NOT NULL, "
                + ROW_SURNAME + " TEXT NOT NULL, "
                + ROW_PHONE + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PERSONS);
        onCreate(db);
    }

    //Save Data
    public void SaveData(String name, String surname, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_NAME, name);
            cv.put(ROW_SURNAME, surname);
            cv.put(ROW_PHONE, phone);
            db.insert(TABLE_PERSONS, null, cv);
        }catch (Exception e){

        }
        db.close();
    }

    //List Data
    public List<String> ListData() {
        List<String> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {ROW_ID, ROW_NAME, ROW_SURNAME, ROW_PHONE};
            Cursor cursor = db.query(TABLE_PERSONS, rows, null, null, null, null, null);
            while (cursor.moveToNext()){
                data.add(cursor.getInt(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2) + " - " + cursor.getString(3));
            }
        }catch (Exception e){

        }
        db.close();
        return data;
    }

    public void DeleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            //Delete data by id
            String where = ROW_NAME + " = '" + name + "'";
            db.delete(TABLE_PERSONS, where, null);
        }catch (Exception e){

        }
        db.close();
    }

}