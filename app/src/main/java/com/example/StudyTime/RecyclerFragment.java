package com.example.StudyTime;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*A fragment for the RecyclerView functionality to be accessed by the drawer.
 *    Allows the user to view saved sessions in a sorted list*/
public class RecyclerFragment extends Fragment {

    private View rootView;
    public RecyclerFragment(){ rootView = null; }
    SessionList sessionList = SessionList.getInstance();

    private RecyclerView rvSessions;

    //inflates(displays) the activity_recycler layout from xml and instantiates init() and setData()
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_recycler, container, false);
        }

        init();
        setData();

        return rootView;
    }

    //ensures that the list is sorted and updated when moving from another fragment
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




