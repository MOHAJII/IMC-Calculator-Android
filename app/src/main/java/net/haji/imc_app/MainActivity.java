package net.haji.imc_app;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button calculateImcBtn = findViewById(R.id.caluclateImcBtn);
        TextView imcResult = findViewById(R.id.resultImc);
        ImageView resultImg = findViewById(R.id.resultImg);
        TextView resultStatus = findViewById(R.id.status);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText weightInput = findViewById(R.id.weightInput);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText heightInput = findViewById(R.id.heightInput);

        calculateImcBtn.setOnClickListener(v -> {
            if (!weightInput.getText().toString().trim().isEmpty() && !heightInput.getText().toString().isEmpty()) {
                double weight = Double.parseDouble(String.valueOf(weightInput.getText()));
                double height = Double.parseDouble(String.valueOf(heightInput.getText()));

                double result = (weight / (height * height)) * 10000;
                imcResult.setText(Double.toString(result));
                if (result < 18.5) {
                    resultStatus.setText("Maigreur");
                    resultImg.setImageResource(R.drawable.maigre);
                } else if (result > 18.5 && result < 25) {
                    resultStatus.setText("Corpulence normale");
                    resultImg.setImageResource(R.drawable.normal);
                } else if (result > 25 && result < 30) {
                    resultStatus.setText("Surpoids");
                    resultImg.setImageResource(R.drawable.surpoids);
                } else if (result > 30 && result < 35) {
                    resultStatus.setText("Obésité modérée");
                    resultImg.setImageResource(R.drawable.obese);
                } else if (result > 35 && result < 40) {
                    resultStatus.setText("Obésité sévère");
                    resultImg.setImageResource(R.drawable.t_obese);
                } else if (result > 40) {
                    resultStatus.setText("Obésité morbide ou massive");
                    resultImg.setImageResource(R.drawable.t_obese);
                }
            }

        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}