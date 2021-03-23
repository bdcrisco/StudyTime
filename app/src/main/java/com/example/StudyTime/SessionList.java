package com.example.StudyTime;

import com.google.gson.Gson;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
// An eagerly created singleton for the users session list
// This means that it is created at application start up
public class SessionList {
    private static SessionList INSTANCE = new SessionList();

    // other instance variables can be here
    List<Session> sessionList = new LinkedList<>();

    private SessionList(){
        addSession(new Session(new Timestamp(1616461188499L), new Timestamp(1616461188499L), new Course ("BIO101")));
    }

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

//        createFile("sessions.txt");
//        writeToFile("sessions.txt", sessionListJson);
    }

    public List<Session> getList() {
        return sessionList;
    }
}
