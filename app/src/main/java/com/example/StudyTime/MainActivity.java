package com.example.StudyTime;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import static android.os.SystemClock.elapsedRealtime;

public class MainActivity extends AppCompatActivity {

    SessionList sessionList;

    Session newSession;

    private Chronometer simpleTimer;
    private boolean running;
    private Chronometer simpleTimer;
    private View rootView;
    private long pauseOffset; // use to calculate time paused
    private Spinner spinnerCourse;
    FileHelper fileHelper;

    ViewPager2 viewPager;

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    NavigationView navigationView;

    Button buttonStart;
    Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem){
                        drawer.closeDrawers();
                        loadFragment(menuItem.getItemId());
                                return true;
                    }
                });



        viewPager = findViewById(R.id.view_pager);
        PagerAdapter demoPagerAdapter = new PagerAdapter(this);
        viewPager.setAdapter(demoPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

        buttonStop = findViewById(R.id.stopButton);

        sessionList = SessionList.getInstance();
        sessionList.initialize(this.getApplicationContext());

        newSession = new Session();
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

        // initiate views
        simpleTimer = findViewById(R.id.simpleTimer);
        simpleTimer.setFormat("Time: %s");
        simpleTimer.setBase(elapsedRealtime());
        addCourseSpinner(); // courses spinner
        addListenerOnButton();




    private void loadFragment(int menuID){
        if (menuID == R.id.home_constraint) {
            viewPager.setCurrentItem(0);
            Log.d("FragmentDemo","Loading Home Fragment");
        } else if (menuID == R.id.add_session) {
            viewPager.setCurrentItem(1);
            Log.d("FragmentDemo","Loading Add Session Fragment");
        } else if (menuID == R.id.calendar_view) {
            viewPager.setCurrentItem(2);
            Log.d("FragmentDemo","Loading Calendar Fragment");
        }
         else if (menuID == R.id.settings_view){
            viewPager.setCurrentItem(3);
        }
        else if (menuID == R.id.recycler_view){
            viewPager.setCurrentItem(4);
        }
    }

    private class PagerAdapter extends FragmentStateAdapter {
        public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = null;
            /* Create the fragment based on the position provided. */
            if (position == 0) {
                fragment = new HomeFragment();
            } else if (position == 1) {
                fragment = new AddSessionFragment();
            } else if (position == 2) {
                fragment = new CalendarFragment();
            }
            else if (position == 3){
                fragment = new SettingsFragment();
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
            else if (position == 4){
                fragment = new RecyclerFragment();
            }
            return fragment;
        }
        @Override
        public int getItemCount() {
            /* The number of fragments managed by the adapter */
            return 5;
        }
    }

    public void openDrawer(View view) {
        drawer.openDrawer(GravityCompat.START);
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
        List<String> courses = new ArrayList<String>();
        courses.add("Select your course:");
        courses.add("CS246");
        courses.add("BIO101");
        courses.add("REL275");

        //create a adapter for the spinner and set that to the spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapter);
    }
    public void addListenerOnButton() {

//        spinnerCourse = (Spinner) findViewById(R.id.spinner1);
        Button btnChooseCourse = (Button) findViewById(R.id.btnSubmit);

        btnChooseCourse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "You Chose : " +
                                "\nCourse : "+ String.valueOf(spinnerCourse.getSelectedItem()) +
                                "\nTime : ",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}

