package com.udacity.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.udacity.habittracker.data.HabitDbHelper;

import java.util.Calendar;

import static com.udacity.habittracker.data.HabitContract.HabitEntry;
import static java.text.DateFormat.getDateInstance;


public class NewHabitActivity extends AppCompatActivity {

    private final static String TAG = NewHabitActivity.class.getSimpleName();
    // UI
    private EditText mNameEdit;
    private EditText mDetailsEdit;
    private String mDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        //
        mNameEdit    = (EditText) findViewById(R.id.editNameView);
        mDetailsEdit = (EditText) findViewById(R.id.editDetailsView);
        TextView dateText = (TextView) findViewById(R.id.dateView);
        mDate = startedToday();
        dateText.setText(mDate);
    }


    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_habit, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_save:
                dataInsertionNewHabit();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Data Insertion
     * There is a single insert method that adds at least two values of two different datatypes (e.g. INTEGER, STRING) into the database using a ContentValues object and the insert() method.
     * @return
     */
    public long dataInsertionNewHabit() {
        String name    = mNameEdit.getText().toString().trim();
        String details = mDetailsEdit.getText().toString().trim();

        HabitDbHelper dbHelper = new HabitDbHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME, name);
        values.put(HabitEntry.COLUMN_DETAILS, details);
        values.put(HabitEntry.COLUMN_START_DATE, mDate);

        return database.insert(HabitEntry.TABLE_NAME, null, values);
    }


    private String startedToday() {
        Calendar calendar = Calendar.getInstance();
        String date = getDateInstance().format(calendar.getTime());
        Log.d(TAG, "date: " + date);
        return date;
    }



}
