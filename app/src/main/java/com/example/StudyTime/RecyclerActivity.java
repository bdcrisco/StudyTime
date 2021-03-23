package com.example.StudyTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;


public class RecyclerActivity extends AppCompatActivity {
    private ArrayList<Sessions> sessionArrayList;
    private RecyclerView rvSessions;
    private static final String tAG = "MainActivity";
//    private FileHelper fileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
//        fileHelper = new FileHelper("sessions");
        init();
        generateData();
        setData();
    }
    private void init(){
        sessionArrayList = new ArrayList<>();
        rvSessions = findViewById(R.id.rv_sessions);
    }

    private void generateData(){
        sessionArrayList.add(new Sessions("BIO101", "03/10/2021", "1:45"));
        sessionArrayList.add(new Sessions("CS246", "03/11/2021", "2:15"));
        sessionArrayList.add(new Sessions("CS246", "03/13/2021", "1:22"));
        sessionArrayList.add(new Sessions("BIO101", "03/13/2021", "1:15"));
        sessionArrayList.add(new Sessions("CS246", "03/15/2021", "1:55"));
        sessionArrayList.add(new Sessions("CS246", "03/16/2021", "1:15"));
        sessionArrayList.add(new Sessions("BIO101", "03/17/2021", "0:48"));
        sessionArrayList.add(new Sessions("BIO101", "03/18/2021", "1:20"));
        sessionArrayList.add(new Sessions("CS246", "03/20/2021", "1:00"));
        sessionArrayList.add(new Sessions("BIO101", "03/21/2021", "2:15"));
        sessionArrayList.add(new Sessions("CS246", "03/22/2021", "2:18"));
        sessionArrayList.add(new Sessions("CS246", "03/22/2021", "1:10"));
        sessionArrayList.add(new Sessions("BIO101", "03/23/2021", "1:55"));
        sessionArrayList.add(new Sessions("BIO101", "03/24/2021", "1:35"));
    }

    private void setData(){
        rvSessions.setLayoutManager(new LinearLayoutManager(this));
        rvSessions.setAdapter(new SessionAdapter(this, sessionArrayList));
    }


    public void moveToTimer(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void moveToCalendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}




