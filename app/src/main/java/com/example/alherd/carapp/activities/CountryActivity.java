package com.example.alherd.carapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alherd.carapp.R;
import com.example.alherd.carapp.database.DatabaseHelperMethods;
import com.example.alherd.carapp.utils.ToastShowing;

public class CountryActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private DatabaseHelperMethods databaseHelperMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        databaseHelperMethods = new DatabaseHelperMethods(CountryActivity.this);
        editText = findViewById(R.id.input_country_edit_text);
        button = findViewById(R.id.confirm_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameCountry = editText.getText().toString();
                if (!nameCountry.equals("")) {
                    databaseHelperMethods.insertCountry(nameCountry);
                    Intent intent = new Intent();
                    intent.putExtra("ID_COUNTRY", databaseHelperMethods.getIdCountryFromName(nameCountry));
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastShowing.postToastMessage(CountryActivity.this, "Please, input country");
                }
            }
        });
    }
}
