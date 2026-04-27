package com.example.ps1registeration;

import android.os.Bundle;
import android.os.Environment;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText etId, etName, etPhone;
    Button btnSave, btnLoad;
    TableLayout tableLayout;
    String FILE_NAME = "employees.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);

        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        tableLayout = findViewById(R.id.tableLayout);

        btnSave.setOnClickListener(v -> saveData());
        btnLoad.setOnClickListener(v -> loadData());
    }

    private File getDownloadFile() {
        File downloads = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
        );
        return new File(downloads, FILE_NAME);
    }

    private void saveData() {
        String id = etId.getText().toString();
        String name = etName.getText().toString();
        String contact = etPhone.getText().toString();

        try {
            File file = getDownloadFile();

            FileWriter fw = new FileWriter(file, true);
            fw.write(id + "," + name + "," + contact + "\n");
            fw.close();

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

            etId.setText("");
            etName.setText("");
            etPhone.setText("");

        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        tableLayout.removeAllViews();

        TableRow header = new TableRow(this);
        header.addView(makeCell("Emp ID"));
        header.addView(makeCell("Name"));
        header.addView(makeCell("Contact"));
        tableLayout.addView(header);

        try {
            File file = getDownloadFile();

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                TableRow row = new TableRow(this);

                row.addView(makeCell(data[0]));
                row.addView(makeCell(data[1]));
                row.addView(makeCell(data[2]));

                tableLayout.addView(row);
            }

            br.close();

        } catch (Exception e) {
            Toast.makeText(this, "No employee data found", Toast.LENGTH_SHORT).show();
        }
    }

    private TextView makeCell(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(20, 20, 20, 20);
        return tv;
    }
}