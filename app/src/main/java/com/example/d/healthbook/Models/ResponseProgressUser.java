package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D on 03.07.2017.
 */

public class ResponseProgressUser {

    @SerializedName("user_sum")
    @Expose
    private Integer userSum;
    @SerializedName("sum")
    @Expose
    private Integer sum;
    @SerializedName("progress")
    @Expose
    private Double progress;
    @SerializedName("missions")
    @Expose
    private List<Mission> missions = null;

    public Integer getUserSum() {
        return userSum;
    }

    public void setUserSum(Integer userSum) {
        this.userSum = userSum;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

}