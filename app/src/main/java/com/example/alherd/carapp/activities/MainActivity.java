package com.example.alherd.carapp.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.alherd.carapp.R;
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
        Cursor cursor = getAllItems();
        carAdapter = new CarAdapter(this, cursor);
        recyclerView.setAdapter(carAdapter);
        carAdapter.notifyDataSetChanged();
    }

    private Cursor getAllItems() {
        return sqLiteDatabase.rawQuery("select * from " + DatabaseHelper.TABLE_CAR_MODELS, null);
    }
}
