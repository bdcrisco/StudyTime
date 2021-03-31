package com.example.StudyTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerActivity extends AppCompatActivity {
    SessionList sessionList = SessionList.getInstance();
    //private List<Session> localList;
    private RecyclerView rvSessions;
    private static final String tAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        init();
        generateData();
        setData();
    }
    private void init(){
        //localList = SessionList.getInstance().getList();
        rvSessions = findViewById(R.id.rv_sessions);
    }

    private void generateData(){

    }

    private void setData(){
        rvSessions.setLayoutManager(new LinearLayoutManager(this));
        rvSessions.setAdapter(new SessionAdapter(this));
    }


    public void moveToTimer(View view) {
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
    }

    public void moveToCalendar(View view) {
        Intent intent = new Intent(this, CalendarFragment.class);
        startActivity(intent);
    }
}




