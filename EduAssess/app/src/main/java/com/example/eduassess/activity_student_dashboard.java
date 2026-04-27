package com.example.eduassess;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_student_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        ImageView btnLogout = findViewById(R.id.btnLogout);
        Button btnStart = findViewById(R.id.btnStartExam);

        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(activity_student_dashboard.this, MainActivity.class));
            finish();
        });

        btnStart.setOnClickListener(v -> {
            // Confirmation before starting the timer
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Start Exam?")
                    .setMessage("Once you start, the timer will begin. Do not close the app.")
                    .setPositiveButton("Start Now", (dialog, which) -> {
                        startActivity(new Intent(activity_student_dashboard.this, activity_exam.class));
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}