package com.example.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbhelpermenu extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBMenu.db";
    public static final String CONTACTS_TABLE_NAME = "items";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "itemname";
    public static final String CONTACTS_COLUMN_TYPE = "itemname";
    public static final String CONTACTS_COLUMN_COST = "itemprice";


    public dbhelpermenu(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
