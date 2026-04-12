package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class nextscreen extends AppCompatActivity {

    Button createBtn;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nextscreen);

        createBtn = findViewById(R.id.button);
        loginText = findViewById(R.id.loginText);

        createBtn.setOnClickListener(v -> {
            Intent intent = new Intent(nextscreen.this, sign_up.class);
            startActivity(intent);
        });

        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(nextscreen.this, login.class);
            startActivity(intent);
        });
    }
}