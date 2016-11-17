package com.udacity.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.udacity.habittracker.data.HabitContract;
import com.udacity.habittracker.data.HabitDbHelper;


public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        mDbHelper = new HabitDbHelper(this);
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        //
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        setupFabOnClick(fab);
    }


    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseOnUI();
    }


    private void displayDatabaseOnUI() {
        Cursor cursor = dataReading();
        TextView displayView = (TextView) findViewById(R.id.mainRowText);
        try {
            Snackbar.make(displayView, "The habit table contains " + cursor.getCount() + " habits.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            int idColumnIndex      = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int nameColumnIndex    = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_NAME);
            int detailsColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_DETAILS);
            int dateColumnIndex    = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_START_DATE);

            while (cursor.moveToNext()) {
                int currentID         = cursor.getInt(idColumnIndex);
                String currentName    = cursor.getString(nameColumnIndex);
                String currentDetails = cursor.getString(detailsColumnIndex);
                String currentDate    = cursor.getString(dateColumnIndex);
                displayView.append(("\n" + currentID + "ª) " + currentName + ": " + currentDetails + ". Started on: " + currentDate));
            }
        } finally {
            cursor.close();
        }
    }


    /**
     * Data Reading
     * There is a single read method that returns a Cursor object. It should get the data repository
     * in read and use the query() method to retrieve at least one column of data.
     */
    private Cursor dataReading() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_NAME,
                HabitContract.HabitEntry.COLUMN_DETAILS,
                HabitContract.HabitEntry.COLUMN_START_DATE,
        };
        return db.query(HabitContract.HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
    }


    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }


    private void setupFabOnClick(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewHabitActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_dummy) {
            insertDummy();
            displayDatabaseOnUI();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void insertDummy() {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_NAME, "Correr");
        values.put(HabitContract.HabitEntry.COLUMN_DETAILS, "Corro todos os dias durante 30 min");
        database.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
    }



}
