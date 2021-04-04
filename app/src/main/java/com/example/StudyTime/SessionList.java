package com.example.StudyTime;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* An eagerly created singleton for the users session list
 *    This means that it is created at application start up */
public class SessionList {
    private static SessionList INSTANCE = new SessionList();

    // other instance variables can be here
    long dayLength = 86400000;
    List<Session> sessionList = new LinkedList<>();
    FileHelper sessionFH;
    Context context;

    // default constructor, used to set up the singleton at runtime
    private SessionList(){}

    // singleton method, used to get the single SessionList
    public static SessionList getInstance() {
        return(INSTANCE);
    }
    // An initialize method used to bring in a context, set a FileHelper and load the user's sessions
    public void initialize(Context context) {
        this.context = context;
        sessionFH = new FileHelper(this.context, "session_list");
        loadSessions();
        sort();
    }

    // Used to clear a user's SessionList
    public void clear() {
        sessionFH.createFile();
        sessionFH.writeToFile("");
        sessionFH.readFromFile();
    }

    // two ways to add sessions, either by taking in a session, or by taking in a list of them
    public void addSession(Session addMe) {
        sessionList.add(addMe);
        saveSessions();
    }
    public void addSession(List<Session> addMe) {
        sessionList.addAll(addMe);
    }

    // works with the FileHelper to save the user's sessions
    public void saveSessions() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String sessionListJson = gson.toJson(sessionList);

        sessionFH.createFile();
        sessionFH.writeToFile(sessionListJson);
    }

    // works with the FileHelper to load the user's sessions
    public void loadSessions() {
        sessionFH.createFile();
        Gson gson = new Gson();
        if (sessionFH.fileExists()) {
            sessionList = gson.fromJson(sessionFH.readFromFile(), new TypeToken<LinkedList<Session>>(){
            }.getType());
        }
    }

    // Gets the SessionList as a list
    public List<Session> getList() {
        return sessionList;
    }

    // Gets the Sessions for a single day
    public List<Session> getDayList(long startTime) {
        List<Session> sessions = new LinkedList<>();
        for (Session session : sessionList) {
            if (session.getStartTime() > startTime && session.getEndTime() < startTime + dayLength) {
                sessions.add(session);
            }
        }
        return sessions;
    }

    // Sorts the sessionList by date ascending
    private void sort() {
        Collections.sort(sessionList, new SessionComparator());
    }

/* WIP sorting on background thread                                         */
//    private Runnable sort() {
//        Runnable sessionSort = new Runnable() {
//            @Override
//            public void run() {
//                Collections.sort(sessionList, new SessionComparator());
//            }
//        };
//        return sessionSort;
//    }

    // need by Collections Sort to know how to sort a SessionList
    public class SessionComparator implements Comparator<Session> {
        @Override
        public int compare(Session session1, Session session2) {
            return session1.compareTo(session2);
        }
    }
}
