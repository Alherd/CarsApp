package com.example.alherd.carapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.adapter.CarAdapter;
import com.example.alherd.carapp.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase sqLiteDatabase;
    private CarAdapter carAdapter;
    private Cursor cursor;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler_view_cars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        cursor = getAllItems();
        carAdapter = new CarAdapter(this, cursor);
        recyclerView.setAdapter(carAdapter);
        carAdapter.notifyDataSetChanged();
    }

    private Cursor getAllItems() {
        return sqLiteDatabase.query(DatabaseHelper.TABLE_CAR_MODELS,
                null, null, null, null, null, DatabaseHelper.COLUMN_NAME_CAR_MODEL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Intent intent = new Intent(this, CarActivity.class);
                startActivityForResult(intent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        carAdapter = new CarAdapter(this, getAllItems());
        recyclerView.setAdapter(carAdapter);
        carAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Insert car successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
    }
}
