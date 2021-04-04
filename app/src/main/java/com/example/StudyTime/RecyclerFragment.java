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

    /*onCreateView
    inflates(displays) the activity_recycler layout from xml
    instantiates init() and setData()
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_recycler, container, false);
        }

        init();
        setData();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

//initialize rvSessions
    private void init(){
        rvSessions = rootView.findViewById(R.id.rv_sessions);
        sessionList.initialize(rootView.getContext().getApplicationContext());
    }


//sends our list data to the adapter
    private void setData(){
        rvSessions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSessions.setAdapter(new SessionAdapter(getContext(),sessionList.getList()));
    }
}




