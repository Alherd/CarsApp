package com.example.alherd.carapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "clinicBase.db";

    public static final String TABLE_MANUFACTURERS = "manufacturers";
    public static final String COLUMN_ID_COUNTRY_MANUFACTURER = "_id_manufacturer";
    public static final String COLUMN_NAME_COUNTRY_MANUFACTURER = "name_country_manufacturer";

    public static final String TABLE_CAR_MARKS = "car_marks";
    public static final String COLUMN_ID_CAR_MARK = "_id_car_mark";
    public static final String COLUMN_NAME_CAR_MARK = "name_car_mark";
    public static final String COLUMN_ID_COUNTRY_CAR_MARK = "_id_country_car_mark";
    public static final String COLUMN_NAME_FOUNDER_CAR_MARK = "name_founder_car_mark";
    public static final String COLUMN_FOUNDATION_YEAR_CAR_MARK = "foundation_year_car_mark";

    public static final String TABLE_CAR_MODELS = "car_models";
    public static final String COLUMN_ID_CAR_MODEL = "_id_car_model";
    public static final String COLUMN_NAME_CAR_MODEL = "name_car_model";
    public static final String COLUMN_ID_CAR_MODEL_MARK = "_id_car_model_mark";
    public static final String COLUMN_COST_CAR_MODEL = "cost_car_model";
    public static final String COLUMN_POWER_CAR_MODEL = "power_car_model";
    public static final String COLUMN_DOORS_NUMBER_CAR_MODEL = "doors_number_car_model";
    public static final String COLUMN_BODY_TYPE_CAR_MODEL = "body_type_car_model";
    public static final String COLUMN_SEATS_NUMBER_CAR_MODEL = "seats_number_car_model";
    public static final String COLUMN_RELEASE_START_CAR_MODEL = "release_start_car_model";
    public static final String COLUMN_RELEASE_END_CAR_MODEL = "release_end_car_model";
    public static final String COLUMN_PHOTO_CAR_MODEL = "photo_car_model";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
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
                + COLUMN_FOUNDATION_YEAR_CAR_MARK + " TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CAR_MODELS
                + " (" + COLUMN_ID_CAR_MODEL
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_CAR_MODEL + " TEXT, "
                + COLUMN_ID_CAR_MODEL_MARK + " TEXT, "
                + COLUMN_COST_CAR_MODEL + " INTEGER, "
                + COLUMN_POWER_CAR_MODEL + " TEXT, "
                + COLUMN_DOORS_NUMBER_CAR_MODEL + " INTEGER, "
                + COLUMN_BODY_TYPE_CAR_MODEL + " TEXT, "
                + COLUMN_SEATS_NUMBER_CAR_MODEL + " INTEGER, "
                + COLUMN_RELEASE_START_CAR_MODEL + " TEXT, "
                + COLUMN_RELEASE_END_CAR_MODEL + " TEXT, "
                + COLUMN_PHOTO_CAR_MODEL + " TEXT);");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_MANUFACTURERS + " (" + COLUMN_ID_COUNTRY_MANUFACTURER + ", " +
                COLUMN_NAME_COUNTRY_MANUFACTURER + ") VALUES ('1', 'USA'),('2','Japan'), ('3', 'Germany');");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CAR_MARKS + " (" + COLUMN_ID_CAR_MARK + ", " +
                COLUMN_NAME_CAR_MARK + "," + COLUMN_ID_COUNTRY_CAR_MARK + "," + COLUMN_NAME_FOUNDER_CAR_MARK + "," +
                COLUMN_FOUNDATION_YEAR_CAR_MARK + ") VALUES ('1', 'Ford', '1', 'Henry Ford', '1903');");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CAR_MODELS + " (" + COLUMN_NAME_CAR_MODEL + ", " +
                COLUMN_ID_CAR_MODEL_MARK + "," + COLUMN_COST_CAR_MODEL + "," + COLUMN_POWER_CAR_MODEL + "," +
                COLUMN_DOORS_NUMBER_CAR_MODEL + "," + COLUMN_BODY_TYPE_CAR_MODEL + "," + COLUMN_SEATS_NUMBER_CAR_MODEL + "," +
                COLUMN_RELEASE_START_CAR_MODEL + "," + COLUMN_RELEASE_END_CAR_MODEL + "," +
                COLUMN_PHOTO_CAR_MODEL + ") VALUES " +
                "('Bronco V', '1', '23000', '526 Hp', '2', 'coupe','2','2017', '-', 'photo')," +
                "('B', '1', '23000', '526 Hp', '2', 'coupe','2','2017', '-', 'photo') ;");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
