package com.example.d.healthbook.Models;

/**
 * Created by D on 23.06.2017.
 */

public class VisitWorkTimeElement {
    private boolean is_day_passed = false;
    private String time_of_day;
    private String roomID;
    private String infoDateAndTimeToVisit;
    private boolean rangeWorkTime=false;


    public boolean isRangeWorkTime() {
        return rangeWorkTime;
    }

    public void setRangeWorkTime(boolean rangeWorkTime) {
        this.rangeWorkTime = rangeWorkTime;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getInfoDateAndTimeToVisit() {
        return infoDateAndTimeToVisit;
    }

    public void setInfoDateAndTimeToVisit(String infoDateAndTimeToVisit) {
        this.infoDateAndTimeToVisit = infoDateAndTimeToVisit;
    }


    private boolean is_open = true;

    public String getTime_of_day() {
        return time_of_day;
    }

    public void setTime_of_day(String time_of_day) {
        this.time_of_day = time_of_day;
    }

    public boolean is_open() {
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }

    public void setIs_day_passed(boolean is_day_passed) {
        this.is_day_passed = is_day_passed;
    }

    public boolean is_day_passed() {
        return is_day_passed;
    }
}
