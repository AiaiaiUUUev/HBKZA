package com.example.d.healthbook.Models;

import io.realm.RealmObject;

/**
 * Created by D on 29.06.2017.
 */

public class CalendarEvents extends RealmObject {
    private String unix_date;
    private String title;
    private String description;

    public String getUnix_date() {
        return unix_date;
    }

    public void setUnix_date(String unix_date) {
        this.unix_date = unix_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
