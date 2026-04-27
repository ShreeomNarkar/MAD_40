package com.example.popupmenu;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopup = findViewById(R.id.btnPopup);

        btnPopup.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnPopup);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();

                if (id == R.id.edit) {
                    Toast.makeText(this, "Edit Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.share) {
                    Toast.makeText(this, "Share Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.delete) {
                    Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });

            popupMenu.show();   // show popup menu
        });
    }
}