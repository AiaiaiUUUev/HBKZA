package com.example.d.healthbook.CalendarBekarysa.Views;

import android.view.View;

import com.example.d.healthbook.CalendarBekarysa.Models.CalendarModel;

import java.util.Calendar;


/**
 * Created by User on 08.07.2017.
 */

public interface ICalendarView {
    void onDateSelected(CalendarModel out);
    void onDateSelected(Calendar out);


}
