package com.example.alherd.carapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.database.DatabaseHelperMethods;

public class CarActivity extends AppCompatActivity {

    private EditText editText;
    private Button saveButton;
    private DatabaseHelperMethods databaseHelperMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        editText = findViewById(R.id.title_model_edit_text);
        saveButton = findViewById(R.id.save_button);
        databaseHelperMethods = new DatabaseHelperMethods(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText.getText().toString();

                databaseHelperMethods.insertCarModel(title);
                Intent intent = new Intent();

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
