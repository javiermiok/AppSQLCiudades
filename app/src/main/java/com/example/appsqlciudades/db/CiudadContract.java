package com.example.appsqlciudades.db;

import android.provider.BaseColumns;

public class CiudadContract {

    public static abstract class CiudadEntry implements BaseColumns {
        public static final String TABLE_NAME = "CIUDADES";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "NOMBRE";
        public static final String COLUMN_PROVINCE = "PROVINCIA";
        public static final String COLUMN_INHAB = "HABITANTES";

    }

}
