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

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CalendarFragment extends Fragment {

    //from CatFragment---
    private View rootView;
        public CalendarFragment(){
        rootView = null;
    }
    //----
    com.applandeo.materialcalendarview.CalendarView calendarView;
    List<EventDay> events;

    SessionList sessionList = SessionList.getInstance();

    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    TextView myDate;
    String date;

    //from CatFragment---

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (rootView ==null){
            Log.d("Fragment Demo", "Creating Layout for Calendar Fragment");

            /*Display (inflate) the layout xml for this fragment */
            rootView = inflater.inflate(R.layout.activity_calendar, container, false);

            calendarView = (com.applandeo.materialcalendarview.CalendarView) rootView.findViewById(R.id.Calendar);
            myDate = (TextView) rootView.findViewById(R.id.dayLabel);

            myDate.setText(new SimpleDateFormat("M/dd/yyyy").format(calendar.getTime()));

        // Events for the calendar view
        events = new ArrayList<>();
        events.add(new EventDay(calendar, R.drawable.event_exists));
        calendarView.setEvents(events);
            createEventOnCalendar();

        // Date information for the TextView (also tells us what day is selected, will need later)
        myDate.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(calendar.getTime()));
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                myDate.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(clickedDayCalendar.getTimeInMillis()));
            }
        });
            OnDayClickListener onDayClick = new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {

                }

//

            };
        }
        return rootView;
    }


    private void createEventOnCalendar() {

        System.out.println("The Current Date is:" + calendar.getTime());
        System.out.println("Current Calendar's Year: " + calendar.get(Calendar.YEAR));
        System.out.println("Current Calendar's Day: " + calendar.get(Calendar.DATE));
        System.out.println("Current MINUTE: " + calendar.get(Calendar.MINUTE));
        System.out.println("Current SECOND: " + calendar.get(Calendar.SECOND));
//        myDate.setText((CharSequence) calendar.getTime());
    }

    public void moveToTimer(View view) {
        Intent intent = new Intent(getActivity(), HomeFragment.class);
        startActivity(intent);
    }

    public void moveToRecycler(View view) {
        Intent intent = new Intent(getActivity(), RecyclerFragment.class);
        startActivity(intent);
    }


}