package com.example.appsqlciudades.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appsqlciudades.model.Ciudad;

import java.util.ArrayList;

public class CiudadDatasource {

    private CiudadSQLiteHelper csh;

    /*--------------------------------          CONSTRUCTOR           ----------------------------*/
    public CiudadDatasource(Context context) {
        csh = new CiudadSQLiteHelper(context);
    }

    /*--------------------------------          ACCESO DB          ----------------------------*/
    public SQLiteDatabase openReadable() {
        return csh.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return csh.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }

    /*--------------------------------           CONSULTA           ----------------------------*/
    public Ciudad consultarCiudad(long idCiudad) {
        SQLiteDatabase sdb = openReadable();

        //TODO cambiado por *
        String select = "SELECT * FROM " +CiudadContract.CiudadEntry.TABLE_NAME +
                " WHERE " + CiudadContract.CiudadEntry.COLUMN_ID + " = ?";
        String[] args = {String.valueOf(idCiudad)};

        Cursor cursor = sdb.rawQuery(select, args);

        Ciudad ciudad = null;
        int id;
        String nombre;
        String provincia;
        long habitantes;

        if(cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(CiudadContract.CiudadEntry.COLUMN_ID));
            nombre = cursor.getString(cursor.getColumnIndex(CiudadContract.CiudadEntry.COLUMN_NAME));
            provincia = cursor.getString(cursor.getColumnIndex(CiudadContract.CiudadEntry.COLUMN_PROVINCE));
            habitantes = cursor.getLong(cursor.getColumnIndex(CiudadContract.CiudadEntry.COLUMN_INHAB));
            ciudad = new Ciudad(id, nombre, provincia, habitantes);
        }

        cursor.close();
        sdb.close();
        return  ciudad;

    }

    public ArrayList<Ciudad> consultarCiudades() {
        ArrayList<Ciudad> listaCiudades = new ArrayList<Ciudad>();
        SQLiteDatabase sdb = openReadable();

        //TODO probando columnas a null
        Cursor cursor = sdb.query(CiudadContract.CiudadEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null); //TODO Ordenar por id

        int id;
        String nombre;
        String provincia;
        long habitantes;

        //TODO probando con while en vez de do while()
        while(cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(CiudadContract.CiudadEntry.COLUMN_ID));
            nombre = cursor.getString(cursor.getColumnIndex(CiudadContract.CiudadEntry.COLUMN_NAME));
            provincia = cursor.getString(cursor.getColumnIndex(CiudadContract.CiudadEntry.COLUMN_PROVINCE));
            habitantes = cursor.getLong(cursor.getColumnIndex(CiudadContract.CiudadEntry.COLUMN_INHAB));
            listaCiudades.add(new Ciudad(id, nombre, provincia, habitantes));
        }

        cursor.close();
        sdb.close();
        return listaCiudades;
    }

    /*--------------------------------             ALTA             ----------------------------*/
    public long insertarCiudad(Ciudad ciudad) {
        SQLiteDatabase sdb = openWriteable();

        sdb.beginTransaction();

        ContentValues cv = new ContentValues();
        cv.put(CiudadContract.CiudadEntry.COLUMN_NAME, ciudad.getNombre());
        cv.put(CiudadContract.CiudadEntry.COLUMN_PROVINCE, ciudad.getProvincia());
        cv.put(CiudadContract.CiudadEntry.COLUMN_INHAB, ciudad.getHabitantes());

        long id = sdb.insert(CiudadContract.CiudadEntry.TABLE_NAME, null, cv);

        if(id != -1) {
            sdb.setTransactionSuccessful();
        }

        sdb.endTransaction();
        close(sdb);
        return id;
    }

    /*--------------------------------          MODIFICACIÓN        ----------------------------*/
    public int modificarCiudad(Ciudad ciudad) {
        SQLiteDatabase sdb = openWriteable();
        sdb.beginTransaction();

        ContentValues cv = new ContentValues();
        //cv.put(CiudadContract.CiudadEntry.COLUMN_NAME, ciudad.getNombre());
        //cv.put(CiudadContract.CiudadEntry.COLUMN_PROVINCE, ciudad.getProvincia());
        cv.put(CiudadContract.CiudadEntry.COLUMN_INHAB, ciudad.getHabitantes());

        String clausulaWhere = CiudadContract.CiudadEntry.COLUMN_ID + " = ?";
        String[] args = {String.valueOf(ciudad.getId())};

        int i = sdb.update(CiudadContract.CiudadEntry.TABLE_NAME,
                cv,
                clausulaWhere,
                args);

        if (i != 0) {
            sdb.setTransactionSuccessful();
        }
        sdb.endTransaction();
        close(sdb);
        return i;
    }


    /*--------------------------------            ELIMINAR          ----------------------------*/
    public int borrarCiudad(long idCiudad) {
        SQLiteDatabase sdb = openWriteable();
        sdb.beginTransaction();

        String clausulaWhere = CiudadContract.CiudadEntry.COLUMN_ID + " = "
                + idCiudad;
        int i = sdb.delete(CiudadContract.CiudadEntry.TABLE_NAME,
                clausulaWhere, null);

        if (i != 0) {
            sdb.setTransactionSuccessful();
        }
        sdb.endTransaction();
        close(sdb);
        return i;
    }
}
