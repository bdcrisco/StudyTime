package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.SimpleDateFormat;
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
        calendarView = (com.applandeo.materialcalendarview.CalendarView) findViewById(R.id.Calendar);
        myDate = (TextView) findViewById(R.id.myDate);

        myDate.setText(new SimpleDateFormat("M/dd/yyyy").format(calendar.getTime()));

        createEventOnCalendar();

        OnDayClickListener onDayClick = new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

            }

//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                date =  (month + 1) + "/" + dayOfMonth + "/" + year;
//                myDate.setText(date);
//            }
//        });

        };
        getTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                TimePickerDialog timePickerDialog = new TimePickerDialog(CalendarActivity.this, 1, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        Toast.makeText(CalendarActivity.this, ""+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
//                    }
//                }, hour, min, false);
//                timePickerDialog.show();
            }
        });
    }
    public void submitTime(View view){
        TimePicker timePicker = new TimePicker(CalendarActivity.this);
        long returnTime = calendar.getTime().getTime();
        returnTime += (timePicker.getHour() * 3600 + timePicker.getMinute() * 60);
        java.sql.Timestamp sessionStart = new Timestamp(returnTime);
        Session adjSession = new Session();
        adjSession.setStartTime(sessionStart);
        System.out.println(adjSession);

        getTime.getSelectionStart();


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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void moveToRecycler(View view) {
        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);
    }
}