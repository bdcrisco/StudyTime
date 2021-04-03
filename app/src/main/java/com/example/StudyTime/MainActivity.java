package com.example.StudyTime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;


import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    CourseList courseList = CourseList.getInstance();
    SessionList sessionList = SessionList.getInstance();

    ViewPager2 viewPager;

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseList.initialize(this.getApplicationContext());
        sessionList.initialize(this.getApplicationContext());

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawer.closeDrawers();
                loadFragment(menuItem.getItemId());
                return true;
            }
        });

        viewPager = findViewById(R.id.view_pager);
        PagerAdapter demoPagerAdapter = new PagerAdapter(this);
        viewPager.setAdapter(demoPagerAdapter);
    }

/*onCreateOptionsMenu
specifies the menu options
inflates the main drawer layout
 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    /*
    loadFragment
    loads each fragment layout
    depending on the clicked menuID
     */
    private void loadFragment(int menuID) {
        if (menuID == R.id.home_constraint) {
            viewPager.setCurrentItem(0);
            Log.d("FragmentDemo", "Loading Home Fragment");
        } else if (menuID == R.id.add_session) {
            viewPager.setCurrentItem(1);
            Log.d("FragmentDemo", "Loading Add Session Fragment");
        } else if (menuID == R.id.calendar_view) {
            viewPager.setCurrentItem(2);
            Log.d("FragmentDemo", "Loading Calendar Fragment");
        } else if (menuID == R.id.recycler_view) {
            viewPager.setCurrentItem(3);
        } else if (menuID == R.id.add_course_view) {
            viewPager.setCurrentItem(4);
        }
    }
/*openDrawer
onclick function to open menu
drawer from button
 */
    public void openDrawer(View view) {
        drawer.openDrawer(GravityCompat.START);
    }
/*PagerAdapter class
Base class providing the adapter
to populate pages inside our ViewPager2
extends FragmentStateAdapter
 */
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
            } else if (position == 3) {
                fragment = new RecyclerFragment();
            } else if (position == 4) {
                fragment = new AddCourseFragment();
            }
            return fragment;
        }

        @Override
        public int getItemCount() {
            /* The number of fragments managed by the adapter */
            return 5;
        }
    }
}