package com.example.appsqlciudades.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class CiudadSQLiteHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "CiudadesDB";
    static final int DATABASE_VERSION = 1;

    static final String CREATE_TABLE_CIUDADES=
            "CREATE TABLE "+ CiudadContract.CiudadEntry.TABLE_NAME+ "( "+
                    CiudadContract.CiudadEntry.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    CiudadContract.CiudadEntry.COLUMN_NAME+" TEXT NOT NULL," +
                    CiudadContract.CiudadEntry.COLUMN_PROVINCE+" TEXT NOT NULL," +
                    CiudadContract.CiudadEntry.COLUMN_INHAB +"INTEGER NOT NULL);";

    public CiudadSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CIUDADES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +CiudadContract.CiudadEntry.TABLE_NAME);
        onCreate(db);
    }
}
