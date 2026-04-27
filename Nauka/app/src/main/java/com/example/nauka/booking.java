package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class booking extends AppCompatActivity {

    // Dynamic Price based on Yacht selection
    private int pricePerPerson = 650;
    private String selectedBoatName = "Yacht";

    private int guestCount = 1;
    private int nightCount = 1;
    private boolean isPaymentFlow = false;

    private TextView txtCount, txtNightCount, totalAmountText;
    private ChipGroup timeChipGroup;
    private ConstraintLayout paymentSection;
    private MaterialButton btnConfirm;
    private ImageView paymentQr;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://nauka-93c75-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();


        if (getIntent().hasExtra("boat_name")) {
            selectedBoatName = getIntent().getStringExtra("boat_name");
        }

        pricePerPerson = getIntent().getIntExtra("boat_price", 650);

        txtCount = findViewById(R.id.txtCount);
        txtNightCount = findViewById(R.id.txtNightCount);
        totalAmountText = findViewById(R.id.totalAmountText);
        timeChipGroup = findViewById(R.id.timeChipGroup);
        paymentSection = findViewById(R.id.paymentSection);
        btnConfirm = findViewById(R.id.btnConfirm);
        paymentQr = findViewById(R.id.imageView4);

        paymentSection.setVisibility(View.GONE);
        paymentQr.setVisibility(View.GONE);

        findViewById(R.id.btnPlus).setOnClickListener(v -> { guestCount++; updateUI(); });
        findViewById(R.id.btnMinus).setOnClickListener(v -> { if (guestCount > 1) { guestCount--; updateUI(); } });
        findViewById(R.id.btnPlusNight).setOnClickListener(v -> { nightCount++; updateUI(); });
        findViewById(R.id.btnMinusNight).setOnClickListener(v -> { if (nightCount > 1) { nightCount--; updateUI(); } });

        btnConfirm.setOnClickListener(v -> {
            if (!isPaymentFlow) {
                handleInitialConfirmation();
            } else {
                saveBookingToFirebase();
            }
        });

        updateUI();
    }

    private void updateUI() {
        txtCount.setText(String.valueOf(guestCount));
        txtNightCount.setText(String.valueOf(nightCount));
        int total = (pricePerPerson * guestCount) * nightCount;
        totalAmountText.setText("Total Bill: ₹" + total);
    }

    private void handleInitialConfirmation() {
        if (timeChipGroup.getCheckedChipId() == -1) {
            Toast.makeText(this, "Please select a Departure Time", Toast.LENGTH_SHORT).show();
            return;
        }
        paymentSection.setVisibility(View.VISIBLE);
        paymentQr.setVisibility(View.VISIBLE);
        btnConfirm.setText("Complete Payment & Book");
        isPaymentFlow = true;
        disableSelectors();
    }

    private void saveBookingToFirebase() {
        String userId = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : "Guest";
        int selectedChipId = timeChipGroup.getCheckedChipId();
        Chip selectedChip = findViewById(selectedChipId);
        String departureTime = selectedChip.getText().toString();
        int finalAmount = (pricePerPerson * guestCount) * nightCount;

        String bookingId = mDatabase.child("bookings").push().getKey();

        HashMap<String, Object> bookingData = new HashMap<>();
        bookingData.put("bookingId", bookingId);
        bookingData.put("userId", userId);
        bookingData.put("boatName", selectedBoatName);
        bookingData.put("departureTime", departureTime);
        bookingData.put("guests", guestCount);
        bookingData.put("nights", nightCount);
        bookingData.put("totalAmount", finalAmount);
        bookingData.put("status", "Confirmed");
        bookingData.put("timestamp", System.currentTimeMillis());

        if (bookingId != null) {
            mDatabase.child("bookings").child(bookingId).setValue(bookingData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Booking Successful!", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(this, receipt_activity.class);
                        intent.putExtra("SLOT", departureTime);
                        intent.putExtra("GUESTS", guestCount);
                        intent.putExtra("NIGHTS", nightCount);
                        intent.putExtra("TOTAL", finalAmount);
                        intent.putExtra("BOAT", selectedBoatName);
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Booking Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    private void disableSelectors() {
        findViewById(R.id.btnPlus).setEnabled(false);
        findViewById(R.id.btnMinus).setEnabled(false);
        findViewById(R.id.btnPlusNight).setEnabled(false);
        findViewById(R.id.btnMinusNight).setEnabled(false);
        for (int i = 0; i < timeChipGroup.getChildCount(); i++) {
            timeChipGroup.getChildAt(i).setEnabled(false);
        }
    }
}