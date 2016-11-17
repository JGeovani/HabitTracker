package com.udacity.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by geovani on 16/11/16.
 */

/**
 * Table Creation
 * There exists a subclass of SQLiteOpenHelper that overrides onCreate() and onUpgrade().
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    private final static String TAG = HabitDbHelper.class.getSimpleName();
    private final static String DATABASE_NAME = "habits.db";
    private static final int DATABASE_VERSION = 1;


    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(HabitContract.HabitEntry.CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



}
