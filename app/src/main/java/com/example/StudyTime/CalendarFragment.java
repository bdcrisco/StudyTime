package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

/*A fragment for Calendar functionality to be accessed by the drawer.
 *    Brings up a calendar with the user's sessions
 *    When you click on a date with sessions, it shows them as a list  */
public class CalendarFragment extends Fragment {

    private View rootView;

    public CalendarFragment() {
        rootView = null;
    }

    SessionList sessionList = SessionList.getInstance();

    CalendarView calendarView;
    List<EventDay> events;
    SessionAdapter adapter;

    long currentDate;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            Log.d("Fragment Demo", "Creating Layout for Calendar Fragment");
            /*Display (inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.activity_calendar, container, false);

            events = new ArrayList<>();
            calendarView = rootView.findViewById(R.id.Calendar);
            adapter = new SessionAdapter(getContext(), sessionList.getDayList(currentDate));

            // Events for the calendar view
            setCalendarEvents();
            setRecyclerView();

            // background thread runnable
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // listener for when a day on the calendar is selected
                    calendarView.setOnDayClickListener(new OnDayClickListener() {
                        @Override
                        public void onDayClick(EventDay eventDay) {
                            currentDate = eventDay.getCalendar().getTimeInMillis();
                            adapter.update(sessionList.getDayList(currentDate));
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        }
        return rootView;
    }

    // onResume that updates the events on the calendar when this fragment is selected
    @Override
    public void onResume() {
        super.onResume();
        setCalendarEvents();
    }

    // sets the calendar to display events
    private void setCalendarEvents() {
        for (Session session : sessionList.getList()) {
            events.add(session.getEventDay());
        }
        calendarView.setEvents(events);
    }

    // sets the recyclerView to show the events for the selected calendar day
    private void setRecyclerView(){
        RecyclerView rvSessions = rootView.findViewById(R.id.rv_sessions);
        rvSessions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSessions.setAdapter(adapter);
    }
}