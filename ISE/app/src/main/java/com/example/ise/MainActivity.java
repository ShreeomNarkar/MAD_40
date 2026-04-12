package com.example.ise;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnimg, btnSubmit;
    ImageView imageView;
    RatingBar ratingBar;
    TextView txtRating;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnimg = findViewById(R.id.imgcp);
        btnSubmit = findViewById(R.id.btnSubmit);
        imageView = findViewById(R.id.imageView3);
        ratingBar = findViewById(R.id.ratingBar);
        txtRating = findViewById(R.id.txtRating);

        btnimg.setOnClickListener(v -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
        });

        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            if (fromUser) {
                String message = "Last Rating: " + rating;
                txtRating.setText(message);
            }
        });

        btnSubmit.setOnClickListener(v -> {

            Toast.makeText(MainActivity.this,
                    "Image & Rating Submitted!",
                    Toast.LENGTH_SHORT).show();

            imageView.setImageDrawable(null);
            imageView.setBackgroundColor(
                    getResources().getColor(android.R.color.darker_gray)
            );

            ratingBar.setRating(0);
            txtRating.setText("No Rating Yet");
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE
                && resultCode == RESULT_OK
                && data != null) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}