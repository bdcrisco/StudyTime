package com.example.StudyTime;

import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/*A class containing session information*/
public class Session implements Comparable<Session>{
    private long startTime;
    private long endTime;
    private List<Long> pauseTime = new ArrayList<>();
    Course course;

    private static boolean isPaused = false;

    // Constructors
    public Session() {
        startTime = Calendar.getInstance().getTimeInMillis();
        course = new Course("Default");
    }
    public Session(Course course) {
        startTime = Calendar.getInstance().getTimeInMillis();
        this.course = course;
    }
    public Session(long startTime, long endTime, Course course) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
    }

    // Getters
    public long getStartTime() {
        return startTime;
    }
    public long getEndTime() {
        return endTime;
    }
    public Course getCourse() {
        return course;
    }
    public List<Long> getPauseTime() {
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
        return timeFrame + " : " + getElapsedTime();
    }

    // Setters
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(long endTime) { this.endTime = endTime; }
    public void setCourse(Course course) {
        this.course = course;
    }
    public void setCourse(String course) {
        this.course = new Course(course);
    }

    // functions that start, stop, and pause a session
    public void start() { startTime = Calendar.getInstance().getTimeInMillis(); }
    public void stop() { endTime = Calendar.getInstance().getTimeInMillis(); }
    public void pause() {
            pauseTime.add(Calendar.getInstance().getTimeInMillis());
            isPaused = !isPaused;
    }

    // returns a string in an elapsed time format
    private String getElapsedTime() {
        long eTime = endTime - startTime;
        int hours = (int) (eTime / 3600000);
        int minutes = (int) (eTime / 60000) % 60;
        int seconds = (int) (eTime / 1000) % 60;

        // formats the string in the way the user likes
        return String.format("%d:%02d hrs", hours, minutes);
    }

    // returns a session as an EventDay object
    public EventDay getEventDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        return new EventDay(calendar, R.drawable.event_exists);
    }

    // comparison logic for list sorting (required for collections.sort())
    @Override
    public int compareTo(Session o) {
        if (o.getStartTime() == this.getStartTime() && o.getEndTime() == this.getEndTime()){
            return 0;
        }
        else if (o.getStartTime() > this.getStartTime()){
            return -1;
        }
        else{
            return 1;
        }
    }
}
