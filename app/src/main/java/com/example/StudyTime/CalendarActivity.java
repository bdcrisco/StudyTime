package com.example.StudyTime;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CalendarActivity extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    CalendarView calendarView;
    TextView myDate;
    String date;
    Button getTime;
    int hour,  min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);
        getTime = findViewById(R.id.timeButton);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        myDate.setText(new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime()));
        createEventOnCalendar();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date =  (month + 1) + "/" + dayOfMonth + "/" + year;
                myDate.setText(date);

            }
        });

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