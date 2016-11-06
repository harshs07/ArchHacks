package com.example.shahh.pfrecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shahh on 11/5/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contacts.db";
    private static final String TABLE_NAME="contacts";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_UNAME="uname";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_INSURANCE="insurance";
    private static final String COLUMN_TIMESTAMP="timestamp";
    private static final String COLUMN_PASS="pass";
    protected SQLiteDatabase db;
    private static final String TABLE_CREATE="create table contacts (id integer primary key not null , " +
            "name text not null,uname text not null, email text not null, insurance integer not null , " +
            "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, pass text not null);";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IF EXISTS" +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void insertContact(Contact c){
          db=this.getWritableDatabase();

        ContentValues values =new ContentValues();

        String query="select * from contacts";
        Cursor cursor=db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_INSURANCE, c.getInsurance());
        values.put(COLUMN_PASS, c.getPassword());
        values.put(COLUMN_TIMESTAMP, getDateTime());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public String searchPass(String username){
        db=this.getReadableDatabase();
        String user;
        String pass="Not Found";
        String query="select uname,pass from " + TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                user = cursor.getString(0);
                if (user.equals(username)) {
                    pass = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return pass;
    }
}
