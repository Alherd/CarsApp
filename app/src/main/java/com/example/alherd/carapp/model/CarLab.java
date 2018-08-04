package com.example.alherd.carapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

public class CarLab {
    private static CarLab sCrimeLab;
    private Context mContext;

    public static CarLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CarLab(context);
        }
        return sCrimeLab;
    }

    private CarLab(Context context) {
        mContext = context.getApplicationContext();
    }

    public File getPhotoFile(Car car) {
        //Определение местонахождения файла фотографии
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, car.getPhotoFilename());
    }


}
