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
import android.view.View;
import android.widget.Toast;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.adapter.CarAdapter;
import com.example.alherd.carapp.adapter.RecyclerTouchListener;
import com.example.alherd.carapp.database.DatabaseHelper;
import com.example.alherd.carapp.database.DatabaseHelperMethods;
import com.example.alherd.carapp.model.Car;
import com.example.alherd.carapp.utils.ToastShowing;

public final class MainActivity extends AppCompatActivity {
    private SQLiteDatabase sqLiteDatabase;
    private CarAdapter carAdapter;
    private Cursor cursor;
    private RecyclerView recyclerView;
    private DatabaseHelperMethods databaseHelperMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler_view_cars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        databaseHelperMethods = new DatabaseHelperMethods(this);
        cursor = getAllItems();
        carAdapter = new CarAdapter(this, cursor);
        //  cursor.close();
        recyclerView.setAdapter(carAdapter);
        carAdapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                Car car = databaseHelperMethods.createCarFromPosition(position);
                Intent intent = new Intent(MainActivity.this, CarActivity.class);
                intent.putExtra("Car", car);
                startActivityForResult(intent, 2);
            }
        }));

    }

    private Cursor getAllItems() {
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_CAR_MODELS,
                null, null, null, null, null, DatabaseHelper.COLUMN_NAME_CAR_MODEL);
        return cursor;
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
        //   getAllItems().close();
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
