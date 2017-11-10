package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 29.05.2017.
 */

public class UpdatedTime {

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


