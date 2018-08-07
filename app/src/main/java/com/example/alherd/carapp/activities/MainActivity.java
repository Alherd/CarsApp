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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.adapter.CarAdapter;
import com.example.alherd.carapp.adapter.RecyclerTouchListener;
import com.example.alherd.carapp.database.DatabaseHelper;
import com.example.alherd.carapp.database.DatabaseHelperMethods;
import com.example.alherd.carapp.model.Car;

public final class MainActivity extends AppCompatActivity {
    private SQLiteDatabase sqLiteDatabase;
    private CarAdapter carAdapter;
    private Cursor cursor;
    private RecyclerView recyclerView;
    private DatabaseHelperMethods databaseHelperMethods;
    private EditText userFilterCountry;
    private ImageView imageViewCountry;
    private EditText userFilterMark;
    private ImageView imageViewMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        userFilterCountry = findViewById(R.id.user_filter_country);
        imageViewCountry = findViewById(R.id.image_country);
        userFilterMark = findViewById(R.id.user_filer_mark);
        imageViewMark = findViewById(R.id.image_mark);
        recyclerView = findViewById(R.id.recycler_view_cars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        databaseHelperMethods = new DatabaseHelperMethods(this);
        cursor = databaseHelperMethods.getAllItems();
        carAdapter = new CarAdapter(this, cursor);
        recyclerView.setAdapter(carAdapter);
        carAdapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                Car car = databaseHelperMethods.createCarFromPosition(position, cursor);
                Intent intent = new Intent(MainActivity.this, CarActivity.class);
                intent.putExtra("Car", car);
                startActivityForResult(intent, 2);
            }
        }));

        imageViewCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor = databaseHelperMethods.getAllItemsSortByCountry(userFilterCountry.getText().toString());
                carAdapter = new CarAdapter(MainActivity.this,
                        cursor);
                recyclerView.setAdapter(carAdapter);
                carAdapter.notifyDataSetChanged();
            }
        });

        imageViewMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor = databaseHelperMethods.getAllItemsSortByMark(userFilterMark.getText().toString());
                carAdapter = new CarAdapter(MainActivity.this,
                        cursor);
                recyclerView.setAdapter(carAdapter);
                carAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_confirm_new_car:
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
        carAdapter = new CarAdapter(this, cursor);
        carAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(carAdapter);
        Toast.makeText(this, R.string.operation_successful, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
    }
}
