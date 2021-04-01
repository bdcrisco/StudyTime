package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AddSessionFragment extends Fragment {
    //From Macbeth's Bird Fragment------
    View rootView;
    public AddSessionFragment(){
        rootView = null;
    }
    //---end BirdFragment

    CalendarView calendarView;

    List<EventDay> events;

    EventDay currentDay;
    SessionList sessionList = SessionList.getInstance();
    private Spinner spinnerCourse, spinnerTopic;

   //From BirdFragment----
    //@Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (rootView ==null){
            Log.d("FragmentDemo", "Creating Layout for AddSession Fragment");
            /*Display(inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.activity_add_session, container, false);

            events = new ArrayList<>();
            calendarView = (CalendarView) rootView.findViewById(R.id.addSessionCalendar);
            Calendar calendar = Calendar.getInstance();

            events.add(new EventDay(calendar, R.drawable.event_exists));
            calendarView.setEvents(events);

//            OnDayClickListener onDayClick = new OnDayClickListener() {
//                @Override
//                public void onDayClick(EventDay eventDay) {
//                    currentDay = eventDay;
//                }
//            };

            TextView currentDate = (TextView) rootView.findViewById(R.id.currentDate);
            currentDate.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(calendar.getTime()));

            addCourseSpinner();
//    addListenerOnButton();
            addListenerOnSpinnerItemSelection();

        }
        return rootView;
    }



    public void SaveSession() {
        Session session = new Session();
        sessionList.addSession(session);
    }
    public void addCourseSpinner() {

        spinnerCourse = (Spinner) rootView.findViewById(R.id.spinnerCourse);
        List<String> courses = new ArrayList<String>();
        courses.add("Select your course:");
        courses.add("CS246");
        courses.add("BIO101");
        courses.add("REL275");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapter);
    }
    public void addListenerOnSpinnerItemSelection() {
//        spinnerCourse = (Spinner) findViewById(R.id.spinner1);
        spinnerCourse.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}