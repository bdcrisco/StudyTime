package com.example.StudyTime;

import android.os.SystemClock;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// add a dateview return function

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

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
//    public void setCourse(String course) {
//        this.course = new Course(course);
//    }
    public void pause() {
            pauseTime.add(new Timestamp(SystemClock.elapsedRealtime()));
            isPaused = !isPaused;
    }
}
