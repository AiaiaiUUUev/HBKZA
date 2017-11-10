package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 26.05.2017.
 */

public class LoginResponseModel {

    @SerializedName("authToken")
    @Expose
    private String authToken;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("lastActiveDate")
    @Expose
    private String lastActiveDate;
    @SerializedName("requestIP")
    @Expose
    private Object requestIP;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(String lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public Object getRequestIP() {
        return requestIP;
    }

    public void setRequestIP(Object requestIP) {
        this.requestIP = requestIP;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}