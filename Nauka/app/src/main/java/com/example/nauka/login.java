package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    MaterialButton loginBtn;
    TextView signUp;
    TextInputEditText emailEdit, passwordEdit;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        loginBtn = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.btnRegister);

        emailEdit = (TextInputEditText) ((TextInputLayout) findViewById(R.id.emailInputLayout)).getEditText();
        passwordEdit = (TextInputEditText) ((TextInputLayout) findViewById(R.id.passwordInputLayout)).getEditText();

        loginBtn.setOnClickListener(v -> {
            String email = emailEdit.getText().toString().trim();
            String password = passwordEdit.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }


            if (email.equals("admin@nauka.com") && password.equals("nauka")) {
                Toast.makeText(login.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this, admin_dashboard.class));
                finish();
            }

            else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(authResult -> {
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            redirectUser(email);
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(login.this, "Authentication Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
            }
        });

        signUp.setOnClickListener(v -> {
            startActivity(new Intent(login.this, sign_up.class));
        });
    }

    private void redirectUser(String email) {
        if (email != null && email.equals("admin@nauka.com")) {
            startActivity(new Intent(login.this, admin_dashboard.class));
        } else {
            startActivity(new Intent(login.this, homescreen.class));
        }
        finish();
    }
}