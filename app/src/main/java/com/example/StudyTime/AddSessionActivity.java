package com.example.StudyTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AddSessionActivity extends AppCompatActivity {
    CalendarView calendarView;
    List<EventDay> events;
    EventDay currentDay;
    SessionList sessionList = SessionList.getInstance();
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    TextView myDate;
    private Spinner spinnerCourse, spinnerTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        // view linking
        calendarView = findViewById(R.id.addSessionCalendar);
        myDate = findViewById(R.id.dayLabel);

        // Events for the calendar view
        events = new ArrayList<>();
        events.add(new EventDay(calendar, R.drawable.event_exists));
        calendarView.setEvents(events);

        // click handling and Date information for the TextView (also tells us what day is selected, will need later)
        myDate.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(calendar.getTime()));
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                // get first day selected
                // Calendar selectedDate = calendarView.getFirstSelectedDate();
                myDate.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(clickedDayCalendar.getTimeInMillis()));

            }
        });

        OnDayClickListener onDayClick = new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                currentDay = eventDay;
            }
        };
        TextView currentDate = (TextView) findViewById(R.id.dayLabel);
        currentDate.setText(new SimpleDateFormat("M/dd/yyyy", java.util.Locale.getDefault()).format(calendar.getTime()));
//    }
//        currentDate.setText(new SimpleDateFormat("M/dd/yyyy").format(calendar.getTime()));
//
    addCourseSpinner();
//    addListenerOnButton();
    addListenerOnSpinnerItemSelection();
}
    public void addCourseSpinner() {

        spinnerCourse = (Spinner) findViewById(R.id.spinnerCourse);
        List<String> courses = new ArrayList<String>();
        courses.add("Select your course:");
        courses.add("CS246");
        courses.add("BIO101");
        courses.add("REL275");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(dataAdapter);
    }
    public void addListenerOnSpinnerItemSelection() {
//        spinnerCourse = (Spinner) findViewById(R.id.spinner1);
        spinnerCourse.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

//        spinnerCourse = (Spinner) findViewById(R.id.spinner1);
        spinnerTopic = (Spinner) findViewById(R.id.spinnerCourse);
        Button btnChooseCourse = (Button) findViewById(R.id.btnSubmit);

        btnChooseCourse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(AddSessionActivity.this,
                        "You Chose : " +
                                "\nCourse : "+ String.valueOf(spinnerTopic.getSelectedItem()) +
                                "\nTime : ",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void moveToTimer(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

// https://stackoverflow.com/questions/43114891/how-to-take-array-as-an-input-in-android - android
// https://www.tutorialspoint.com/How-to-add-items-to-an-array-in-java-dynamically - java