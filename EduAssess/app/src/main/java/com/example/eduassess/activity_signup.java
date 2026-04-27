package com.example.eduassess;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activity_signup extends AppCompatActivity {

    private EditText etName, etRoll, etEmail, etPassword;
    private Button btnSignup;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.etSignupName);
        etRoll = findViewById(R.id.etSignupRoll);
        etEmail = findViewById(R.id.etSignupEmail);
        etPassword = findViewById(R.id.etSignupPassword);
        btnSignup = findViewById(R.id.btnSignup);
        tvLoginLink = findViewById(R.id.tvBackToLogin);

        btnSignup.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String roll = etRoll.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (name.isEmpty() || roll.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            } else {
                // UI Demo Success
                Toast.makeText(this, "Registration Successful for " + name, Toast.LENGTH_LONG).show();
                // After signup, redirect to student dashboard
                startActivity(new Intent(activity_signup.this, activity_student_dashboard.class));
                finish();
            }
        });

        tvLoginLink.setOnClickListener(v -> {
            // Goes back to Login screen (MainActivity)
            finish();
        });
    }
}