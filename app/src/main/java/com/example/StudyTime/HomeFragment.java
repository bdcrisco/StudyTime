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
import android.widget.Chronometer;
import android.widget.Toast;

public class HomeFragment extends Fragment {


    private View rootView;
    public HomeFragment(){
        rootView = null;
    }


    private Chronometer simpleTimer;
    private boolean running;
    private long pauseOffset; // use to calculate time paused
    FileHelper fileHelper;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            Log.d("Fragment Demo", "Creating Layout for Home Fragment");

            /*Display (inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.activity_home, container, false);

            fileHelper = new FileHelper(getActivity(), "session_list");
            fileHelper.createFile();

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




    public void moveToCalendar(View view) {
        Intent intent = new Intent(getActivity(), CalendarFragment.class);
        startActivity(intent);
    }

    public void moveToAddSession(View view) {
        Intent intent = new Intent(getActivity(), AddSessionFragment.class);
        startActivity(intent);
    }

    public void moveToDrawerActivity(View view) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
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

