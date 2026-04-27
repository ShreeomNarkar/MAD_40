package com.example.alarmset;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    TimePicker timePicker;
    Button btnSetAlarm;

    Handler handler = new Handler();
    int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute;
    boolean isAlarmSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);

        timePicker.setIs24HourView(true);

        // only future dates
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

        btnSetAlarm.setOnClickListener(v -> {
            alarmYear = datePicker.getYear();
            alarmMonth = datePicker.getMonth();
            alarmDay = datePicker.getDayOfMonth();
            alarmHour = timePicker.getHour();
            alarmMinute = timePicker.getMinute();

            isAlarmSet = true;

            Toast.makeText(this, "Alarm Set Successfully", Toast.LENGTH_SHORT).show();
        });

        startAlarmChecker();
    }

    private void startAlarmChecker() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAlarmSet) {
                    Calendar now = Calendar.getInstance();

                    if (now.get(Calendar.YEAR) == alarmYear &&
                            now.get(Calendar.MONTH) == alarmMonth &&
                            now.get(Calendar.DAY_OF_MONTH) == alarmDay &&
                            now.get(Calendar.HOUR_OF_DAY) == alarmHour &&
                            now.get(Calendar.MINUTE) == alarmMinute) {

                        MediaPlayer mp = MediaPlayer.create(
                                MainActivity.this,
                                Settings.System.DEFAULT_ALARM_ALERT_URI
                        );
                        mp.start();

                        Toast.makeText(MainActivity.this,
                                "Alarm Ringing!",
                                Toast.LENGTH_LONG).show();

                        isAlarmSet = false; // stop repeat ringing
                    }
                }

                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
}