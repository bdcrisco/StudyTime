package com.example.StudyTime;

import android.os.Build;
import android.os.SystemClock;

import androidx.annotation.RequiresApi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Timestamp startTime;
    private Timestamp endTime;
    private List<Timestamp> pauseTime = new ArrayList<>();
    Course course;

    private static boolean isPaused = false;

    // Constructors
    public Session() {
        startTime = new Timestamp(SystemClock.elapsedRealtime());
        course = new Course("Default");
    }
    public Session(Course course) {
        startTime = new Timestamp(SystemClock.elapsedRealtime());
        this.course = course;
    }
    public Session(Timestamp startTime, Timestamp endTime, Course course) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
    }

    // Getters
    public Timestamp getStartTime() {
        return startTime;
    }
    public Timestamp getEndTime() {
        return endTime;
    }
    public Course getCourse() {
        return course;
    }
    public List<Timestamp> getPauseTime() {
        return pauseTime;
    }
    public boolean getPause() {
        return isPaused;
    }
    public String getDate() {
        return new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(startTime);
    }
    public String getTime() {
        String timeFrame = new SimpleDateFormat("h:mm a", java.util.Locale.getDefault()).format(startTime)
                + " - " +  new SimpleDateFormat("h:mm a", java.util.Locale.getDefault()).format(endTime);
        long eTime = endTime.getTime() - startTime.getTime();
        int hours = (int) (eTime / 3600);
        int minutes = (int) (eTime % 3600);
        String elTime = String.format("%o:%02ohrs", hours, minutes);

        return timeFrame + " : " + elTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public void setCourse(String course) {
        this.course = new Course(course);
    }
    public void pause() {
            pauseTime.add(new Timestamp(SystemClock.elapsedRealtime()));
            isPaused = !isPaused;
    }
}
