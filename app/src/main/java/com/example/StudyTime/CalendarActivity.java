package com.example.StudyTime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.Calendar;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CalendarActivity extends AppCompatActivity {
    com.applandeo.materialcalendarview.CalendarView calendarView;
    EventDay currentDay;
    SessionList sessionList = SessionList.getInstance();

    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    TextView myDate;
    String date;
    Button getTime;
    int hour,  min;
//    declare the spinner
//    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.Calendar);
        myDate = findViewById(R.id.dayLabel);

//        myDate.setText(new SimpleDateFormat("M/dd/yyyy").format(calendar.getTime()));


        OnDayClickListener onDayClick = new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
            Calendar clickedDayCalendar = eventDay.getCalendar();
            long timeStop = clickedDayCalendar.getTimeInMillis();
            myDate.setText(Math.toIntExact((long) timeStop));
            }
        };
    }

//    private OnSelectDateListener listener = new OnSelectDateListener() {
//        @Override
//        public void onSelect(List<Calendar> calendars) {
//            DatePickerBuilder builder = new DatePickerBuilder(this, listener)
//                    .pickerType(CalendarView.ONE_DAY_PICKER);
//// .date(Calendar.getInstance()) // Initial date as Calendar object
//            DatePicker datePicker = builder.build();
//            datePicker.show();
//        }
//    };


    public void moveToTimer(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void moveToRecycler(View view) {
        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);
    }
}