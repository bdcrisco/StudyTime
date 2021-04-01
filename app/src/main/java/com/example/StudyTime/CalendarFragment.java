package com.example.StudyTime;

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
import java.util.Calendar;
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
    EventDay currentDay;
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
            myDate = (TextView) rootView.findViewById(R.id.myDate);

            myDate.setText(new SimpleDateFormat("M/dd/yyyy").format(calendar.getTime()));

            createEventOnCalendar();

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