package com.example.exp1;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4;
    ConstraintLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        main=findViewById(R.id.main);
        b1=findViewById(R.id.Red);

        b1.setOnClickListener(v->{
            main.setBackgroundColor(Color.RED);
        });

        b2=findViewById(R.id.Blue);

        b2.setOnClickListener(v->{
            main.setBackgroundColor(Color.BLUE);
        });

        b3=findViewById(R.id.Green);

        b3.setOnClickListener(v->{
            main.setBackgroundColor(Color.GREEN);
        });

        b4=findViewById(R.id.Yellow);

        b4.setOnClickListener(v->{
            main.setBackgroundColor(Color.YELLOW);
        });

    }
}