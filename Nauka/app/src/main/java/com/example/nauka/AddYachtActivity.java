package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddYachtActivity extends AppCompatActivity {

    private TextInputEditText nameIn, subtitleIn, capacityIn, priceIn, descIn;
    private ChipGroup amenityGroup;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_yacht);

        mDatabase = FirebaseDatabase.getInstance("https://nauka-93c75-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference();

        nameIn = findViewById(R.id.adminBoatName);
        subtitleIn = findViewById(R.id.adminBoatSubtitle);
        capacityIn = findViewById(R.id.adminCapacity);
        priceIn = findViewById(R.id.adminPrice);
        descIn = findViewById(R.id.adminDescription);
        amenityGroup = findViewById(R.id.adminAmenityGroup);

        MaterialButton btnSave = findViewById(R.id.btnAddBoat);
        btnSave.setOnClickListener(v -> saveDetailedBoat());
    }

    private void saveDetailedBoat() {
        String name = nameIn.getText().toString().trim();
        String subtitle = subtitleIn.getText().toString().trim();
        String capacity = capacityIn.getText().toString().trim();
        String priceString = priceIn.getText().toString().trim();
        String desc = descIn.getText().toString().trim();

        if (name.isEmpty() || priceString.isEmpty()) {
            Toast.makeText(this, "Name and Price are required!", Toast.LENGTH_SHORT).show();
            return;
        }


        List<String> selectedAmenities = new ArrayList<>();
        for (int i = 0; i < amenityGroup.getChildCount(); i++) {
            Chip chip = (Chip) amenityGroup.getChildAt(i);
            if (chip.isChecked()) {
                selectedAmenities.add(chip.getText().toString());
            }
        }


        String boatId = mDatabase.child("yacht_details").push().getKey();


        HashMap<String, Object> boatDetails = new HashMap<>();
        boatDetails.put("name", name);
        boatDetails.put("subtitle", subtitle);
        boatDetails.put("capacity", capacity + " Guests");
        boatDetails.put("price", Integer.parseInt(priceString));
        boatDetails.put("description", desc);
        boatDetails.put("amenities", selectedAmenities);
        boatDetails.put("rating", "5.0");

        if (boatId != null) {

            mDatabase.child("yacht_details").child(boatId).setValue(boatDetails)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Yacht Added Successfully!", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(AddYachtActivity.this, admin_dashboard.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Database Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}