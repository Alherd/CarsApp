package com.example.alherd.carapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.database.DatabaseHelperMethods;
import com.example.alherd.carapp.utils.BitmapUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public final class CarActivity extends AppCompatActivity {
    private static final int REQUEST_PHOTO = 1;
    private EditText editText;
    private Button saveButton;
    private ImageButton mPhotoButton;
    private DatabaseHelperMethods databaseHelperMethods;
    private ImageView mPhotoView;
    private Bitmap selectedImages;

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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText.getText().toString();

                databaseHelperMethods.insertCarModel(title, BitmapUtils.convertToBase64(selectedImages));
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
            Uri selectedImage = data.getData();
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
}
