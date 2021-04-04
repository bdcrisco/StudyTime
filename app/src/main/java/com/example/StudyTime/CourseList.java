package com.example.StudyTime;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// An eagerly created singleton for the users course list
// This means that it is created at application start up
public class CourseList {
    private static CourseList INSTANCE = new CourseList();

    // other instance variables can be here
    Map<String, Course> courseMap = new HashMap<>();
    FileHelper courseFH;
    Context context;

//    int size;
//
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;

    private CourseList(){}

    public static CourseList getInstance() { return(INSTANCE); }
    public void initialize(Context context) {
        this.context = context;
//        sharedPreferences = context.getSharedPreferences(context.getString(R.string.course_count), Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();

//        size = sharedPreferences.getInt(context.getString(R.string.course_count), 0);
//        if (size == 0) {
//            courseMap.put("Default", new Course("Select your course:"));
//            size += 1;
//            editor.putInt(context.getString(R.string.course_count), size);
//        }

        courseFH = new FileHelper(this.context, "course_list");
        loadCourses();
    }

    public void clear() {
        courseFH.createFile();
        courseFH.writeToFile("");
    }

    // other instance methods can follow
    public void addCourse(Course addMe) {
        courseMap.put(addMe.getCourseName(), addMe);
        saveCourses();
    }
    public void addCourse(String addMe) {
        courseMap.put(addMe, new Course(addMe));
        saveCourses();
    }
//    public void addCourse(List<Course> addMe) {
//        courseSet.addAll(addMe);
//        saveCourses();
//    }

    public void saveCourses() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String courseListJson = gson.toJson(courseMap);

        courseFH.createFile();
        courseFH.writeToFile(courseListJson);
    }

    public void loadCourses() {
        courseFH.createFile();
        Gson gson = new Gson();
        if (courseFH.fileExists()) {
            courseMap = gson.fromJson(courseFH.readFromFile(), new TypeToken<Map<String, Course>>(){
            }.getType());
        }
    }

    public Map<String, Course> getMap() {
        return courseMap;
    }

    public List<String> getStringList() {
        List<String> stringList = new ArrayList<>();
        for (Course value: courseMap.values()) {
            stringList.add(value.getCourseName());
        }

        return stringList;
    }
}
