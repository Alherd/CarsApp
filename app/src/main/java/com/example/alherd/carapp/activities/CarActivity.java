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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.database.DatabaseHelperMethods;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CarActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private EditText editText;
    private Button saveButton;
    private ImageButton mPhotoButton;
    private DatabaseHelperMethods databaseHelperMethods;
    private File mPhotoFile;
    private ImageView mPhotoView;
    private Uri selectedImage;
    private Bitmap selectedImages;

    private static final int REQUEST_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        editText = findViewById(R.id.title_model_edit_text);
        saveButton = findViewById(R.id.save_button);
        databaseHelperMethods = new DatabaseHelperMethods(this);
        mPhotoButton = findViewById(R.id.crime_camera);

        final Intent captureImage = new Intent();
        captureImage.setType("image/*");
        captureImage.setAction(Intent.ACTION_GET_CONTENT);

        mPhotoView = (ImageView) findViewById(R.id.crime_photo);

        isStoragePermissionGranted();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText.getText().toString();

                databaseHelperMethods.insertCarModel(title, convertToBase64(selectedImages));
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, REQUEST_PHOTO);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_PHOTO) {
            selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            selectedImages = BitmapFactory.decodeStream(imageStream);
            mPhotoView.setImageBitmap(selectedImages);
        }
    }

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

    public String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        byte[] byteArray = os.toByteArray();
        return Base64.encodeToString(byteArray, 0);
    }
}
