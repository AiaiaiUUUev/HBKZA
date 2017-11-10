package com.example.d.healthbook.Models;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.greasemonk.timetable.IGridItem;
import com.greasemonk.timetable.TimeRange;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by D on 21.06.2017.
 */

public class EmployeePlanItem implements IGridItem {
    private String employeeName, projectName;
    private String planName = "-";
    private TimeRange timeRange;
    private Context context;

    public EmployeePlanItem() {
    }

    public EmployeePlanItem(Context context, String employeeName, String projectName, Date planStart, Date planEnd) {
        this.employeeName = employeeName;
        this.projectName = projectName;
        this.timeRange = new TimeRange(planStart, planEnd);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static EmployeePlanItem generateSample(Context context, final int num) {

        final String[] projectNames = {"Roof Renovation", "Mall Construction", "Demolition old Hallway"};

        // Generate a date range between now and 30 days
        Random rand = new Random();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_YEAR, 7);
//        int r1 = -rand.nextInt(12);
//        int r2 = rand.nextInt(12);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateandTime = sdf.format(new Date());


        List<String> timesNumber = new ArrayList<>();
        for (int i = 6; i < 21; i++) {
            timesNumber.add(i + ":" + "00");
            timesNumber.add(i + ":" + "30");
        }


        return new EmployeePlanItem(context, timesNumber.get(num),
                projectNames[rand.nextInt(projectNames.length)],
                start.getTime(),
                end.getTime());
    }

    @Nullable
    @Override
    public TimeRange getTimeRange() {
        return timeRange;
    }

    @Override
    public String getName() {
        return projectName;
    }

    @Override
    public String getPersonName() {
        return employeeName;
    }


}