
package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private TextView countryText;

    private final ActivityResultLauncher<Intent> countryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            String country = result.getData()
                                    .getStringExtra("country");

                            if (country != null && !country.trim().isEmpty()) {
                                countryText.setText("Country: " + country);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        countryText = findViewById(R.id.countryText);

        Button nextButton = findViewById(R.id.nextButton);
        Button callButton = findViewById(R.id.callButton);

        nextButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            if (name.isEmpty()) {
                nameInput.setError("Please enter your name");
                return;
            }

            Intent intent = new Intent(
                    MainActivity.this, SecondActivity.class);
            intent.putExtra("name", name);

            countryLauncher.launch(intent);
        });

        callButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:03001234567")
            );
            startActivity(intent);
        });
    }
}
