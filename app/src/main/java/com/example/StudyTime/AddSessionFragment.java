package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.List;

/*A fragment for the addSession functionality to be accessed by the drawer.
 *    Allows the user to add sessions to their personal list*/
public class AddSessionFragment extends Fragment {
    View rootView;
    public AddSessionFragment(){
        rootView = null;
    }

    CalendarView calendarView;

    List<EventDay> events = new ArrayList<>();

    SessionList sessionList = SessionList.getInstance();
    CourseList courseList = CourseList.getInstance();

    Button buttonSaveSession;

    private Session newSession;

    private long selectedDate;
    private TimePicker timePicker;
    private Spinner spinnerCourse;
    private Spinner spinnerElapsedTime;


    // onCreateView initializes the fragment's view, and returns it to it's super
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (rootView ==null){
            Log.d("FragmentDemo", "Creating Layout for AddSession Fragment");
            /*Display(inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.activity_add_session, container, false);

            setViews();

            setCalendarEvents();
            setCourseSpinner();
            setElapsedTimeSpinner();
            setSaveStudyTime();

            // background thread runnable
            getActivity().runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    // listener for when a day on the calendar is selected
                    calendarView.setOnDayClickListener(new OnDayClickListener() {
                        @Override
                        public void onDayClick(EventDay eventDay) {
                            courseList.initialize(rootView.getContext().getApplicationContext());
                            selectedDate = eventDay.getCalendar().getTimeInMillis();
                            setCalendarEvents();
                        }
                    });
                }
            });
        }
        return rootView;
    }

    // onResume that makes sure the CalendarEvents and Spinner are synced with the application
    @Override
    public void onResume() {
        super.onResume();
        setCalendarEvents();
        setCourseSpinner();
    }

    // sets all of the interactables
    private void setViews() {
        calendarView = (CalendarView) rootView.findViewById(R.id.addSessionCalendar);
        timePicker = rootView.findViewById(R.id.timePicker);
        spinnerCourse = rootView.findViewById(R.id.spinnerCourse);
        spinnerElapsedTime = rootView.findViewById(R.id.spinnerElapsedTime);
    }

    // sets the course spinner data
    public void setCourseSpinner() {
        List<String> courses = courseList.getStringList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapter);
    }

    // sets the elapsed time spinner
    public void setElapsedTimeSpinner() {
        ArrayList<String> elapsedTime = new ArrayList<>();
        elapsedTime.add("30 Minutes");
        elapsedTime.add("1 Hour");
        elapsedTime.add("1.5 Hours");
        elapsedTime.add("2 Hours");
        elapsedTime.add("2.5 Hours");
        elapsedTime.add("3 Hours");
        ArrayAdapter<String> elapsedTimeAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, elapsedTime);
        elapsedTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerElapsedTime.setAdapter(elapsedTimeAdapter);
    }
    // gets the time for each spinner item in milliseconds
    private long getElapsedTimeSpinnerTime() {
        long conversion = 60000;
        switch((int) spinnerElapsedTime.getSelectedItemId()) {
            case 0:
                return 30 * conversion;
            case 1:
                return 60 * conversion;
            case 2:
                return 90 * conversion;
            case 3:
                return 120 * conversion;
            case 4:
                return 150 * conversion;
            case 5:
                return 180 * conversion;
        }

        return 0;
    }

    // sets the study save button
    public void setSaveStudyTime() {
        buttonSaveSession =  (Button) rootView.findViewById(R.id.saveSessionButton);
        buttonSaveSession.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                newSession = calculateNewSession();
                sessionList.addSession(newSession);
            }
        });
    }

    // creates a new session based on the fragment's interactables
    private Session calculateNewSession() {
        Session newSession = new Session();
        newSession.setCourse(new Course((String) spinnerCourse.getSelectedItem()));
        newSession.setStartTime(selectedDate + timePicker.getCurrentHour() * 3600000 + timePicker.getCurrentMinute() * 60000);
        newSession.setEndTime(newSession.getStartTime() + getElapsedTimeSpinnerTime());

        return newSession;
    }

    // sets the calendar events so that a drawable appears indicating a session for a given day
    private void setCalendarEvents() {
        for (Session session : sessionList.getList()) {
            events.add(session.getEventDay());
        }
        calendarView.setEvents(events);
    }
}