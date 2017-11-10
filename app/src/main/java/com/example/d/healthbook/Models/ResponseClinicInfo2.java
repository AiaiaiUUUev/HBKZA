package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pc on 09.06.2017.
 */

public class ResponseClinicInfo2 {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("mention_count")
    @Expose
    private String mentionCount;
    @SerializedName("expert_count")
    @Expose
    private String expertCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMentionCount() {
        return mentionCount;
    }

    public void setMentionCount(String mentionCount) {
        this.mentionCount = mentionCount;
    }

    public String getExpertCount() {
        return expertCount;
    }

    public void setExpertCount(String expertCount) {
        this.expertCount = expertCount;
    }

}