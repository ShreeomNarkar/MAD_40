package com.example.eduassess;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_schedule_exam extends AppCompatActivity {

    private EditText etExamName, etQuestion, etOpt1, etOpt2, etOpt3, etOpt4;
    private Button btnAddMore, btnFinalSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_exam);

        // Bindings
        etExamName = findViewById(R.id.etExamName);
        etQuestion = findViewById(R.id.etQuestion);
        etOpt1 = findViewById(R.id.etOpt1);
        etOpt2 = findViewById(R.id.etOpt2);
        etOpt3 = findViewById(R.id.etOpt3);
        etOpt4 = findViewById(R.id.etOpt4);
        btnAddMore = findViewById(R.id.btnAddMore);
        btnFinalSchedule = findViewById(R.id.btnFinalSchedule);

        btnAddMore.setOnClickListener(v -> {
            // Validate if question is empty
            if (etQuestion.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a question first", Toast.LENGTH_SHORT).show();
                return;
            }

            // CLEAR only the question fields
            etQuestion.setText("");
            etOpt1.setText("");
            etOpt2.setText("");
            etOpt3.setText("");
            etOpt4.setText("");

            Toast.makeText(this, "Question saved locally", Toast.LENGTH_SHORT).show();
            etQuestion.requestFocus(); // Keep focus for next entry
        });

        btnFinalSchedule.setOnClickListener(v -> {
            String name = etExamName.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(this, "Exam Name is required", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Exam: " + name + " has been Published!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}