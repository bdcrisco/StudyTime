package com.example.StudyTime;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyTime.R;

/* SessionViewHolder class
 *    Describes an item view and metadata about its place within the RecyclerFragment
 *    extends RecyclerView.ViewHolder                                                  */
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

    //sets String courseName for our RecyclerView list
    public void setCourseName(String courseName){
        this.txtCourseName.setText(courseName);
    }
    //sets String date for our RecyclerView list
    public void setDate(String date){
        this.txtDate.setText(date);
    }
    //sets String time for our RecyclerView list
    public void setTime(String time){
        this.txtTime.setText(time);
    }

}

