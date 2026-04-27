package com.example.contextmenu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentName = findViewById(R.id.studentName);

        // Register TextView for context menu
        registerForContextMenu(studentName);
    }

    // Create context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Student Options");
        menu.add(0, 1, 0, "View Details");
        menu.add(0, 2, 0, "Edit Student");
        menu.add(0, 3, 0, "Delete Student");
    }

    // Handle menu item click
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "Viewing Details", Toast.LENGTH_SHORT).show();
                return true;

            case 2:
                Toast.makeText(this, "Editing Student", Toast.LENGTH_SHORT).show();
                return true;

            case 3:
                Toast.makeText(this, "Deleting Student", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}