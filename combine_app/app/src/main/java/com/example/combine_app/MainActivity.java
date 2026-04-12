package com.example.combine_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLinear, btnRelative, btnAbsolute, btnFrame,
            btnTable, btnGrid, btnConstraint,
            btnImplicit, btnExplicit, btnProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLinear = findViewById(R.id.btnLinear);
        btnRelative = findViewById(R.id.btnRelative);
        btnAbsolute = findViewById(R.id.btnAbsolute);
        btnFrame = findViewById(R.id.btnFrame);
        btnTable = findViewById(R.id.btnTable);
        btnGrid = findViewById(R.id.btnGrid);
        btnConstraint = findViewById(R.id.btnConstraint);
        btnImplicit = findViewById(R.id.btnImplicit);
        btnExplicit = findViewById(R.id.btnExplicit);
        btnProfile = findViewById(R.id.btnProfile);

        btnLinear.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LinearActivity.class)));

        btnRelative.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RelativeActivity.class)));

        btnAbsolute.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ProfileActivity.class)));

        btnFrame.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, FrameActivity.class)));

        btnTable.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TableActivity.class)));

        btnGrid.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, GridActivity.class)));

        btnConstraint.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ConstraintActivity.class)));

        btnImplicit.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ImplicitActivity.class)));

        btnExplicit.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ExplicitActivity.class)));

        btnProfile.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
    }
}