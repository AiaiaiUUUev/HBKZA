package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D on 05.07.2017.
 */

public class ResponseMyFamilyMemberCreate {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parameters_count")
    @Expose
    private Integer parametersCount;
    @SerializedName("records_count")
    @Expose
    private Integer recordsCount;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("update_date")
    @Expose
    private long updateDate;
    @SerializedName("is_deleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_surname")
    @Expose
    private String userSurname;
    @SerializedName("user_patronymic")
    @Expose
    private String userPatronymic;
    @SerializedName("birthday")
    @Expose
    private long birthday;
    @SerializedName("role")
    @Expose
    private Integer role;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("shared_with")
    @Expose
    private List<Object> sharedWith = null;
    @SerializedName("tracking_parameters")
    @Expose
    private List<Object> trackingParameters = null;



    @SerializedName("_id")
    @Expose
    private Id _id;

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("id")
    @Expose
    private String id;





    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParametersCount() {
        return parametersCount;
    }

    public void setParametersCount(Integer parametersCount) {
        this.parametersCount = parametersCount;
    }

    public Integer getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(Integer recordsCount) {
        this.recordsCount = recordsCount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserPatronymic() {
        return userPatronymic;
    }

    public void setUserPatronymic(String userPatronymic) {
        this.userPatronymic = userPatronymic;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<Object> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<Object> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public List<Object> getTrackingParameters() {
        return trackingParameters;
    }

    public void setTrackingParameters(List<Object> trackingParameters) {
        this.trackingParameters = trackingParameters;
    }



}