package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView welcomeText = findViewById(R.id.welcomeText);
        EditText countryInput = findViewById(R.id.countryInput);
        Button backButton = findViewById(R.id.backButton);

        String name = getIntent().getStringExtra("name");
        welcomeText.setText("Welcome, " + name + "!");

        backButton.setOnClickListener(v -> {
            String country = countryInput.getText().toString().trim();

            if (country.isEmpty()) {
                countryInput.setError("Please enter your country");
                return;
            }

            Intent result = new Intent();
            result.putExtra("country", country);
            setResult(RESULT_OK, result);
            finish();
        });
    }
}
