package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 05.07.2017.
 */

public class Id {

    @SerializedName("timestamp")
    @Expose
    private long timestamp;
    @SerializedName("machineIdentifier")
    @Expose
    private Integer machineIdentifier;
    @SerializedName("processIdentifier")
    @Expose
    private Integer processIdentifier;
    @SerializedName("counter")
    @Expose
    private Integer counter;
    @SerializedName("timeSecond")
    @Expose
    private long timeSecond;
    @SerializedName("time")
    @Expose
    private long time;
    @SerializedName("date")
    @Expose
    private long date;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getMachineIdentifier() {
        return machineIdentifier;
    }

    public void setMachineIdentifier(Integer machineIdentifier) {
        this.machineIdentifier = machineIdentifier;
    }

    public Integer getProcessIdentifier() {
        return processIdentifier;
    }

    public void setProcessIdentifier(Integer processIdentifier) {
        this.processIdentifier = processIdentifier;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public long getTimeSecond() {
        return timeSecond;
    }

    public void setTimeSecond(long timeSecond) {
        this.timeSecond = timeSecond;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}