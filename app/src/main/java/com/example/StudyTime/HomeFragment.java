package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.os.SystemClock.elapsedRealtime;

public class HomeFragment extends Fragment {


    private View rootView;
    public HomeFragment(){
        rootView = null;
    }

    SessionList sessionList;
    Session newSession;
    private long pauseOffset; // use to calculate time paused
    private Spinner spinnerCourse;
    private Chronometer simpleTimer;
    private boolean running;
    Button buttonStart;
    Button buttonStop;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            Log.d("Fragment Demo", "Creating Layout for Home Fragment");
            /*Display (inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.activity_home, container, false);

            buttonStop = rootView.findViewById(R.id.stopButton);
            sessionList = SessionList.getInstance();
            sessionList.initialize(getContext().getApplicationContext());

            newSession = new Session();

            // initiate views
            simpleTimer = rootView.findViewById(R.id.simpleTimer);
            simpleTimer.setFormat("Time: %s");
            simpleTimer.setBase(elapsedRealtime());
            addCourseSpinner(); // courses spinner


            // initiate views
            simpleTimer = (Chronometer) rootView.findViewById(R.id.simpleTimer);
            simpleTimer.setFormat("Time: %s");
            simpleTimer.setBase(SystemClock.elapsedRealtime());

            simpleTimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 60000) {
//                line below would stop the clock at the above set time (maybe after set amount of hours)
//                chronometer.setBase(SystemClock.elapsedRealtime());
//                give a message at the above time (maybe a time to take a quick break)
                        Toast.makeText(getActivity(), "Great Start, 1 minute down!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        return rootView;
    }

    public void startPauseTimer(View view) {
//        figure out how to change name of button based on bool running or !running
        buttonStart = (Button) rootView.findViewById(R.id.startButton);
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


    public void addCourseSpinner() {
        spinnerCourse = (Spinner) rootView.findViewById(R.id.spinnerCourse);
        List<String> courses = new ArrayList<String>();
        courses.add("Select your course:");
        courses.add("CS246");
        courses.add("BIO101");
        courses.add("REL275");

        //create a adapter for the spinner and set that to the spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapter);
    }


}

