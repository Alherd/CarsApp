package com.example.alherd.carapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alherd.carapp.adapter.CarAdapter;
import com.example.alherd.carapp.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_cars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        carAdapter = new CarAdapter(this, getAllItems());
        recyclerView.setAdapter(carAdapter);
        carAdapter.notifyDataSetChanged();
    }

    private Cursor getAllItems() {
        return sqLiteDatabase.query(
                DatabaseHelper.TABLE_CAR_MODELS,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
