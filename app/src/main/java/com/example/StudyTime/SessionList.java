package com.example.StudyTime;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
// An eagerly created singleton for the users session list
// This means that it is created at application start up
public class SessionList {
    private static SessionList INSTANCE = new SessionList();

    // other instance variables can be here
    long dayLength = 86400000;
    List<Session> sessionList = new LinkedList<>();
    FileHelper sessionFH;
    Context context;

    private SessionList(){}

    public static SessionList getInstance() {
        return(INSTANCE);
    }
    public void initialize(Context context) {
        this.context = context;
        sessionFH = new FileHelper(this.context, "session_list");
        loadSessions();
        sort();
    }

    // other instance methods can follow
    public void addSession(Session addMe) {
        sessionList.add(addMe);
        saveSessions();
    }
    public void addSession(List<Session> addMe) {
        sessionList.addAll(addMe);
    }

    public void saveSessions() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String sessionListJson = gson.toJson(sessionList);

        sessionFH.createFile();
        sessionFH.writeToFile(sessionListJson);
    }

    public void loadSessions() {
        sessionFH.createFile();
        Gson gson = new Gson();
        if (sessionFH.fileExists()) {
            sessionList = gson.fromJson(sessionFH.readFromFile(), new TypeToken<LinkedList<Session>>(){
            }.getType());
        }
    }

    public void clear() {
        sessionFH.createFile();
        sessionFH.writeToFile("");
    }

    public List<Session> getList() {
        return sessionList;
    }

    public List<Session> getDayList(long startTime) {
        List<Session> sessions = new LinkedList<>();
        for (Session session : sessionList) {
            if (session.getStartTime() > startTime && session.getEndTime() < startTime + dayLength) {
                sessions.add(session);
            }
        }
        return sessions;
    }

    private void sort() {
        Collections.sort(sessionList, new SessionComparator());
    }

    public class SessionComparator implements Comparator<Session> {
        @Override
        public int compare(Session session1, Session session2) {
            return session1.compareTo(session2);
        }
    }
}
