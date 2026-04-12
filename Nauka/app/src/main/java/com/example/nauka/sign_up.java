package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class sign_up extends AppCompatActivity {

    Button createBtn;
    TextView loginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_up);

        createBtn = findViewById(R.id.btnSignup);
        loginRedirect = findViewById(R.id.btnLoginRedirect);

        createBtn.setOnClickListener(v -> {
            Intent intent = new Intent(sign_up.this, homescreen.class);
            startActivity(intent);
        });

        loginRedirect.setOnClickListener(v -> {
            Intent intent = new Intent(sign_up.this, login.class);
            startActivity(intent);
        });
    }
}