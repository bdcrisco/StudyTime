package com.example.StudyTime;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

// spinner listener for addSession
public class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(),
                "Selected : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    // Auto-generated method stub
    @Override
    public void onNothingSelected(AdapterView<?> arg0) { }

}

