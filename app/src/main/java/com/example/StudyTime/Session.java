package com.example.StudyTime;

import android.os.SystemClock;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
        String timeFrame = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(startTime)
                + " - " +  new SimpleDateFormat("h:mm a", Locale.getDefault()).format(endTime);
        Long eTime = endTime.getTime() - startTime.getTime();

        int hours = (int) (eTime / 3600000);
        int minutes = (int) (eTime / 60000) % 60;
        int seconds = (int) (eTime / 1000) % 60;
        String elTime = String.format("%d:%02d hrs", hours, minutes);


        return timeFrame + " : " + elTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    public Timestamp setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return endTime;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public void setCourse(String course) {
        this.course = new Course(course);
    }

    public void start() {
        startTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }
    public void stop() {
        endTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }
    public void pause() {
            pauseTime.add(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            isPaused = !isPaused;
    }

//created when trying to use setEndTime in MainActivity
//    public Timestamp setEndTime(long elapsedRealtime, Session newSession) {
//    }
}
