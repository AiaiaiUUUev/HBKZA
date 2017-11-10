package com.example.d.healthbook.CalendarPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.d.healthbook.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;

public class CalendarView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        com.prolificinteractive.materialcalendarview.MaterialCalendarView mcv;
        mcv = (MaterialCalendarView) findViewById(R.id.calendarView);
//        mcv.state().edit()
//                .setFirstDayOfWeek(Calendar.WEDNESDAY)
//                .setMinimumDate(CalendarDay.from(2016, 4, 3))
//                .setMaximumDate(CalendarDay.from(2016, 5, 12))
//                .setCalendarDisplayMode(CalendarMode.WEEKS)
//                .commit();
    }
}
