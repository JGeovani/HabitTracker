package com.udacity.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by geovani on 16/11/16.
 */


/**
 * Table Definition
 *
 * There exists a contract class that defines name of table and constants.
 * Inside the contract class, there is an inner class for each table created.
 */
public final class HabitContract {


    private HabitContract() {
    }


    public static final class HabitEntry implements BaseColumns
    {
        public final static String TABLE_NAME             = "habits";
        public final static String _ID                    = BaseColumns._ID;
        public final static String COLUMN_NAME            = "name";
        public final static String COLUMN_DETAILS         = "details";
        public final static String COLUMN_START_DATE      = "start_date";

        public final static String CREATE_TABLE =
                    "CREATE TABLE " + TABLE_NAME + " (" +
                            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            COLUMN_NAME + " TEXT NOT NULL, " +
                            COLUMN_DETAILS + " TEXT NOT NULL, " +
                            COLUMN_START_DATE + " DATETIME NOT NULL DEFAULT CURRENT_DATE)";
    }



}
