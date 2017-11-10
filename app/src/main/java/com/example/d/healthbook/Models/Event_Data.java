package com.example.d.healthbook.Models;

import java.io.Serializable;

/**
 * Created by D on 29.06.2017.
 */

public class Event_Data implements Serializable {
    private int type;
    private String title;
    private String description;
    private String date;
    private String times;
    private String id;
    private String pills;
    private int period;
    private int duration;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String body;

    public Event_Data(int type, String title, String description, String date, String id) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
    }

    public String getPills() {
        return pills;
    }

    public void setPills(String pills) {
        this.pills = pills;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Event_Data(int type, String title, String description, String date, String times, String body, String id,
                      String pills, int period, int duration) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.date = date;
        this.times = times;
        this.body = body;
        this.id = id;
        this.pills = pills;
        this.period = period;
        this.duration = duration;

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
