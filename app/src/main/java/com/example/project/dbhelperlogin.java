package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbhelperlogin extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBLogin.db";
    public static final String CONTACTS_TABLE_NAME = "users";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_BG = "bg";
    public static final String CONTACTS_COLUMN_GST = "gst";
    public static final String CONTACTS_COLUMN_Pass = "password";
    public static final String CONTACTS_COLUMN_ConPass = "confirm password";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    public static final String CONTACTS_COLUMN_Gender = "gender";


    public dbhelperlogin(Context context) {

        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table "+ CONTACTS_TABLE_NAME+
                        "(id integer primary key, name text,phone text,email text, bg text,gst text," +
                        "password text,gender text,confirmpassword text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact (String name, String phone, String email, String bg,String gst,String pass,String conpass,String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("bg", bg);
        contentValues.put("gst", gst);
        contentValues.put("pass", pass);
        contentValues.put("conpass", conpass);
        contentValues.put("gender", gender);

        db.insert("users", null, contentValues);
        return true;
    }

    public Boolean insertuser(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_EMAIL,email);
        contentValues.put(CONTACTS_COLUMN_Pass,pass);
        db.insert(CONTACTS_TABLE_NAME,null,contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME + " where "+CONTACTS_COLUMN_ID+"="+id+"", null );
        return res;
    }
    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CONTACTS_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_Pass)));
            res.moveToNext();
        }
        return array_list;
    }
}


