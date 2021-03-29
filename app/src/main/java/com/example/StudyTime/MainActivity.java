package com.example.StudyTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Timer;

import static android.os.SystemClock.elapsedRealtime;

public class MainActivity extends AppCompatActivity {

    private Chronometer simpleTimer;
    private boolean running;
    private long pauseOffset; // use to calculate time paused
    FileHelper fileHelper;
    Session newSession;
//    Button btn = (Button)findViewById(R.id.startButton);
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileHelper = new FileHelper(this, "session_list");
        fileHelper.createFile();

        // initiate views
        simpleTimer = (Chronometer) findViewById(R.id.simpleTimer);
        simpleTimer.setFormat("Time: %s");
        simpleTimer.setBase(elapsedRealtime());

        simpleTimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((elapsedRealtime() - chronometer.getBase())>= 60000){
//                line below would stop the clock at the above set time (maybe after set amount of hours)
//                chronometer.setBase(SystemClock.elapsedRealtime());
//                give a message at the above time (maybe a time to take a quick break)
                    Toast.makeText(MainActivity.this, "Great Start, 1 minute down!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void moveToCalendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void moveToAddSession(View view) {
        Intent intent = new Intent(this, AddSessionActivity.class);
        startActivity(intent);
    }

    public void startTimer(View view) {
        // if not running this will start the timer (and subtract the time from stopped/paused)
        if (!running){
            simpleTimer.setBase(elapsedRealtime() - pauseOffset);
            simpleTimer.start();
            running = true;
        }
    }

    public void pauseTimer(View view) {
        // no way to stop only pause, this will take the time and subtract the time stopped
        if (running) {
            simpleTimer.stop();
            pauseOffset = elapsedRealtime() - simpleTimer.getBase();
            running = false;
        }
    }

    public void startPauseTimer(View view) {
//        figure out how to change name of button based on bool running or !running
        btn = (Button)findViewById(R.id.startButton);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!running && (btn.getText().toString().trim().equals("Start"))) {
                    btn.setText("Pause");
                    simpleTimer.setBase(elapsedRealtime() - pauseOffset);
                    simpleTimer.start();

                    running = true;
                } else if (running && (btn.getText().toString().trim().equals("Pause"))) {
                    btn.setText("Start");
                    simpleTimer.stop();
                    pauseOffset = elapsedRealtime() - simpleTimer.getBase();
//            long pausedSession = newSession.getPauseTime(SystemClock.setCurrentTimeMillis());
                    running = false;
                }

            }
        });
    }

    public void stopTimer(View view) {
//        long sessionSave = newSession.setEndTime(SystemClock.elapsedRealtime(), newSession);
//        Timestamp sessionSave = newSession.setEndTime(SystemClock.elapsedRealtime());

        //     stops the timer and clears the paused hold so it will truly reset
        simpleTimer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}
