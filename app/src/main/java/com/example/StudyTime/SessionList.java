package com.example.StudyTime;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;


// import static com.example.team11.FileHelper.createFile;
// import static com.example.team11.FileHelper.writeToFile;



// An eagerly created singleton for the users session list
// This means that it is created at application start up
public class SessionList {
    private static SessionList INSTANCE = new SessionList();

    // other instance variables can be here
    List<Session> sessionList = new LinkedList<>();

    private SessionList() {};

    public static SessionList getInstance() {
        return(INSTANCE);
    }

    // other instance methods can follow
    public void addSession(Session addMe) {
        sessionList.add(addMe);
    }
    public void addSession(List<Session> addMe) {
        sessionList.addAll(addMe);
    }

    public void saveSessions() {
        Gson gson = new Gson();
        String sessionListJson = gson.toJson(sessionList);

//        createFile("temp.txt");
//        writeToFile("temp.txt", sessionListJson);
    }

    public List<Session> getList() {
        return sessionList;
    }
}
