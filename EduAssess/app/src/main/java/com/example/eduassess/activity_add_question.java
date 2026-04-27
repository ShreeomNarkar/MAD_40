package com.example.eduassess;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_add_question extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        findViewById(R.id.btnSaveQuestion).setOnClickListener(v -> {
            Toast.makeText(this, "Question Added to Bank", Toast.LENGTH_SHORT).show();
            // Clear fields for next question
        });
    }
}