package com.example.alherd.carapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.alherd.carapp.model.Car;

public final class DatabaseHelperMethods extends DatabaseHelper {
    public DatabaseHelperMethods(Context context) {
        super(context);
    }

    public String getNameMarkFromId(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor b = db.rawQuery("select " + COLUMN_NAME_CAR_MARK + " from " + TABLE_CAR_MARKS + " where " + COLUMN_ID_CAR_MARK +
                " = '" + id + "' ;", null);
        b.moveToFirst();
        String nameP = b.getString(b.getColumnIndex(COLUMN_NAME_CAR_MARK));
        b.close();
        return nameP;
    }

    public String getManufacturerFromIdMark(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor b = db.rawQuery("select * from " + TABLE_CAR_MARKS + ", " + TABLE_MANUFACTURERS +
                " where " + COLUMN_ID_CAR_MARK + " = '" + id + "' AND " + COLUMN_ID_COUNTRY_CAR_MARK +
                " = " + COLUMN_ID_COUNTRY_MANUFACTURER + ";", null);
        b.moveToFirst();
        String nameP = b.getString(b.getColumnIndex(COLUMN_NAME_COUNTRY_MANUFACTURER));
        b.close();
        return nameP;
    }

    public void insertCarModel(Car car, Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_CAR_MODEL, car.getTitle());
        contentValues.put(COLUMN_ID_CAR_MODEL_MARK, car.getMark());
        contentValues.put(COLUMN_COST_CAR_MODEL, car.getCost());
        contentValues.put(COLUMN_POWER_CAR_MODEL, car.getPower());
        contentValues.put(COLUMN_DOORS_NUMBER_CAR_MODEL, car.getDoorsNumber());
        contentValues.put(COLUMN_BODY_TYPE_CAR_MODEL, car.getBodyType());
        contentValues.put(COLUMN_SEATS_NUMBER_CAR_MODEL, car.getSeatsNumber());
        contentValues.put(COLUMN_RELEASE_START_CAR_MODEL, car.getStartRelease());
        contentValues.put(COLUMN_RELEASE_END_CAR_MODEL, car.getEndRelease());
        contentValues.put(COLUMN_PHOTO_CAR_MODEL, car.getPhoto());
        db.insert(TABLE_CAR_MODELS, null, contentValues);
        Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show();
    }
}
