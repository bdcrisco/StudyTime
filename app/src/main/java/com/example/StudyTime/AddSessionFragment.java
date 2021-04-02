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
import java.util.Calendar;
import java.util.List;

public class AddSessionFragment extends Fragment {
    View rootView;
    public AddSessionFragment(){
        rootView = null;
    }

    CalendarView calendarView;

    List<EventDay> events = new ArrayList<>();

    SessionList sessionList = SessionList.getInstance();
    CourseList courseList = CourseList.getInstance();
//    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);

    Button buttonSaveSession;

    private Session newSession;

    private long selectedDate;
    private TimePicker timePicker;
    private Spinner spinnerCourse;
    private Spinner spinnerElapsedTime;

   //From BirdFragment----
    //@Override
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

            calendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    Calendar clickedDayCalendar = eventDay.getCalendar();
                    selectedDate = clickedDayCalendar.getTimeInMillis();
                    calculateNewSession();
                    setCalendarEvents();
                }
            });
        }
        return rootView;
    }

    private void setViews() {
        calendarView = (CalendarView) rootView.findViewById(R.id.addSessionCalendar);
        timePicker = rootView.findViewById(R.id.timePicker);
        spinnerCourse = rootView.findViewById(R.id.spinnerCourse);
        spinnerElapsedTime = rootView.findViewById(R.id.spinnerElapsedTime);
    }

    public void setCourseSpinner() {
        List<String> courses = courseList.getStringList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapter);
    }

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

    private Session calculateNewSession() {
        Session newSession = new Session();
        newSession.setCourse(new Course((String) spinnerCourse.getSelectedItem()));
        newSession.setStartTime(selectedDate + timePicker.getCurrentHour() * 3600000 + timePicker.getCurrentMinute() * 60000);
        newSession.setEndTime(newSession.getStartTime() + getElapsedTimeSpinnerTime());

        return newSession;
    }

    private void setCalendarEvents() {
        for (Session session : sessionList.getList()) {
            events.add(session.getEventDay());
        }
        calendarView.setEvents(events);
    }
}