package com.example.StudyTime;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyTime.R;

public class SessionViewHolder extends  RecyclerView.ViewHolder{
    private TextView txtCourseName;
    private TextView txtDate;
    private TextView txtTime;

    public SessionViewHolder(@NonNull View itemView) {
        super(itemView);

        txtDate = itemView.findViewById(R.id.txt_date);
        txtCourseName = itemView.findViewById(R.id.txt_course_name);
        txtTime = itemView.findViewById(R.id.txt_time);
    }

    public void setCourseName(String courseName){
        this.txtCourseName.setText(courseName);
    }
    public void setDate(String date){
        this.txtDate.setText(date);
    }
    public void setTime(String time){
        this.txtTime.setText(time);
    }

}

