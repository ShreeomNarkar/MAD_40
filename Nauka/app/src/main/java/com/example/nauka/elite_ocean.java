package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.*;

public class elite_ocean extends AppCompatActivity {

    TextView title, subtitle, topTitle, topSubtitle, capacity, description, priceLabel, ratingTxt;
    ChipGroup amenityGroup;
    MaterialButton bookBtn;
    DatabaseReference mDatabase;
    int currentBoatPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elite_ocean);

        title = findViewById(R.id.textView5);
        subtitle = findViewById(R.id.textView6);
        topTitle = findViewById(R.id.textView3);
        topSubtitle = findViewById(R.id.textView4);
        capacity = findViewById(R.id.textView7);
        description = findViewById(R.id.textView10);
        priceLabel = findViewById(R.id.textView12);
        ratingTxt = findViewById(R.id.textView14);
        amenityGroup = findViewById(R.id.detailsAmenityGroup);
        bookBtn = findViewById(R.id.button7);

        int boatIndex = getIntent().getIntExtra("boat_index", 0);

        mDatabase = FirebaseDatabase.getInstance("https://nauka-93c75-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("yacht_details");

        loadYachtDetails(boatIndex);

        bookBtn.setOnClickListener(v -> {
            Intent intent = new Intent(elite_ocean.this, booking.class);
            intent.putExtra("boat_name", title.getText().toString());
            intent.putExtra("boat_price", currentBoatPrice);
            startActivity(intent);
        });
    }

    private void loadYachtDetails(int index) {
        mDatabase.limitToFirst(index + 1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for (DataSnapshot boatSnap : snapshot.getChildren()) {
                    if (count == index) {
                        Yacht yacht = boatSnap.getValue(Yacht.class);
                        if (yacht != null) {
                            title.setText(yacht.name);
                            topTitle.setText(yacht.name);
                            subtitle.setText("(" + yacht.subtitle + ")");
                            topSubtitle.setText(yacht.subtitle);
                            capacity.setText("Capacity: " + yacht.capacity);
                            description.setText(yacht.description);
                            ratingTxt.setText(yacht.rating);
                            currentBoatPrice = yacht.price;
                            priceLabel.setText("₹" + currentBoatPrice);

                            amenityGroup.removeAllViews();
                            if (yacht.amenities != null) {
                                for (String amenity : yacht.amenities) {
                                    Chip chip = new Chip(elite_ocean.this);
                                    chip.setText(amenity);
                                    chip.setChipBackgroundColorResource(android.R.color.transparent);
                                    chip.setChipStrokeWidth(2f);
                                    chip.setChipStrokeColorResource(android.R.color.white);
                                    chip.setTextColor(getResources().getColor(android.R.color.white));
                                    amenityGroup.addView(chip);
                                }
                            }
                        }
                    }
                    count++;
                }
            }
            @Override public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}