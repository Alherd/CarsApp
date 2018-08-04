package com.example.alherd.carapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.adapter.CarAdapter;
import com.example.alherd.carapp.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

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
        //   isStoragePermissionGranted();
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

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("ddddd", "Permission is granted");
                return true;
            } else {

                Log.v("ddddd", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("ddddd", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("ddddd", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }
}
