package com.example.eduassess;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class activity_edit_students extends AppCompatActivity {

    private String[] names = {
            "Aarav Sharma", "Ananya Iyer", "Vihaan Patel", "Diya Mistri", "Arjun Verma",
            "Myra Kapoor", "Sai Reddy", "Ishani Joshi", "Rohan Gupta", "Zara Khan",
            "Krishna Das", "Saanvi Gill", "Aditya Singh", "Anika Bose", "Ishaan Malhotra",
            "Pari Yadav", "Dev Shah", "Navya Nair", "Kabir Mehra", "Riya Chowdhury",
            "Aaryan Goel", "Kiara Advani", "Vivaan Saxena", "Sia Pandit", "Dhruv Bhat",
            "Tara Deshmukh", "Omkar Kulkarni", "Amrita Rao", "Pranav Hegde", "Isha Pande",
            "Sahil Merchant", "Kavya Menon", "Tejas Patil", "Meher Kaur", "Yash Bansal",
            "Tanvi Shinde", "Aryan More", "Gauri Gadgil", "Karan Jadhav", "Bhakti Kadam"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_students);

        LinearLayout container = findViewById(R.id.studentContainer);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < names.length; i++) {
            // Inflate the item row
            View row = inflater.inflate(R.layout.activity_item_student, container, false);

            TextView tvRoll = row.findViewById(R.id.tvRoll);
            TextView tvName = row.findViewById(R.id.tvName);

            // Setting the data
            tvRoll.setText(String.format("%02d", i + 1));
            tvName.setText(names[i]);

            // Add to the main list
            container.addView(row);
        }
    }
}