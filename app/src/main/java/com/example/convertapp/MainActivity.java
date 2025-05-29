package com.example.convertapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextKilograms;
    private TextView textViewResult;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextKilograms = findViewById(R.id.editTextKilograms);
        textViewResult = findViewById(R.id.textViewResult);
        buttonConvert = findViewById(R.id.buttonConvert);

        // Set click listener for the convert button
        buttonConvert.setOnClickListener(v -> convertKilogramsToPounds());

        // Handle window insets for EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void convertKilogramsToPounds() {
        String input = editTextKilograms.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập giá trị", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double kilograms = Double.parseDouble(input);
            if (kilograms < 0) {
                Toast.makeText(this, "Khối lượng không thể âm", Toast.LENGTH_SHORT).show();
                return;
            }
            double pounds = kilograms * 2.20462;
            // Display full precision without rounding
            String result = kilograms + " kg = " + pounds + " lbs";
            textViewResult.setText(result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Đầu vào không hợp lệ. Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}