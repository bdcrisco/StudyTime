package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddSessionFragment extends Fragment {
    View rootView;
    public AddSessionFragment(){
        rootView = null;
    }

    CalendarView calendarView;

    List<EventDay> events;

    EventDay currentDay;
    SessionList sessionList = SessionList.getInstance();
    CourseList courseList = CourseList.getInstance();
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);

    TextView elapsedTime;

    private TextView dayLabel;
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

            calendarView = (CalendarView) rootView.findViewById(R.id.addSessionCalendar);
            dayLabel = rootView.findViewById(R.id.dayLabel);
            timePicker = rootView.findViewById(R.id.timePicker);
            spinnerCourse = rootView.findViewById(R.id.spinnerCourse);
            spinnerElapsedTime = rootView.findViewById(R.id.spinnerElapsedTime);

            dayLabel.getText();
            timePicker.getBaseline();
            spinnerCourse.getSelectedItem();
            elapsedTime = (TextView) spinnerElapsedTime.getSelectedItem();

            events = new ArrayList<>();

            Calendar calendar = Calendar.getInstance();

            events.add(new EventDay(calendar, R.drawable.event_exists));
            calendarView.setEvents(events);

            dayLabel.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(calendar.getTime()));

            calendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    Calendar clickedDayCalendar = eventDay.getCalendar();
//                    // get first day selected
//                    // Calendar selectedDate = calendarView.getFirstSelectedDate();
                    dayLabel.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(clickedDayCalendar.getTimeInMillis()));
//
                }
            });

            addCourseSpinner();
            addElapsedTimeSpinner();
        }
        return rootView;
    }

    public void addCourseSpinner() {
        List<String> courses = courseList.getStringList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapter);
    }

    public void addElapsedTimeSpinner() {
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
}