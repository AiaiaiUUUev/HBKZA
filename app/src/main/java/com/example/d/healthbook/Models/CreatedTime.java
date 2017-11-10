package com.example.d.healthbook.Models;

/**
 * Created by D on 29.05.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedTime {

    @SerializedName("sec")
    @Expose
    private Integer sec;
    @SerializedName("usec")
    @Expose
    private Integer usec;

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }

    public Integer getUsec() {
        return usec;
    }

    public void setUsec(Integer usec) {
        this.usec = usec;
    }

}
