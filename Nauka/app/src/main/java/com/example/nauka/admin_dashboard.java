package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class admin_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        MaterialCardView addYacht = findViewById(R.id.cardAddYacht);
        MaterialCardView viewBookings = findViewById(R.id.cardViewBookings);
        MaterialButton logout = findViewById(R.id.adminLogout);


        addYacht.setOnClickListener(v -> {
            Intent intent = new Intent(admin_dashboard.this, AddYachtActivity.class);
            startActivity(intent);
        });

        viewBookings.setOnClickListener(v -> {
            Intent intent = new Intent(admin_dashboard.this, ViewBookingsActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(admin_dashboard.this, login.class));
            finish();
        });
    }
}