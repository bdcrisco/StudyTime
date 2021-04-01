package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerFragment extends Fragment {

    private View rootView;
    public RecyclerFragment(){

        rootView = null;
    }
    SessionList sessionList = SessionList.getInstance();

    private RecyclerView rvSessions;
    private static final String tAG = "MainActivity";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_recycler, container, false);


        }

        init();
        generateData();
        setData();

        return rootView;
    }

    private void init(){
        //localList = SessionList.getInstance().getList();
        rvSessions = rootView.findViewById(R.id.rv_sessions);
    }

    private void generateData(){

    }

    private void setData(){
        rvSessions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSessions.setAdapter(new SessionAdapter(getContext()));
    }


    public void moveToTimer(View view) {
        Intent intent = new Intent(getActivity(), HomeFragment.class);
        startActivity(intent);
    }

    public void moveToCalendar(View view) {
        Intent intent = new Intent(getActivity(), CalendarFragment.class);
        startActivity(intent);
    }
}




