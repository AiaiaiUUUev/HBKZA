package com.example.d.healthbook.CalendarBekarysa.Calendar_UI;

import com.example.d.healthbook.CalendarBekarysa.Models.CalendarModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



/**
 * Created by User on 07.07.2017.
 */

public class CalendarHelper {
    int firstDayOfMonth=1;
    int lastDayOfMonth;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    public List<CalendarModel> generateMonth(Calendar cal){
        List<CalendarModel> retVal = new ArrayList<>();
        lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar firstDayOFMonth =Calendar.getInstance();
        firstDayOFMonth.set(cal.get(Calendar.YEAR) , cal.get(Calendar.MONTH) , 1);

        int firstWeekNum = firstDayOFMonth.get(Calendar.DAY_OF_WEEK)-1;
        firstWeekNum = firstWeekNum==0 ? 7 : firstWeekNum;

        Calendar lastDateOfMonth = Calendar.getInstance();
        lastDateOfMonth.set(cal.get(Calendar.YEAR) , cal.get(Calendar.MONTH) , cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        int lastWeekNum = lastDateOfMonth.get(Calendar.DAY_OF_WEEK)-1;
        lastWeekNum = lastWeekNum==0 ? 7 : lastWeekNum;

        int addToFirstWeekValue = 6-(7 - firstWeekNum);
        int n = addToFirstWeekValue + lastDayOfMonth ;
        for(int i=-addToFirstWeekValue ; i <=n ; i++ ){
            if(lastDayOfMonth>i) {
                if (i < 0) {
                    retVal.add(new CalendarModel(0,""));

                }
                else {
                    if(i+1>1)
                        firstDayOFMonth.add(Calendar.DATE,1);
                    retVal.add(new CalendarModel(i + 1, format1.format(firstDayOFMonth.getTime())));
                }
            }
        }
        return retVal;
    }

}
