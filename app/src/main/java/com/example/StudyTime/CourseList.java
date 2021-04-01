package com.example.StudyTime;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// An eagerly created singleton for the users course list
// This means that it is created at application start up
public class CourseList {
    private static CourseList INSTANCE = new CourseList();

    // other instance variables can be here
    List<Course> courseList = new LinkedList<>();
    FileHelper courseFH;
    Context context;

    private CourseList(){

    }

    public static CourseList getInstance() { return(INSTANCE); }
    public void initialize(Context context) {
        this.context = context;
        courseFH = new FileHelper(this.context, "course_list");
        loadCourses();
    }

    // other instance methods can follow
    public void addCourse(Course addMe) {
        courseList.add(addMe);
        saveCourses();
    }
    public void addCourse(String addMe) {
        courseList.add(new Course(addMe));
        saveCourses();
    }
    public void addCourse(List<Course> addMe) {
        courseList.addAll(addMe);
        saveCourses();
    }

    public void saveCourses() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String courseListJson = gson.toJson(courseList);

        courseFH.createFile();
        courseFH.writeToFile(courseListJson);
    }

    public void loadCourses() {
        courseFH.createFile();
        Gson gson = new Gson();
        if (courseFH.fileExists()) {
            courseList = gson.fromJson(courseFH.readFromFile(), new TypeToken<LinkedList<Course>>() {
            }.getType());
        }
    }

    public List<Course> getList() {
        return courseList;
    }

    public List<String> getStringList() {
        List<String> stringList = new ArrayList<>();
        for (Course course : courseList) {
            stringList.add(course.getCourseName());
        }

        return stringList;
    }
    private void sort() {
        //TODO
    }
}
