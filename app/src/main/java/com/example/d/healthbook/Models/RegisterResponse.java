package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 29.05.2017.
 */

public class RegisterResponse {

    @SerializedName("invited_by")
    @Expose
    private Object invitedBy;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("bid")
    @Expose
    private String bid;
    @SerializedName("bid_ref")
    @Expose
    private Object bidRef;
    @SerializedName("disabled")
    @Expose
    private Boolean disabled;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("city_id")
    @Expose
    private Object cityId;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("promo")
    @Expose
    private Object promo;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("birthday")
    @Expose
    private Object birthday;
    @SerializedName("created_time")
    @Expose
    private CreatedTime createdTime;
    @SerializedName("updated_time")
    @Expose
    private UpdatedTime updatedTime;
    @SerializedName("id")
    @Expose
    private String id;

    public Object getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(Object invitedBy) {
        this.invitedBy = invitedBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Object getBidRef() {
        return bidRef;
    }

    public void setBidRef(Object bidRef) {
        this.bidRef = bidRef;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Object getCityId() {
        return cityId;
    }

    public void setCityId(Object cityId) {
        this.cityId = cityId;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getPromo() {
        return promo;
    }

    public void setPromo(Object promo) {
        this.promo = promo;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public CreatedTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(CreatedTime createdTime) {
        this.createdTime = createdTime;
    }

    public UpdatedTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(UpdatedTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}