package com.example.StudyTime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
    private boolean started = false;
    private long pauseOffset; // use to calculate time paused
    FileHelper fileHelper;
    Session newSession;
    SessionList sessionList;
    Button buttonStart;
    Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStop = findViewById(R.id.stopButton);

        sessionList = SessionList.getInstance();
        newSession = new Session();

        fileHelper = new FileHelper(this, "session_list");
        fileHelper.createFile();

        // initiate views
        simpleTimer = findViewById(R.id.simpleTimer);
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


    public void startPauseTimer(View view) {
//        figure out how to change name of button based on bool running or !running
        buttonStart = (Button)findViewById(R.id.startButton);
        buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!running && (buttonStart.getText().toString().trim().equals("Start"))) {
                    buttonStart.setText("Pause");
                    simpleTimer.setBase(elapsedRealtime() - pauseOffset);
                    simpleTimer.start();
                    running = true;
                    started = true;

                    newSession.start();
                } else if (running && (buttonStart.getText().toString().trim().equals("Pause"))) {
                    buttonStart.setText("Start");
                    pauseOffset = elapsedRealtime() - simpleTimer.getBase();
                    simpleTimer.stop();
                    running = false;

                    newSession.pause();
                }
            }
        });
    }

    public void stopTimer(View view) {
        //     stops the timer and clears the paused hold so it will truly reset
        buttonStart.setText("Start");
        simpleTimer.stop();
        pauseOffset = 0;
        running = false;

        newSession.stop();
        sessionList.addSession(newSession);
    }
}
