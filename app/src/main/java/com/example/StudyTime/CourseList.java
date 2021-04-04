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

/*An eagerly created singleton for the users course list
 *   This means that it is created at application start up
 *   Noteworthy: The CourseList is implemented as a map for item uniqueness */
public class CourseList {
    private static CourseList INSTANCE = new CourseList();

    // other instance variables can be here
    Map<String, Course> courseMap = new HashMap<>();
    FileHelper courseFH;
    Context context;

    // default constructor, used to set up the singleton at runtime
    private CourseList(){}

    // singleton method, used to get the single CourseList
    public static CourseList getInstance() { return(INSTANCE); }
    // An initialize method used to bring in a context, set a FileHelper and load the user's courses
    public void initialize(Context context) {
        this.context = context;
        courseFH = new FileHelper(this.context, "course_list");
        loadCourses();
    }

    // Used to clear a user's CourseList
    public void clear() {
        courseFH.createFile();
        courseFH.writeToFile("");
        courseFH.readFromFile();
    }

    // two ways to add courses, either by taking in a course or a string
    public void addCourse(Course addMe) {
        courseMap.put(addMe.getCourseName(), addMe);
        saveCourses();
    }
    public void addCourse(String addMe) {
        courseMap.put(addMe, new Course(addMe));
        saveCourses();
    }

    // works with the FileHelper to save the user's courses
    public void saveCourses() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String courseListJson = gson.toJson(courseMap);

        courseFH.createFile();
        courseFH.writeToFile(courseListJson);
    }

    // works with the FileHelper to load the user's courses
    public void loadCourses() {
        courseFH.createFile();
        Gson gson = new Gson();
        if (courseFH.fileExists()) {
            courseMap = gson.fromJson(courseFH.readFromFile(), new TypeToken<Map<String, Course>>(){
            }.getType());
        }
    }

    // returns the underlying map of the CourseList
    public Map<String, Course> getMap() {
        return courseMap;
    }

    // returns the CourseList as a string
    public List<String> getStringList() {
        List<String> stringList = new ArrayList<>();
        for (Course value: courseMap.values()) {
            stringList.add(value.getCourseName());
        }

        return stringList;
    }
}
