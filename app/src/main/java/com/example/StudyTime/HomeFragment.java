package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;

import java.util.List;

import static android.os.SystemClock.elapsedRealtime;
/*
Home Fragment
The HomeFragment houses the timer,
saves a timed session,
and spinner to choose a course
 */

public class HomeFragment extends Fragment {
    /*Fragment instead of activity so can
    be accessed from drawer fragment

     */

/*
base constructor to instantiate
rootView object
 */
    private View rootView;
    public HomeFragment(){
        rootView = null;
    }

    SessionList sessionList = SessionList.getInstance();
    CourseList courseList = CourseList.getInstance();
    Session newSession;
    private long pauseOffset; // use to calculate time paused
    private Spinner spinnerCourse;
    private Chronometer simpleTimer;
    private boolean running;
    Button buttonStart;
    Button buttonStop;

/*
onCreateView function
creates and returns the view
hierarchy associated with the fragment.
 */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            Log.d("Fragment Demo", "Creating Layout for Home Fragment");
            /*Display (inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.activity_home, container, false);

            buttonStop = rootView.findViewById(R.id.stopButton);

            sessionList.initialize(getContext().getApplicationContext());
            courseList.initialize(getContext().getApplicationContext());

            // makes sure that the first course in the CourseList is a default course
            courseList.addCourse("Select a course:");

            newSession = new Session();

            // initiate views
            simpleTimer = rootView.findViewById(R.id.simpleTimer);
            simpleTimer.setFormat("Time: %s");
            simpleTimer.setBase(elapsedRealtime());
            addCourseSpinner(); // courses spinner

            setStartPauseTimer();
            setStopTimer();

            // initiate views
            simpleTimer = rootView.findViewById(R.id.simpleTimer);
            simpleTimer.setFormat("Time: %s");
            simpleTimer.setBase(SystemClock.elapsedRealtime());
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setStartPauseTimer() {
//        figure out how to change name of button based on bool running or !running
        buttonStart = rootView.findViewById(R.id.startButton);
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

    public void setStopTimer() {
        //     stops the timer and clears the paused hold so it will truly reset
        buttonStop = rootView.findViewById(R.id.stopButton);
        buttonStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (running || !running) {
                    buttonStart.setText("Start");
                    simpleTimer.setBase(elapsedRealtime());
                    simpleTimer.stop();
                    pauseOffset = 0;
                    running = false;

                    newSession.stop();
                    newSession.setCourse(new Course(spinnerCourse.getSelectedItem().toString()));
                    sessionList.addSession(newSession);
                }
            }
        });
    }

    public void addCourseSpinner() {
        spinnerCourse = rootView.findViewById(R.id.spinnerCourse);
        List<String> courses = courseList.getStringList();

        //create a adapter for the spinner and set that to the spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapter);
    }
}

