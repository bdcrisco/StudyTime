package com.example.StudyTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     
        HamburgerFragment hamburgerfragment = new HamburgerFragment("param1", "param2");
    }

    public void moveToCalendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void moveToAddSession(View view) {
        Intent intent = new Intent(this, AddSessionActivity.class);
        startActivity(intent);
    }
}