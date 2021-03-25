package com.example.StudyTime;

import java.util.LinkedList;
import java.util.List;

// An eagerly created singleton for the users course list
// This means that it is created at application start up
public class CourseList {
    private static CourseList INSTANCE = new CourseList();

    // other instance variables can be here
    List<Course> courseList = new LinkedList<>();

    private CourseList(){

    }

    public static CourseList getInstance() { return(INSTANCE); }

    // other instance methods can follow
    public void addCourse(Course addMe) { courseList.add(addMe); }
    public void addCourse(List<Course> addMe) { courseList.addAll(addMe); }

    public void saveCourse() {

    }

    public List<Course> getList() { return courseList; }
}
