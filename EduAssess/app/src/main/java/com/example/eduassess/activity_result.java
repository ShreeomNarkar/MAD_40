package com.example.eduassess;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class activity_result extends AppCompatActivity {

    private TextView tvFinalScore, tvStatus, tvFeedback;
    private Button btnBackToDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize UI Elements
        tvFinalScore = findViewById(R.id.tvFinalScore);
        tvStatus = findViewById(R.id.tvStatus);
        tvFeedback = findViewById(R.id.tvFeedback);
        btnBackToDash = findViewById(R.id.btnBackToDash);

        // Get Data from Intent
        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 20);

        // Set Score Text
        tvFinalScore.setText(score + " / " + total);

        // Logic for Passing Status
        if (score >= (total / 2)) {
            tvStatus.setText("PASSED");
            tvStatus.setTextColor(Color.parseColor("#4CAF50")); // Green
            tvFeedback.setText("Excellent performance, keep it up!");
        } else {
            tvStatus.setText("FAILED");
            tvStatus.setTextColor(Color.parseColor("#F44336")); // Red
            tvFeedback.setText("Don't give up. Try harder next time!");
        }

        // Return to Dashboard
        btnBackToDash.setOnClickListener(v -> {
            Intent intent = new Intent(activity_result.this, activity_student_dashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Prevents going back to Result screen
            startActivity(intent);
            finish();
        });
    }
}