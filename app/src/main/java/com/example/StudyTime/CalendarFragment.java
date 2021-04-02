package com.example.StudyTime;
//TODO: Merge with CalendarActivity

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CalendarFragment extends Fragment {
    private View rootView;

    public CalendarFragment() {
        rootView = null;
    }

    com.applandeo.materialcalendarview.CalendarView calendarView;
    List<EventDay> events = new ArrayList<>();
    SessionList sessionList = SessionList.getInstance();

    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    TextView myDate;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            Log.d("Fragment Demo", "Creating Layout for Calendar Fragment");

            /*Display (inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.activity_calendar, container, false);

            calendarView = (com.applandeo.materialcalendarview.CalendarView) rootView.findViewById(R.id.Calendar);
            myDate = (TextView) rootView.findViewById(R.id.dayLabel);

            myDate.setText(new SimpleDateFormat("M/dd/yyyy").format(calendar.getTime()));

            // Events for the calendar view
            setCalendarEvents();

            // Date information for the TextView (also tells us what day is selected, will need later)
            myDate.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(calendar.getTime()));
            calendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    Calendar clickedDayCalendar = eventDay.getCalendar();
                    myDate.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(clickedDayCalendar.getTimeInMillis()));
                    long currentDate = calendarView.getFirstSelectedDate().getTimeInMillis();
                    setCalendarEvents();
                }
            });
        }
        return rootView;
    }

    private void setCalendarEvents() {
        for (Session session : sessionList.getList()) {
            events.add(session.getEventDay());
        }
        calendarView.setEvents(events);
    }
}