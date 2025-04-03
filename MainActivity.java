package com.devdroid.bmicalclator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.llMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Variable Initialization
        EditText edtWeight = findViewById(R.id.EdtWeight);
        EditText edtHeightFt = findViewById(R.id.edtHeightFt);
        EditText edtHeightIn = findViewById(R.id.edtHeightIn);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        TextView txtResult = findViewById(R.id.txtResult);
        LinearLayout llMain = findViewById(R.id.llMain);

        // Calculate BMI
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate Inputs
                if (edtWeight.getText().toString().isEmpty() ||
                        edtHeightFt.getText().toString().isEmpty() ||
                        edtHeightIn.getText().toString().isEmpty()) {
                    txtResult.setText("Please fill in all fields!");
                    return;
                }

                int wt = Integer.parseInt(edtWeight.getText().toString());
                int ft = Integer.parseInt(edtHeightFt.getText().toString());
                int inches = Integer.parseInt(edtHeightIn.getText().toString());

                int totalInches = ft * 12 + inches;
                double totalCm = totalInches * 2.53;
                double totalM = totalCm / 100;
                double bmi = wt / (totalM * totalM);

                // Display Result & Change Background Color
                if (bmi > 25) {
                    txtResult.setText("You're Overweight!");
                    llMain.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.ColorOw));
                } else if (bmi < 18) {
                    txtResult.setText("You're Underweight!");
                    llMain.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.ColorUw));
                } else {
                    txtResult.setText("You're Healthy!");
                    llMain.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.ColorH));
                }
            }
        });
    }
}
