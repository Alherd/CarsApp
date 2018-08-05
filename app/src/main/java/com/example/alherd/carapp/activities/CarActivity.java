package com.example.alherd.carapp.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.adapter.CarAdapter;
import com.example.alherd.carapp.database.DatabaseHelperMethods;
import com.example.alherd.carapp.model.Car;
import com.example.alherd.carapp.utils.ToastShowing;

import java.io.FileNotFoundException;
import java.io.InputStream;

public final class CarActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int REQUEST_PHOTO = 1;
    private static final int REQUEST_COUNTRY = 2;
    private EditText titleEditText;
    private EditText markEditText;
    private EditText costEditText;
    private EditText powerEditText;
    private EditText doorsNumberEditText;
    private EditText bodyTypeEditText;
    private EditText seatsNumberEditText;
    private EditText startReleaseEditText;
    private EditText endReleaseEditText;
    private DatabaseHelperMethods databaseHelperMethods;
    private ImageView mPhotoView;
    private Car car = new Car();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        titleEditText = findViewById(R.id.title_model_edit_text);
        markEditText = findViewById(R.id.mark_model_edit_text);
        costEditText = findViewById(R.id.cost_model_edit_text);
        powerEditText = findViewById(R.id.power_model_edit_text);
        doorsNumberEditText = findViewById(R.id.doors_number_model_edit_text);
        bodyTypeEditText = findViewById(R.id.body_type_model_edit_text);
        seatsNumberEditText = findViewById(R.id.seats_number_model_edit_text);
        startReleaseEditText = findViewById(R.id.start_release_model_edit_text);
        endReleaseEditText = findViewById(R.id.end_release_model_edit_text);

        Button saveButton = findViewById(R.id.save_button);
        databaseHelperMethods = new DatabaseHelperMethods(this);
        ImageButton mPhotoButton = findViewById(R.id.crime_camera);

        final Intent captureImage = new Intent();
        captureImage.setType("image/*");
        captureImage.setAction(Intent.ACTION_GET_CONTENT);

        mPhotoView = (ImageView) findViewById(R.id.crime_photo);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mark = markEditText.getText().toString();
                if (titleEditText.getText().toString().equals("")) {
                    ToastShowing.postToastMessage(CarActivity.this, "Please, input title of car");
                } else if (markEditText.getText().toString().equals("")) {
                    ToastShowing.postToastMessage(CarActivity.this, "Please, input mark of car");
                } else {
                    if (!databaseHelperMethods.isMarkExist(markEditText.getText().toString())) {
                        Intent intent = new Intent(CarActivity.this, CountryActivity.class);
                        startActivityForResult(intent, REQUEST_COUNTRY);
                    } else {
                        car.setTitle(titleEditText.getText().toString());
                        car.setMark(String.valueOf(databaseHelperMethods.getIdMarkFromName(mark)));
                        car.setCost(Integer.parseInt(costEditText.getText().toString()));
                        car.setPower(powerEditText.getText().toString());
                        car.setDoorsNumber(Integer.parseInt(doorsNumberEditText.getText().toString()));
                        car.setBodyType(bodyTypeEditText.getText().toString());
                        car.setSeatsNumber(Integer.parseInt(seatsNumberEditText.getText().toString()));
                        car.setStartRelease(startReleaseEditText.getText().toString());
                        car.setEndRelease(endReleaseEditText.getText().toString());

                        databaseHelperMethods.insertCarModel(car);
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }
        });

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermissionGranted()) {
                    startActivityPhotoPickerIntent();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_PHOTO) {
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap selectedImages = BitmapFactory.decodeStream(imageStream);
            car.setPhoto(selectedImage.toString());
            mPhotoView.setImageBitmap(selectedImages);
        }
        if (requestCode == REQUEST_COUNTRY) {
            int idCountry = data.getIntExtra("ID_COUNTRY", 0);
            databaseHelperMethods.insertMark(markEditText.getText().toString(), idCountry);
//            car.setMark(databaseHelperMethods.getIdMarkFromName(markEditText.getText().toString()));
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivityPhotoPickerIntent();
        }
    }

    private void startActivityPhotoPickerIntent() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_PHOTO);
    }

}
