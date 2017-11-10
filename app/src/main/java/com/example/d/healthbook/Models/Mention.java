package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 07.06.2017.
 */


public class Mention {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("visit_id")
    @Expose
    private String visitId;
    @SerializedName("performance_mark")
    @Expose
    private String performanceMark;
    @SerializedName("efficiency_mark")
    @Expose
    private String efficiencyMark;
    @SerializedName("recommendation_mark")
    @Expose
    private String recommendationMark;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("entity_type")
    @Expose
    private String entityType;
    @SerializedName("entity_id")
    @Expose
    private String entityId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("is_anonymous")
    @Expose
    private String isAnonymous;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getPerformanceMark() {
        return performanceMark;
    }

    public void setPerformanceMark(String performanceMark) {
        this.performanceMark = performanceMark;
    }

    public String getEfficiencyMark() {
        return efficiencyMark;
    }

    public void setEfficiencyMark(String efficiencyMark) {
        this.efficiencyMark = efficiencyMark;
    }

    public String getRecommendationMark() {
        return recommendationMark;
    }

    public void setRecommendationMark(String recommendationMark) {
        this.recommendationMark = recommendationMark;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

}