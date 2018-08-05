package com.example.alherd.carapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "carBase.db";

    public static final String TABLE_MANUFACTURERS = "manufacturers";
    public static final String COLUMN_ID_COUNTRY_MANUFACTURER = "_id_manufacturer";
    public static final String COLUMN_NAME_COUNTRY_MANUFACTURER = "name_country_manufacturer";

    public static final String TABLE_CAR_MARKS = "car_marks";
    public static final String COLUMN_ID_CAR_MARK = "_id_car_mark";
    public static final String COLUMN_NAME_CAR_MARK = "name_car_mark";
    public static final String COLUMN_ID_COUNTRY_CAR_MARK = "_id_country_car_mark";

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
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_COUNTRY_MANUFACTURER + " TEXT);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CAR_MARKS
                + " (" + COLUMN_ID_CAR_MARK
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_CAR_MARK + " TEXT, "
                + COLUMN_ID_COUNTRY_CAR_MARK + " INTEGER);");

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
                COLUMN_NAME_CAR_MARK + "," + COLUMN_ID_COUNTRY_CAR_MARK + ") VALUES ('1', 'Ford', '1')," +
                "('2', 'Toyota', '2'),('3', 'Nissan', '2'),('4', 'BMW', '3'),('5', 'Mercedes-Benz', '3')," +
                "('6', 'Volkswagen', '3');");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_CAR_MODELS + " (" + COLUMN_NAME_CAR_MODEL + ", " +
                COLUMN_ID_CAR_MODEL_MARK + "," + COLUMN_COST_CAR_MODEL + "," + COLUMN_POWER_CAR_MODEL + "," +
                COLUMN_DOORS_NUMBER_CAR_MODEL + "," + COLUMN_BODY_TYPE_CAR_MODEL + "," + COLUMN_SEATS_NUMBER_CAR_MODEL + "," +
                COLUMN_RELEASE_START_CAR_MODEL + "," + COLUMN_RELEASE_END_CAR_MODEL + "," +
                COLUMN_PHOTO_CAR_MODEL + ") VALUES " +
                "('Bronco V', '1', '23000', '526 Hp', '2', 'Coupe','2','2017', '-', 'res/drawable/bronvov.jpg')," +
                "('Edge II', '1', '28000', '280 Hp', '5', 'SUV','5','2015', '-', 'res/drawable/edge_ford.jpg')," +
                "('Avalon IV', '2', '40000', '268 Hp', '4', 'Sedan','5','2017', '2018', 'res/drawable/avalon_toyota.jpg')," +
                "('Duet', '2', '17000', '110 Hp', '5', 'Hatchback','5','2000', '-', 'res/drawable/duet_toyota.jpg')," +
                "('Matrix II', '2', '20000', '160 Hp', '5', 'Hatchback','5','2008', '-', 'res/drawable/matrix_toyota.jpg')," +
                "('Almera Classic', '3', '18000', '107 Hp', '4', 'Sedan','5','2006', '2013', 'res/drawable/almera_nissan.jpg')," +
                "('Maxima VIII', '3', '21000', '300 Hp', '4', 'Sedan','5','2015', '-', 'res/drawable/maxima_nissan.jpg')," +
                "('7er', '4', '26000', '450 Hp', '4', 'Sedan','5','2015', '-', 'res/drawable/er_bmw.jpg')," +
                "('Z3', '4', '8000', '231 Hp', '2', 'Cabriolet','2','2000', '2003', 'res/drawable/z_bmw.jpg')," +
                "('GLK', '5', '24000', '306 Hp', '5', 'SUV','5','2012', '-', 'res/drawable/glk_mers.jpg')," +
                "('Viano', '5', '15000', '224 Hp', '5', 'Minivan','6','2010', '-', 'res/drawable/viano_mers.jpg')," +
                "('Passat Alltrack', '6', '22000', '220 Hp', '5', 'SUV','5','2015', '-', 'res/drawable/passat_volks.jpg');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
