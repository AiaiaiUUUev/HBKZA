package com.example.d.healthbook.CalendarBekarysa.Models;

/**
 * Created by User on 08.07.2017.
 */

public class CalendarModel {
    public CalendarModel( Integer day,String date){
        this.day = day;
        this.date = date;
    }

    private Integer day;
    private String date;
    private boolean Note;
    private boolean Drugs;

    public boolean isDrugs() {
        return Drugs;
    }

    public void setDrugs(boolean drugs) {
        Drugs = drugs;
    }

    public boolean isNote() {
        return Note;
    }

    public void setNote(boolean note) {
        Note = note;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
