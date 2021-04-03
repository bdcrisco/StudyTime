package com.example.StudyTime;

/*A class containing course information*/
public class Course {
    private String courseName;

    // constructor taking in a courseName
    public Course(String courseName) {
        this.courseName = courseName;
    }

    // get function for a courseName as a string
    public String getCourseName() {
        return courseName;
    }
}
