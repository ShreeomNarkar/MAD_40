package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class homescreen extends AppCompatActivity {

    CardView boatCard1, boatCard2, boatCard3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        boatCard1 = findViewById(R.id.boatCard1);
        boatCard2 = findViewById(R.id.boatCard2);
        boatCard3 = findViewById(R.id.boatCard3);

        boatCard1.setOnClickListener(v -> openScreen(elite_ocean.class));
        boatCard2.setOnClickListener(v -> openScreen(ocean_pearl.class));
        boatCard3.setOnClickListener(v -> openScreen(elite_ocean.class));
    }

    private void openScreen(Class<?> cls) {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(homescreen.this, cls);
            startActivity(intent);

            overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );
        }, 400);
    }
}