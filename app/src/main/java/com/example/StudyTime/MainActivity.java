package com.example.StudyTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    // initiate views
    private Chronometer simpleTimer = (Chronometer) findViewById(R.id.simpleTimer);
    private boolean running;
    private long pauseOffset; // use to calculate time paused
    private FileHelper fileHelper;
    private ArrayList<Sessions> sessionArrayList;
    SessionList sessionList = SessionList.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileHelper = new FileHelper("sessions");

        simpleTimer.setFormat("Time: %s");
        simpleTimer.setBase(SystemClock.elapsedRealtime());

        simpleTimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 60000){
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
//      **********************************************
        // when add session is clicked the time will be sent to addsession
//        View.OnClickListener stopListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////            pauseTimer();
//                simpleTimer.stop();
//                long saveTime = SystemClock.elapsedRealtime() - simpleTimer.getBase();
//                long seconds = (long) (saveTime / 1000 % 60); // if seconds needed
////            adding the saveTime to the addsessionactivity
////                Intent intent = new Intent(getApplicationContext(),
////                        AddSessionActivity.class).putExtra("time",seconds);
//                startActivity(intent);
////            }
//        }
////         *******************************************
//
//        };
    }
    public void startTimer(View view) {
        // if not running this will start the timer (and subtract the time from stopped/paused)
        if (!running){
            simpleTimer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            simpleTimer.start();
            running = true;
        }
    }

    public void pauseTimer(View view) {
        // no way to stop only pause, this will take the time and subtract the time stopped
        if (running) {
            simpleTimer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - simpleTimer.getBase();
            running = false;
        }
    }

    public void resetTimer(View view) {
        //     stops the timer and clears the paused hold so it will truly reset
        simpleTimer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

}
