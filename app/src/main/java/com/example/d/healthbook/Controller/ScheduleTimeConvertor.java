package com.example.d.healthbook.Controller;

import android.util.Log;

import com.example.d.healthbook.Models.ResponseCloseOrOpenVisit;
import com.example.d.healthbook.Models.Schedule;
import com.example.d.healthbook.Models.VisitWorkTimeElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.d.healthbook.Activities.ChooseVisitDataActivity.dayofweekTV;

/**
 * Created by D on 23.06.2017.
 */

public class ScheduleTimeConvertor {
    private List<Schedule> raw_schedule;
    List<ResponseCloseOrOpenVisit> closedDates;
    private boolean dayIsBusy = false;
    private boolean dayPassed = false;
    private boolean notPassedDayBecauseNext = false;
    private Calendar setCalendarDay;
    private Calendar cal;
    private Calendar loop_start_time;
    private VisitWorkTimeElement elem;
    private int dayOfweek;
    private SimpleDateFormat sdf2;
    private int getFirstDayOfWeek;
    private boolean clickedNextWeek;
    private int nextWeekDay;
    private boolean rangeHourStart = false;
    private boolean rangeHourEnd = false;
    private boolean rangeHourOpenClose = false;
    private String formattedWeek = "";

    public ScheduleTimeConvertor(List<Schedule> raw_schedule, List<ResponseCloseOrOpenVisit> closedDates) {
        this.raw_schedule = raw_schedule;
        this.closedDates = closedDates;
    }

    public List<VisitWorkTimeElement> mappedList(int day_of_week, String roomID, boolean nextWeekOfMonth) {
//        "start_time": "09:00:00",
//                "finish_time": "17:00:00",


//        "id": "156",
//                "user_id": "37387",
//                "expert_room_id": "304",
//                "expert_id": "754",
//                "start_date": "2017-06-24 13:30:00",
//                "visit_date": "2017-06-24 13:30:00",
//                "finish_date": "2017-06-24 14:00:00",
//                "status_id": "1",
//                "status": "Новый",
//                "status_code": "pending"

        List<VisitWorkTimeElement> return_data = new ArrayList<VisitWorkTimeElement>();
        day_of_week = day_of_week + 1;
        dayOfweek = day_of_week;
        clickedNextWeek = nextWeekOfMonth;

        cal = Calendar.getInstance();
//        Calendar start_of_work = convert_Str_time_to_Calendar(findByDateOfWeek(day_of_week).getStartTime());//"start_time": "09:00:00",
//        Calendar end_of_work = convert_Str_time_to_Calendar(findByDateOfWeek(day_of_week).getFinishTime());//"finish_time": "17:00:00",

        String startOfWork = ((findByDateOfWeek(day_of_week) != null) ? findByDateOfWeek(day_of_week).getStartTime() : "");
        String endOfWork = ((findByDateOfWeek(day_of_week) != null) ? findByDateOfWeek(day_of_week).getFinishTime() : "");

        if (startOfWork.equals("")) {
            rangeHourOpenClose = false;
        }


        loop_start_time = getStartLoopTime();


        checkWhatDayOfWeekEntered();


        for (int i = 6; i < 21; i++) {
            for (int t = 0; t <= 30; t = t + 30) {

                elem = new VisitWorkTimeElement();
                elem.setRoomID(roomID);

                loop_start_time.set(Calendar.HOUR_OF_DAY, i);
                loop_start_time.set(Calendar.MINUTE, t);


                if (!rangeHourStart) {
                    rangeHourStart = checkRangeHour(startOfWork);
                }


                if (rangeHourStart && !rangeHourEnd) {

                    if (!rangeHourEnd) {
                        rangeHourEnd = checkRangeHour(endOfWork);
                    }

                    checkDayOfWeekEqualsOrNot();

                    if (notPassedDayBecauseNext) {
                        Log.d("GETTIME-TRUE_FALSE", "FALSE");
                        elem.setIs_day_passed(false);
                    } else if (!dayPassed && loop_start_time.get(Calendar.HOUR_OF_DAY) > cal.get(Calendar.HOUR_OF_DAY)) {
                        elem.setIs_day_passed(false);
                        Log.d("GETTIME-TRUE_FALSE", "FALSE");
                    } else {
                        elem.setIs_day_passed(true);
                        Log.d("GETTIME-TRUE_FALSE", "TRUE");
                    }


                    elem.setTime_of_day(makeStringTime(i, t));

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    String formatted = sdf.format(loop_start_time.getTime());
                    Log.d("GETTIMELOOP", formatted);
                    elem.setInfoDateAndTimeToVisit(formatted);
                    elem.setRangeWorkTime(true);

                    if (!dayPassed) {
                        checkIfDayIsAlreadyTaken(formatted, elem);
                    }
                    return_data.add(elem);

                } else {

                    elem.setRangeWorkTime(false);
                    elem.setTime_of_day(makeStringTime(i, t));
                    return_data.add(elem);
                }
            }
        }
        return return_data;
    }


    private boolean checkRangeHour(String timeOfWork) {
        boolean rangeOf = false;
        SimpleDateFormat sdfCheckRange = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        String formattedRange = sdfCheckRange.format(loop_start_time.getTime());
        if (formattedRange.equals(timeOfWork)) {
            rangeOf = true;

        }
        return rangeOf;

    }

    private void checkWhatDayOfWeekEntered() {

        Date date = new Date();
        sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        setCalendarDay = Calendar.getInstance();
        setCalendarDay.setTime(date);
        Log.d("CHECK_WEEK", String.valueOf(setCalendarDay.get(Calendar.MONTH)));


//        getFirstDayOfWeek = setCalendarDay.getFirstDayOfWeek() + 1;

        if (dayOfweek == 1) {

            setSelectedCal(Calendar.MONDAY);
        } else if (dayOfweek == 2) {
            setSelectedCal(Calendar.TUESDAY);
        } else if (dayOfweek == 3) {
            setSelectedCal(Calendar.WEDNESDAY);
        } else if (dayOfweek == 4) {
            setSelectedCal(Calendar.THURSDAY);
        } else if (dayOfweek == 5) {
            setSelectedCal(Calendar.FRIDAY);
        } else if (dayOfweek == 6) {
            setSelectedCal(Calendar.SATURDAY);
        }
    }

    private void setSelectedCal(int dayOfweek) {

        //TODO сколько дней всего в месяце
//        Log.d("CHECK_WEEK", String.valueOf(setCalendarDay.getActualMaximum(Calendar.DAY_OF_MONTH)));
//        setCalendarDay.set(Calendar.DAY_OF_MONTH, 3);
        SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.ENGLISH);
        if (clickedNextWeek) {
            int day = 0;
            setCalendarDay.set(Calendar.DAY_OF_WEEK, dayOfweek);
            formattedWeek = sdf.format(setCalendarDay.getTime());
            getFirstDayOfWeek = Integer.parseInt(formattedWeek);

//            day = checkWhatDayOfWeek(dayOfweek - 1);

            setCalendarDay.set(Calendar.DAY_OF_MONTH, getFirstDayOfWeek + 7);
            loop_start_time.set(Calendar.DAY_OF_MONTH, getFirstDayOfWeek + 7);
            String formatted = sdf2.format(loop_start_time.getTime());
            dayofweekTV.setText(formatted);
        } else {
            setCalendarDay.set(Calendar.DAY_OF_WEEK, dayOfweek);
            loop_start_time.set(Calendar.DAY_OF_WEEK, dayOfweek);
            String formatted = sdf2.format(loop_start_time.getTime());
            dayofweekTV.setText(formatted);
        }
    }

//    private int checkWhatDayOfWeek(int dayOfWeek) {
//        if (dayOfWeek == 1) {
//            nextWeekDay = getFirstDayOfWeek + 7;
//        } else if (dayOfWeek == 2) {
//            nextWeekDay = getFirstDayOfWeek + 8;
//        } else if (dayOfWeek == 3) {
//            nextWeekDay = getFirstDayOfWeek + 9;
//        } else if (dayOfWeek == 4) {
//            nextWeekDay = getFirstDayOfWeek + 10;
//        } else if (dayOfWeek == 5) {
//            nextWeekDay = getFirstDayOfWeek + 11;
//        } else if (dayOfWeek == 6) {
//            nextWeekDay = getFirstDayOfWeek + 12;
//        }
//        return nextWeekDay;
//
//    }

    private void checkIfDayIsAlreadyTaken(String cur_loop_cal, VisitWorkTimeElement elem) {
        for (ResponseCloseOrOpenVisit tmp : closedDates) {
            Log.d("GETTIME", tmp.getVisitDate());
            String s = tmp.getVisitDate();
            dayIsBusy = cur_loop_cal.equals(tmp.getVisitDate());
            if (dayIsBusy) {
                elem.setIs_open(false);
                elem.setIs_day_passed(true);
            } else {
                elem.setIs_open(true);
            }
        }

    }

    private void checkDayOfWeekEqualsOrNot() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        int setCaledarDayFormat = Integer.valueOf(sdf.format(setCalendarDay.getTime()));
        int CurrentDayFormat = Integer.valueOf(sdf.format(cal.getTime()));

        Log.d("GETTIME_Day_of_week1", setCaledarDayFormat + "");
        Log.d("GETTIME_Day_of_week2", CurrentDayFormat + "");

        if (setCaledarDayFormat == CurrentDayFormat) {
            Log.d("GETTIME_Day_of_week", "Равные");
            dayPassed = false;

        } else if (setCaledarDayFormat > CurrentDayFormat) {
            notPassedDayBecauseNext = true;
            dayPassed = false;
            Log.d("GETTIME_Day_of_week", "Больше");

        } else {
            Log.d("GETTIME_Day_of_week", "Меньше");
            dayPassed = true;
            elem.setIs_day_passed(true);
        }
    }


    private Calendar getStartLoopTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 6);
        return cal;
    }

    private String makeStringTime(Integer hour, Integer minutes) {
        return ((hour.toString().length() == 2 ? hour.toString() : "0" + hour.toString()) +
                ":" +
                (minutes.toString().length() == 2 ? minutes.toString() : minutes.toString() + "0"));
    }


    private Calendar convert_Str_time_to_Calendar(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//        Log.d("GETTIMEFORMATED",formatted);
        try {
            cal.setTime(sdf.parse(date));// all done

        } catch (ParseException e) {
            Log.e("[Convertor]:", date);
            e.printStackTrace();
        }
        return cal;
    }

    private Schedule findByDateOfWeek(int dayOfWeek) {
        for (Schedule tmp : raw_schedule) {
            if (tmp.getDay() == dayOfWeek) {
                return tmp;
            }
        }
        return null;
    }
}
