package com.example.alherd.carapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "clinicBase.db";

    private static final String TABLE_MANUFACTURERS = "manufacturers";
    private static final String COLUMN_ID_COUNTRY_MANUFACTURER = "_id_manufacturer";
    private static final String COLUMN_NAME_COUNTRY_MANUFACTURER = "name_country_manufacturer";

    private static final String TABLE_CAR_MARKS = "car_marks";
    private static final String COLUMN_ID_CAR_MARK = "_id_car_mark";
    private static final String COLUMN_NAME_CAR_MARK = "name_car_mark";
    private static final String COLUMN_ID_COUNTRY_CAR_MARK = "_id_country_car_mark";
    private static final String COLUMN_NAME_FOUNDER_CAR_MARK = "name_founder_car_mark";
    private static final String COLUMN_FOUNDATION_YEAR_CAR_MARK = "foundation_year_car_mark";

    private static final String TABLE_CAR_MODELS = "car_models";
    private static final String COLUMN_ID_CAR_MODEL = "_id_car_model";
    private static final String COLUMN_NAME_CAR_MODEL = "name_car_model";
    private static final String COLUMN_ID_CAR_MODEL_MARK = "_id_car_model_mark";
    private static final String COLUMN_COST_CAR_MODEL = "cost_car_model";
    private static final String COLUMN_MODIFICATION_ENGINE_CAR_MODEL = "modification_engine_car_model";
    private static final String COLUMN_DOORS_NUMBER_CAR_MODEL = "doors_number_car_model";
    private static final String COLUMN_BODY_TYPE_CAR_MODEL = "body_type_car_model";
    private static final String COLUMN_SEATS_NUMBER_CAR_MODEL = "seats_number_car_model";
    private static final String COLUMN_RELEASE_START_CAR_MODEL = "release_start_car_model";
    private static final String COLUMN_RELEASE_END_CAR_MODEL = "release_end_car_model";
    private static final String COLUMN_PHOTO_CAR_MODEL = "photo_car_model";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MANUFACTURERS
                + " (" + COLUMN_ID_COUNTRY_MANUFACTURER
                + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME_COUNTRY_MANUFACTURER + " TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CAR_MARKS
                + " (" + COLUMN_ID_CAR_MARK
                + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME_CAR_MARK + " TEXT, "
                + COLUMN_ID_COUNTRY_CAR_MARK + " TEXT, "
                + COLUMN_NAME_FOUNDER_CAR_MARK + " TEXT, "
                + COLUMN_FOUNDATION_YEAR_CAR_MARK + " TEXT, "
                + COLUMN_NAME_COUNTRY_MANUFACTURER + " TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CAR_MODELS
                + " (" + COLUMN_ID_CAR_MODEL
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_CAR_MODEL + " TEXT, "
                + COLUMN_ID_CAR_MODEL_MARK + " TEXT, "
                + COLUMN_COST_CAR_MODEL + " TEXT, "
                + COLUMN_MODIFICATION_ENGINE_CAR_MODEL + " TEXT, "
                + COLUMN_DOORS_NUMBER_CAR_MODEL + " TEXT, "
                + COLUMN_BODY_TYPE_CAR_MODEL + " TEXT, "
                + COLUMN_SEATS_NUMBER_CAR_MODEL + " TEXT, "
                + COLUMN_RELEASE_START_CAR_MODEL + " TEXT, "
                + COLUMN_RELEASE_END_CAR_MODEL + " TEXT, "
                + COLUMN_PHOTO_CAR_MODEL + " TEXT, "
                + COLUMN_NAME_COUNTRY_MANUFACTURER + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
