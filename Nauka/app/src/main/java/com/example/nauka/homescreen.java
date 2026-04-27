package com.example.nauka;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class homescreen extends AppCompatActivity {

    CardView boatCard1, boatCard2, boatCard3;
    TextView name1, name2, name3, price1, price2, price3, loc1, loc2, loc3, rate1, rate2, rate3;
    TextView userGreeting, dateText;
    DatabaseReference mDatabase, userRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://nauka-93c75-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("yacht_details");


        userGreeting = findViewById(R.id.textView);
        dateText = findViewById(R.id.textView2);

        boatCard1 = findViewById(R.id.boatCard1);
        boatCard2 = findViewById(R.id.boatCard2);
        boatCard3 = findViewById(R.id.boatCard3);

        name1 = findViewById(R.id.cardName1); price1 = findViewById(R.id.cardPrice1); loc1 = findViewById(R.id.cardLocation1); rate1 = findViewById(R.id.cardReview1);
        name2 = findViewById(R.id.cardName2); price2 = findViewById(R.id.cardPrice2); loc2 = findViewById(R.id.cardLocation2); rate2 = findViewById(R.id.cardReview2);
        name3 = findViewById(R.id.cardName3); price3 = findViewById(R.id.cardPrice3); loc3 = findViewById(R.id.cardLocation3); rate3 = findViewById(R.id.cardReview3);


        setCurrentDate();

        fetchUserName();

        fetchBoatData();

        boatCard1.setOnClickListener(v -> openDetailScreen(0));
        boatCard2.setOnClickListener(v -> openDetailScreen(1));
        boatCard3.setOnClickListener(v -> openDetailScreen(2));
    }

    private void setCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd", Locale.ENGLISH);
        String formattedDate = dateFormat.format(calendar.getTime());
        dateText.setText(formattedDate);
    }

    private void fetchUserName() {
        String uid = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : null;
        if (uid != null) {
            userRef = FirebaseDatabase.getInstance("https://nauka-93c75-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .getReference("Users").child(uid);

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String fullName = snapshot.child("fullName").getValue(String.class);
                        if (fullName != null && !fullName.isEmpty()) {

                            String firstName = fullName.split(" ")[0];
                            userGreeting.setText("Hey, " + firstName + "!");
                        }
                    }
                }
                @Override public void onCancelled(@NonNull DatabaseError error) {}
            });
        }
    }

    private void fetchBoatData() {
        mDatabase.limitToFirst(3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                for (DataSnapshot boatSnap : snapshot.getChildren()) {
                    String name = boatSnap.child("name").getValue(String.class);
                    String subtitle = boatSnap.child("subtitle").getValue(String.class);
                    String rating = boatSnap.child("rating").getValue(String.class);
                    String price = "₹" + boatSnap.child("price").getValue().toString() + "/ride";

                    if (count == 0) { name1.setText(name); loc1.setText(subtitle); price1.setText(price); rate1.setText("⭐ " + rating); }
                    else if (count == 1) { name2.setText(name); loc2.setText(subtitle); price2.setText(price); rate2.setText("⭐ " + rating); }
                    else if (count == 2) { name3.setText(name); loc3.setText(subtitle); price3.setText(price); rate3.setText("⭐ " + rating); }
                    count++;
                }
            }
            @Override public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void openDetailScreen(int index) {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(homescreen.this, elite_ocean.class);
            intent.putExtra("boat_index", index);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, 300);
    }
}