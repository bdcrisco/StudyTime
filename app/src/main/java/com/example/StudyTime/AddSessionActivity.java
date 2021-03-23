package com.example.StudyTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddSessionActivity extends AppCompatActivity {
    CalendarView calendarView;
    EventDay currentDay;
    SessionList sessionList = SessionList.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
//        events.add(new EventDay(calendar, R.drawable.sample_three_icons));

        calendarView = (CalendarView) findViewById(R.id.addSessionCalendar);
        calendarView.setEvents(events);

        OnDayClickListener onDayClick = new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                currentDay = eventDay;
            }
        };

        TextView currentDate = (TextView) findViewById(R.id.currentDate);
        currentDate.setText(new SimpleDateFormat("M/dd/yyyy").format(calendar.getTime()));
    }

    public void SaveSession() {
        Session session = new Session();
        sessionList.addSession(session);
    }


    public void moveToTimer(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}