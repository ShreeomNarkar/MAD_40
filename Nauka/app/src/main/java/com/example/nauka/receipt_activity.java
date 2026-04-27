package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class receipt_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_activity);

        TextView tvBoatName = findViewById(R.id.resBoatName);
        TextView tvDetails = findViewById(R.id.resDetails);
        TextView tvTotal = findViewById(R.id.resTotal);
        TextView tvId = findViewById(R.id.resBookingId);
        MaterialButton btnHome = findViewById(R.id.btnBackHome);

        String boatName = getIntent().getStringExtra("BOAT");
        String slot = getIntent().getStringExtra("SLOT");
        int guests = getIntent().getIntExtra("GUESTS", 1);
        int nights = getIntent().getIntExtra("NIGHTS", 1);
        int total = getIntent().getIntExtra("TOTAL", 0);

        if (boatName != null) {
            tvBoatName.setText(boatName);
        } else {
            tvBoatName.setText("Elite Yacht");
        }

        String displaySlot = (slot != null) ? slot : "Not Selected";
        String combinedDetails = "Departure: " + displaySlot + "\n" +
                "Guests: " + guests + " Persons\n" +
                "Duration: " + nights + " Night(s)";

        tvDetails.setText(combinedDetails);
        tvTotal.setText("₹" + total);

        tvId.setText("Booking ID: #NK" + (System.currentTimeMillis() / 1000000));

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(receipt_activity.this, homescreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}