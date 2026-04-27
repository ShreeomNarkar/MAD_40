package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class sign_up extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private TextInputEditText nameEdit, emailEdit, cityEdit, passwordEdit;
    private MaterialButton btnSignup;
    private TextView loginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mAuth = FirebaseAuth.getInstance();


        mDatabase = FirebaseDatabase.getInstance("https://nauka-93c75-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference();

        nameEdit = (TextInputEditText) ((TextInputLayout) findViewById(R.id.nameInputLayout)).getEditText();
        emailEdit = (TextInputEditText) ((TextInputLayout) findViewById(R.id.emailInputLayout)).getEditText();
        cityEdit = (TextInputEditText) ((TextInputLayout) findViewById(R.id.cityInputLayout)).getEditText();
        passwordEdit = (TextInputEditText) ((TextInputLayout) findViewById(R.id.passwordInputLayout)).getEditText();

        btnSignup = findViewById(R.id.btnSignup);
        loginRedirect = findViewById(R.id.btnLoginRedirect);

        btnSignup.setOnClickListener(v -> handleSignUp());

        loginRedirect.setOnClickListener(v -> {
            Intent intent = new Intent(sign_up.this, login.class);
            startActivity(intent);
            finish();
        });
    }

    private void handleSignUp() {
        String name = nameEdit.getText().toString().trim();
        String email = emailEdit.getText().toString().trim();
        String city = cityEdit.getText().toString().trim();
        String pass = passwordEdit.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || city.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String uid = mAuth.getCurrentUser().getUid();
                        saveUserToDatabase(uid, name, email, city);
                    } else {
                        Toast.makeText(sign_up.this, "Sign Up Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void saveUserToDatabase(String uid, String name, String email, String city) {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("fullName", name);
        userMap.put("email", email);
        userMap.put("city", city);
        userMap.put("role", "user");

        mDatabase.child("Users").child(uid).setValue(userMap)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(sign_up.this, "Welcome to Nauka!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(sign_up.this, homescreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Log.e("FirebaseDB", "Failed to write to DB: " + e.getMessage());

                    Intent intent = new Intent(sign_up.this, homescreen.class);
                    startActivity(intent);
                    finish();
                });
    }
}