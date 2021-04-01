package com.example.StudyTime;

import android.content.Context;
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
import android.widget.Chronometer;
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

public class MainActivity extends AppCompatActivity {
    private boolean running;
    private Chronometer simpleTimer;
    private View rootView;
    private long pauseOffset; // use to calculate time paused

    ViewPager2 viewPager;

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    NavigationView navigationView;

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


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }





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

    public void startTimer(View view) {
        // if not running this will start the timer (and subtract the time from stopped/paused)
        if (!running){
            simpleTimer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            simpleTimer.start();
            running = true;
        }
    }

}