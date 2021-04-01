package com.example.StudyTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.os.SystemClock.elapsedRealtime;

public class MainActivity extends AppCompatActivity {

    SessionList sessionList;
    CourseList courseList;

    Session newSession;
    Course newCourse;

    private Chronometer simpleTimer;
    private boolean running;
    private long pauseOffset; // use to calculate time paused
    private Spinner spinnerCourse;

    Button buttonStart;
    Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStop = findViewById(R.id.stopButton);

        sessionList = SessionList.getInstance();
        sessionList.initialize(this.getApplicationContext());
        courseList = CourseList.getInstance();
        courseList.initialize(this.getApplicationContext());

        newSession = new Session();
        newCourse = new Course("CS 246");

        courseList.addCourse(newCourse);

        // initiate views
        simpleTimer = findViewById(R.id.simpleTimer);
        simpleTimer.setFormat("Time: %s");
        simpleTimer.setBase(elapsedRealtime());
        addCourseSpinner(); // courses spinner

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
        if (running == true) {
            buttonStart.setText("Start");
            simpleTimer.setBase(elapsedRealtime());
            simpleTimer.stop();
            pauseOffset = 0;
            running = false;

            newSession.stop();
            sessionList.addSession(newSession);
        }
    }


//    addCourseSpinner();
//    addListenerOnButton();
    public void addCourseSpinner(){
        spinnerCourse = (Spinner) findViewById(R.id.spinnerCourse);
        List<String> courses = courseList.getStringList();
//        List<String> courses = new ArrayList<String>();
//        courses.add("Select your course:");
//        courses.add("CS246");
//        courses.add("BIO101");
//        courses.add("REL275");

        //create a adapter for the spinner and set that to the spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapter);
    }
//    public void addListenerOnButton() {
//
////        spinnerCourse = (Spinner) findViewById(R.id.spinner1);
//        Button btnChooseCourse = (Button) findViewById(R.id.btnSubmit);
//
//        btnChooseCourse.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,
//                        "You Chose : " +
//                                "\nCourse : "+ String.valueOf(spinnerCourse.getSelectedItem()) +
//                                "\nTime : ",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
//    }
}

