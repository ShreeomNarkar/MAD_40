package com.example.exp_7;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ToggleButton tgbtn;
    ConstraintLayout cnstlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tgbtn=findViewById(R.id.toggleButton);
        cnstlayout=findViewById(R.id.main);

        tgbtn.setOnCheckedChangeListener((btn, isChecked) -> {
            cnstlayout.setBackgroundColor(isChecked ? Color.BLACK : Color.WHITE);

            tgbtn.setText(isChecked ? "White" : "Dark");
        });

        ImageButton imgbtn = findViewById(R.id.imageButton);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        imgbtn.setOnClickListener(v -> {
            progressBar.setProgress(100);
            Toast.makeText(MainActivity.this, "Download Complete", Toast.LENGTH_SHORT).show();
        });


        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            if (fromUser) {
                Toast.makeText(MainActivity.this,
                        "You rated this image: " + rating + " stars",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }




}