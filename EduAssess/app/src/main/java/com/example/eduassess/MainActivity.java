package com.example.eduassess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private CheckBox cbIsAdmin;
    private Button btnLogin;
    private TextView tvGoToSignup; // 1. Declare the TextView

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        cbIsAdmin = findViewById(R.id.cbIsAdmin);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToSignup = findViewById(R.id.tvGoToSignup); // 2. Initialize it

        // 3. Navigation Logic
        tvGoToSignup.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_signup.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
                return;
            }

            if (cbIsAdmin.isChecked()) {
                if (email.equals("admin@eduassess.com") && password.equals("admin123")) {
                    Toast.makeText(this, "Admin Login Successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, activity_admin_dashboard.class));
                    finish();
                } else {
                    Toast.makeText(this, "Invalid Admin Credentials", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Student Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, activity_student_dashboard.class));
                finish();
            }
        });
    }
}