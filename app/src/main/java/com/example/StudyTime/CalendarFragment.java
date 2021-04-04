package com.example.StudyTime;
//TODO: Merge with CalendarActivity

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ListAdapter;

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

    private RecyclerView rvSessions;
    private View rootView;

    public CalendarFragment() {
        rootView = null;
    }

    com.applandeo.materialcalendarview.CalendarView calendarView;
    List<EventDay> events = new ArrayList<>();
    SessionList sessionList = SessionList.getInstance();

    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    SessionAdapter adapter;

    long currentDate;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            Log.d("Fragment Demo", "Creating Layout for Calendar Fragment");

            /*Display (inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.activity_calendar, container, false);
            calendarView = (com.applandeo.materialcalendarview.CalendarView) rootView.findViewById(R.id.Calendar);
            adapter = new SessionAdapter(getContext(), sessionList.getDayList(currentDate));
            // Events for the calendar view
            setCalendarEvents();
            setData();

            // Date information for the TextView (also tells us what day is selected, will need later)
            calendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    currentDate = eventDay.getCalendar().getTimeInMillis();
                    adapter.update(sessionList.getDayList(currentDate));
                    adapter.notifyDataSetChanged();
                }
            });
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setCalendarEvents();
    }

    private void setCalendarEvents() {
        for (Session session : sessionList.getList()) {
            events.add(session.getEventDay());
        }
        calendarView.setEvents(events);
    }

    private void setData(){
        rvSessions = rootView.findViewById(R.id.rv_sessions);
        rvSessions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSessions.setAdapter(adapter);
    }
}