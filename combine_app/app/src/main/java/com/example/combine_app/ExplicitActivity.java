package com.example.combine_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.combine_app.ProfileActivity;

public class ExplicitActivity extends AppCompatActivity {

    EditText un, ps;
    Button lg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explicit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void checkpass(View view){
        un=findViewById(R.id.username);
        ps=findViewById(R.id.passkey);

        String unn=un.getText().toString();
        String pss=ps.getText().toString();

        if(unn.equals("Shree") && pss.equals("Shreeom@16")){
            Intent i = new Intent(ExplicitActivity.this, ProfileActivity.class);
            startActivity(i);

        } else {
            Toast.makeText(this,"Wrong Username or Password", Toast.LENGTH_SHORT).show();
        }

    }
}