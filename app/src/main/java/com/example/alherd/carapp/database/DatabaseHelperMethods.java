package com.example.alherd.carapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alherd.carapp.activities.MainActivity;
import com.example.alherd.carapp.model.Car;
import com.example.alherd.carapp.utils.ToastShowing;

import java.util.UUID;

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

    public void insertCarModel(Car car) {
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
        if (car.getPhoto() != null) {
            contentValues.put(COLUMN_PHOTO_CAR_MODEL, car.getPhoto());
        } else {
            contentValues.put(COLUMN_PHOTO_CAR_MODEL, "");
        }

        db.insert(TABLE_CAR_MODELS, null, contentValues);
    }

    public void deleteCarModel(Car car) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_CAR_MODELS, COLUMN_ID_CAR_MODEL + " = '" + car.getId() + "'", null);
    }

    public boolean isMarkExist(String mark) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_CAR_MARKS + " where " + COLUMN_NAME_CAR_MARK + " = '" + mark + "';", null);
        int a = cursor.getCount();
        cursor.close();
        return a != 0;
    }

    public void insertMark(String mark, int idCountry) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_NAME_CAR_MARK, mark);
        contentValues.put(DatabaseHelper.COLUMN_ID_COUNTRY_CAR_MARK, idCountry);

        db.insert(TABLE_CAR_MARKS, null, contentValues);
    }

    public int getIdMarkFromName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor b = db.rawQuery("select * from " + TABLE_CAR_MARKS +
                " where " + COLUMN_NAME_CAR_MARK + " = '" + name + "';", null);
        b.moveToFirst();
        String nameP = b.getString(b.getColumnIndex(COLUMN_ID_CAR_MARK));
        b.close();
        return Integer.parseInt(nameP);
    }

    public void insertCountry(String nameCountry) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_NAME_COUNTRY_MANUFACTURER, nameCountry);
        db.insert(TABLE_MANUFACTURERS, null, contentValues);
    }

    public int getIdCountryFromName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor b = db.rawQuery("select * from " + TABLE_MANUFACTURERS +
                " where " + COLUMN_NAME_COUNTRY_MANUFACTURER + " = '" + name + "';", null);
        b.moveToFirst();
        String nameP = b.getString(b.getColumnIndex(COLUMN_ID_COUNTRY_MANUFACTURER));
        b.close();
        return Integer.parseInt(nameP);
    }

    public Car createCarFromPosition(int position) {
        Car car = new Car();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_CAR_MODELS,
                null, null, null, null, null, DatabaseHelper.COLUMN_NAME_CAR_MODEL);
        cursor.moveToFirst();
        if (!cursor.move(position)) {
            return car;
        }

        String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_CAR_MODEL));
        String titleModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_CAR_MODEL));
        String idMark = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_CAR_MODEL_MARK));
        int costModelString = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_CAR_MODEL)));
        String powerModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POWER_CAR_MODEL));
        String doorsNumberModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DOORS_NUMBER_CAR_MODEL));
        String bodyTypeModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BODY_TYPE_CAR_MODEL));
        String seatsNumberModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SEATS_NUMBER_CAR_MODEL));
        String startReleaseModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RELEASE_START_CAR_MODEL));
        String endReleaseModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RELEASE_END_CAR_MODEL));
        String photoModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHOTO_CAR_MODEL));

        car.setmId(id);
        car.setTitle(titleModelString);
        car.setMark(idMark);
        car.setCost(costModelString);
        car.setPower(powerModelString);
        car.setDoorsNumber(doorsNumberModelString);
        car.setBodyType(bodyTypeModelString);
        car.setSeatsNumber(seatsNumberModelString);
        car.setStartRelease(startReleaseModelString);
        car.setEndRelease(endReleaseModelString);
        car.setPhoto(photoModelString);

        cursor.close();
        return car;
    }
}
