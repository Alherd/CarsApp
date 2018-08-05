package com.example.alherd.carapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.database.DatabaseHelper;
import com.example.alherd.carapp.database.DatabaseHelperMethods;
import com.example.alherd.carapp.model.Car;
import com.example.alherd.carapp.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarHolder extends RecyclerView.ViewHolder {
    private TextView titleModel;
    private ImageView photoModel;
    private TextView markAndCountryModel;
    private TextView costModel;
    private TextView powerModel;
    private TextView doorsNumberModel;
    private TextView bodyTypeModel;
    private TextView seatsNumberModel;
    private TextView startReleaseModel;
    private TextView endReleaseModel;
    private DatabaseHelperMethods databaseHelperMethods;
    private final Handler handler = new Handler();
    private Car car = new Car();

    public CarHolder(View itemView) {
        super(itemView);
        titleModel = itemView.findViewById(R.id.title_model);
        photoModel = itemView.findViewById(R.id.photo_model);
        markAndCountryModel = itemView.findViewById(R.id.mark_country_model);
        costModel = itemView.findViewById(R.id.cost_model);
        powerModel = itemView.findViewById(R.id.power_model);
        doorsNumberModel = itemView.findViewById(R.id.doors_number_model);
        bodyTypeModel = itemView.findViewById(R.id.body_type_model);
        seatsNumberModel = itemView.findViewById(R.id.seats_number_model);
        startReleaseModel = itemView.findViewById(R.id.start_release_model);
        endReleaseModel = itemView.findViewById(R.id.end_release_model);
    }

    public void setTextInViewHolder(final int position, final Context context, final Cursor cursor) {
        cursor.moveToFirst();
        if (!cursor.move(position)) {
            return;
        }
        databaseHelperMethods = new DatabaseHelperMethods(context);
        String photoModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHOTO_CAR_MODEL));
        if (photoModelString != null) {
            if (StringUtils.foo(photoModelString, "jpg")) {
                InputStream is = getClass().getClassLoader().getResourceAsStream(photoModelString);
                Bitmap bm = BitmapFactory.decodeStream(is);
                photoModel.setImageBitmap(bm);
                //     setFibonacciNumberOnUiThread(photoModel, bm);

            } else {
                Picasso.with(context)
                        .load(photoModelString)
                        .fit()
                        .into(photoModel);
            }
        }
        String titleModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_CAR_MODEL));
        titleModel.setText(titleModelString);
        //      setFibonacciNumberOnUiThread(titleModel, titleModelString);
        String idMark = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_CAR_MODEL_MARK));
        markAndCountryModel.setText(databaseHelperMethods.getNameMarkFromId(idMark) +
                " (" + databaseHelperMethods.getManufacturerFromIdMark(idMark) + ")");
        //     setFibonacciNumberOnUiThread(markAndCountryModel, databaseHelperMethods.getNameMarkFromId(idMark) +
//                " (" + databaseHelperMethods.getManufacturerFromIdMark(idMark) + ")");
        String costModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST_CAR_MODEL));
        costModel.setText("$" + costModelString);
        //   setFibonacciNumberOnUiThread(costModel, "$" + costModelString);
        String powerModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POWER_CAR_MODEL));
        powerModel.setText(powerModelString);
        //   setFibonacciNumberOnUiThread(powerModel, powerModelString);
        String doorsNumberModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DOORS_NUMBER_CAR_MODEL));
        doorsNumberModel.setText(doorsNumberModelString);

        String bodyTypeModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BODY_TYPE_CAR_MODEL));
        bodyTypeModel.setText(bodyTypeModelString);

        String seatsNumberModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SEATS_NUMBER_CAR_MODEL));
        seatsNumberModel.setText(seatsNumberModelString);

        String startReleaseModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RELEASE_START_CAR_MODEL));
        startReleaseModel.setText(startReleaseModelString);

        String endReleaseModelString = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RELEASE_END_CAR_MODEL));
        endReleaseModel.setText(endReleaseModelString);
    }

    private void setFibonacciNumberOnUiThread(final TextView textView, final String CONCLUSION_STRING) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(CONCLUSION_STRING);
            }
        });
    }

    private void setFibonacciNumberOnUiThread(final ImageView imageView, final Bitmap bitmap) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}