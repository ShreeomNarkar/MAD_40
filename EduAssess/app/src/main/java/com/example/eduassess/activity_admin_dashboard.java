package com.example.eduassess;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class activity_admin_dashboard extends AppCompatActivity {

    // Renamed variables to match your 4 specific options
    private CardView cardSchedule, cardEditStudents, cardViewResults, cardLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // 1. Initialize Cards with the IDs from your Premium XML
        cardSchedule = findViewById(R.id.cardSchedule);
        cardEditStudents = findViewById(R.id.cardEditStudents);
        cardViewResults = findViewById(R.id.cardViewResults);
        cardLogout = findViewById(R.id.cardLogout);

        // 2. Schedule Exam Logic
        cardSchedule.setOnClickListener(v -> {
            // Transitions to the combined Schedule + Add Question screen
            startActivity(new Intent(activity_admin_dashboard.this, activity_schedule_exam.class));
        });

        // 3. Edit Students Logic
        cardEditStudents.setOnClickListener(v -> {
            // Placeholder for Student Management UI
            startActivity(new Intent(activity_admin_dashboard.this, activity_edit_students.class));
        });

        // 4. View Results Logic
        cardViewResults.setOnClickListener(v -> {
            // Transitions to the Student Results list
            startActivity(new Intent(activity_admin_dashboard.this, activity_result.class));
        });

        // 5. Logout Logic
        cardLogout.setOnClickListener(v -> {
            // Return to the Login Screen (MainActivity)
            Intent intent = new Intent(activity_admin_dashboard.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        });
    }
}